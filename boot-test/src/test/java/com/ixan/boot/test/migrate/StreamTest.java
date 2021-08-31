package com.ixan.boot.test.migrate;

import com.ixan.boot.utils.JdbcUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/8/24 下午10:17
 * @description 流式查询数据
 * <p>
 * jdbc查询上亿数据
 * https://blog.csdn.net/mengxiangxingdong/article/details/87865004
 * ResultSet用法集锦:
 * https://www.iteye.com/blog/soft-development-1420323
 * https://docs.oracle.com/database/121/JJDBC/resltset.htm#JJDBC28611
 */
public class StreamTest {
	public static void main(String[] args) {
		StreamTest streamTest = new StreamTest();
		System.out.println("+++++++++++++++++++++++++++++++");
		//streamTest.select("select * from log_file");
		String sql = "select * from log_file";
		streamTest.streamSelect(sql);

		//String sql = "select column_name from information_schema.columns where table_name = 'log_file' and table_schema = 'user' ";
		//streamTest.printColumn(sql);
		//streamTest.cursorFetch(sql);
	}

	//single table import
	private void singleTableImport(String querySql, String insertSql) {
		long startTime = System.currentTimeMillis();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		Connection connectionPg = null;
		PreparedStatement statementPg = null;

		try {
			connection = getSqlConnection();
			statement = connection.prepareStatement(querySql);
			resultSet = statement.executeQuery();

			//获取另一个连接(写入)
			connectionPg = getSqlConnection();
			connectionPg.setAutoCommit(false);
			statementPg = connectionPg.prepareStatement(insertSql);

			int num = 0;
			while (resultSet.next()) {
				num++;
				statementPg.setString(1, resultSet.getString("col"));
				statementPg.addBatch();
				if (num > 1000) {
					statementPg.executeBatch();
					connectionPg.commit();
					num = 0;
				}
				statementPg.executeBatch();
				connectionPg.commit();
			}
		} catch (SQLException e) {
			try {
				connectionPg.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JdbcUtils.close(resultSet, statement, connection);
			JdbcUtils.close(null, statementPg, connectionPg);
			long endTime = System.currentTimeMillis();
			System.out.println(String.format("总耗时:%s ms", (endTime - startTime)));
		}


	}

	private void printColumn(String sql) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = getSqlConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭资源
			JdbcUtils.close(resultSet, statement, connection);
		}
	}

	//cursor查询
	private void cursorFetch(String sql) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//开启游标查询
		String url = "jdbc:mysql://127.0.0.1:3306/user?useCursorFetch=true";
		try {
			connection = DriverManager.getConnection(url, "root", "root");
			statement = connection.prepareStatement(sql);
			//设置读取行数
			statement.setFetchSize(1000);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String content = resultSet.getString(1) + "｜" + resultSet.getString(2);
				System.out.println(content);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭资源
			JdbcUtils.close(resultSet, statement, connection);
		}
	}

	//流式查询
	private void streamSelect(String sql) {
		long count = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = getSqlConnection();
			statement = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			//设置每次从服务器加载到内存的条数
			statement.setFetchSize(Integer.MIN_VALUE);
			statement.setFetchDirection(ResultSet.FETCH_REVERSE);
			resultSet = statement.executeQuery();

			File file = getFile();
			while (resultSet.next()) {
				String content = resultSet.getString(1) + "｜" + resultSet.getString(2);
				//writeInFile(file, content);
				count++;
				if (count % 600000 == 0) {
					System.out.println(" 写入到第  " + (count / 600000) + " 个文件中！");
				}
				System.out.println(content);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭资源
			JdbcUtils.close(resultSet, statement, connection);
		}
	}

	//普通查询
	private void select(String sql) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = getSqlConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			File file = getFile();
			while (resultSet.next()) {
				String content = "id:" + resultSet.getString(1) + ",content:" + resultSet.getString(2);
				writeInFile(file, content);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭资源
			JdbcUtils.close(resultSet, statement, connection);
		}
	}

	private File getFile() {
		File file = new File("/Users/mac/Desktop/" + "oneline.log");
		if (!file.exists()) {
			try {
				file.createNewFile(); //如果文件不存在则创建文件
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	private void writeInFile(File file, String content) {
		Writer writer = null;
		StringBuilder outputString = new StringBuilder();
		try {
			outputString.append(content).append("\r\n");
			writer = new FileWriter(file, true); // true表示追加
			writer.write(outputString.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}

	private Connection getSqlConnection() {
		String url = "jdbc:mysql://127.0.0.1:3306/user";
		String user = "root";
		String password = "root";
		String driverClass = "com.mysql.cj.jdbc.Driver";
		Connection connection = null;
		try {
			Class.forName(driverClass).newInstance();
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
