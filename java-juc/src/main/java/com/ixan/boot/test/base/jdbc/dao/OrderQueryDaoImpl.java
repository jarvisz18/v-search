package com.ixan.boot.test.base.jdbc.dao;

import com.ixan.boot.test.base.jdbc.domain.OrderDTO;

import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/11/21 下午10:32
 * @description 订单查询
 */
public class OrderQueryDaoImpl extends AbstractDao<OrderDTO> implements BaseDao<OrderDTO> {
	@Override
	public OrderDTO findById(String sql, Object arg) {
		return super.findById(sql, arg);
	}

	@Override
	public List<OrderDTO> findAll(String sql) {
		return super.findAll(sql);
	}

	@Override
	public int deleteById(String sql, Object arg) {
		return super.deleteById(sql, arg);
	}

	@Override
	public int update(String sql, Object... args) {
		return super.update(sql, args);
	}
}
