package com.ixan.boot.test.migrate;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/11/20 下午2:15
 * @description 预编译SQL 测试
 */
public class PreparedStatementTest {

	@Test
	public void testPreparedStatementfindAll() {
		String sql = "select order_id orderId,order_name orderName,order_time orderTime from tbl_order";
		List<OrderDTO> orderDTOList = JdbcUtils.findAll(OrderDTO.class, sql);
		orderDTOList.forEach(System.out::println);
		String sql1 = "select id,name,create_time createTime,update_time updateTime from user where id > ?";
		List<User> userList = JdbcUtils.findAll(User.class, sql1, 2);
		userList.forEach(System.out::println);
	}

	@Test
	public void testPreparedStatementCommonQuery() {
		String sql = "select order_id orderId,order_name orderName,order_time orderTime from tbl_order where order_id =? ";
		OrderDTO orderDTO = JdbcUtils.commonQuery(OrderDTO.class, sql, 1);
		System.out.println(orderDTO);
		String sql1 = "select id,name,create_time createTime,update_time updateTime from user where id =? ";
		User user = JdbcUtils.commonQuery(User.class, sql1, 1);
		System.out.println(user);
	}

	@Test
	public void testPreparedStatementQuery() {
		//获取连接
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = JdbcUtils.getConnection();
			statement = connection.prepareStatement("select order_id,order_name,order_time from tbl_order where order_id =?");
			statement.setObject(1, 1);
			//执行SQL
			statement.execute();
			ResultSet resultSet = statement.getResultSet();
			if (resultSet.next()) {
				OrderDTO orderDTO = new OrderDTO();
				orderDTO.setOrderId(resultSet.getInt(1));
				orderDTO.setOrderName(resultSet.getString(2));
				orderDTO.setOrderTime((Date) resultSet.getObject(3));
				System.out.println(orderDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcUtils.close(connection, statement);
		}
	}

	@Test
	public void testCommonUpdate() {
		String sql = "insert into tbl_order(order_id,order_name,order_time) values (?,?,?)";
		JdbcUtils.commonUpdate(sql, 1, "米线黄焖鸡", new Date());
	}


	@Test
	public void testPreparedStatementUpdate() {
		//获取连接
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = JdbcUtils.getConnection();
			statement = connection.prepareStatement("update user set name=?,update_time =? where id =?");
			statement.setString(1, "哪吒");
			statement.setObject(2, new Date());
			statement.setObject(3, 5);
			//执行SQL
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcUtils.close(connection, statement);
		}
	}

	@Test
	public void testPreparedStatementInsert() {
		//获取连接
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = JdbcUtils.getConnection();
			statement = connection.prepareStatement("insert into user(name,create_time,update_time) values (?,?,?)");
			statement.setString(1, "张贤龙");
			statement.setObject(2, new Date());
			statement.setObject(3, new Date());
			//执行SQL
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//关闭连接
			JdbcUtils.close(connection, statement);
		}
	}
}
