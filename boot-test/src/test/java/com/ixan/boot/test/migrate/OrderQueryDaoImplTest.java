package com.ixan.boot.test.migrate;

import com.ixan.boot.test.migrate.dao.OrderQueryDaoImpl;
import com.ixan.boot.test.migrate.domain.OrderDTO;
import org.junit.Test;

import java.util.List;

public class OrderQueryDaoImplTest {

	private OrderQueryDaoImpl orderQueryDao = new OrderQueryDaoImpl();

	@Test
	public void findById() {
		String sql = "select order_id orderId,order_name orderName,order_time orderTime from tbl_order where order_id = ?";
		OrderDTO orderDTO = orderQueryDao.findById(sql, 1);
		System.out.println(orderDTO);
	}

	@Test
	public void findAll() {
		String sql = "select order_id orderId,order_name orderName,order_time orderTime from tbl_order ";
		List<OrderDTO> all = orderQueryDao.findAll(sql);
		all.forEach(System.out::println);
	}

	@Test
	public void deleteById() {
		String sql = "delete from tbl_order where order_id = ?";
		int rowCount = orderQueryDao.deleteById(sql, 3);
		System.out.println("effect rows:" + rowCount);
	}

	@Test
	public void update() {
		String sql = "update tbl_order set order_name = ? where order_id = ?";
		int rowCount = orderQueryDao.update(sql, "皮蛋瘦肉粥套餐", 2);
		System.out.println("effect rows:" + rowCount);
	}
}