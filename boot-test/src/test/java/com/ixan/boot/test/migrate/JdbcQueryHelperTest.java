package com.ixan.boot.test.migrate;

import com.ixan.boot.test.migrate.domain.OrderDTO;
import com.ixan.boot.test.migrate.domain.User;
import com.ixan.boot.test.migrate.utils.JdbcQueryHelper;
import org.junit.Test;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class JdbcQueryHelperTest {

	@Test
	public void testPreparedStatementfindAllWithConnection() {
		Connection connection = null;
		try {
			connection = JdbcQueryHelper.getConnection();
			String sql = "select order_id orderId,order_name orderName,order_time orderTime from tbl_order";
			List<OrderDTO> orderDTOList = JdbcQueryHelper.findAll(connection, OrderDTO.class, sql);
			orderDTOList.forEach(System.out::println);
			String sql1 = "select id,name,create_time createTime,update_time updateTime from user where id > ?";
			List<User> userList = JdbcQueryHelper.findAll(connection, User.class, sql1, 2);
			userList.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//关闭资源
			JdbcQueryHelper.close(connection, null);
		}

	}

	@Test
	public void testPreparedStatementfindAll() {
		String sql = "select order_id orderId,order_name orderName,order_time orderTime from tbl_order";
		List<OrderDTO> orderDTOList = JdbcQueryHelper.findAll(OrderDTO.class, sql);
		orderDTOList.forEach(System.out::println);
		String sql1 = "select id,name,create_time createTime,update_time updateTime from user where id > ?";
		List<User> userList = JdbcQueryHelper.findAll(User.class, sql1, 2);
		userList.forEach(System.out::println);
	}

	@Test
	public void testPreparedStatementCommonQuery() {
		String sql = "select order_id orderId,order_name orderName,order_time orderTime from tbl_order where order_id =? ";
		OrderDTO orderDTO = JdbcQueryHelper.findOne(OrderDTO.class, sql, 1);
		System.out.println(orderDTO);
		String sql1 = "select id,name,create_time createTime,update_time updateTime from user where id =? ";
		User user = JdbcQueryHelper.findOne(User.class, sql1, 1);
		System.out.println(user);
	}

	@Test
	public void testGetValue() {
		String sql = "select count(1) from tbl_order";
		Long count = JdbcQueryHelper.getValue(sql);
		System.out.println("record num:" + count);
	}

	@Test
	public void testCommonUpdate() {
		String sql = "insert into tbl_order(order_id,order_name,order_time) values (?,?,?)";
		JdbcQueryHelper.update(sql, 2, "皮蛋瘦肉粥", new Date());
	}


	@Test
	public void testPreparedStatementUpdate() {
		//获取连接
		Connection connection = null;
		try {
			connection = JdbcQueryHelper.getDruidConnection();
			String sql = "update user set name=?,update_time =? where id =?";
			int count = JdbcQueryHelper.update(connection, sql, "刘常亮", new Date(), 4);
			System.out.println("effect rows:" + count);
		} finally {
			//关闭连接
			JdbcQueryHelper.close(connection, null);
		}
	}

	@Test
	public void testPreparedStatementInsert() {
		//获取连接
		Connection connection = null;
		try {
			connection = JdbcQueryHelper.getDruidConnection();
			String sql = "insert into user(name,create_time,update_time) values (?,?,?)";
			int count = JdbcQueryHelper.update(connection, sql, "杨雁生", new Date(), new Date());
			System.out.println("effect rows:" + count);
		} finally {
			//关闭连接
			JdbcQueryHelper.close(connection, null);
		}
	}

}