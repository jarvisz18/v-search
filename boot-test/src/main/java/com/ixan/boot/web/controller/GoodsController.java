package com.ixan.boot.web.controller;

import com.ixan.boot.domain.Goods;
import com.ixan.boot.utils.SpringContextUtils;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/12 下午7:07
 * @description 商品控制器
 */
@RestController
public class GoodsController {
	private static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);

	@GetMapping("/api/goods/list")
	public void listGoods() {
		Instant start = Instant.now();
		List<Goods> list = new ArrayList<>();
		SqlSessionFactory sqlSessionFactory = SpringContextUtils.getBeanByClass(SqlSessionFactory.class);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Cursor<Goods> cursor = sqlSession.selectCursor("com.ixan.boot.mapper.GoodsMapper.findAll");
		try {
			Iterator<Goods> iterator = cursor.iterator();
			while (iterator.hasNext()) {
				list.add(iterator.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				cursor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			sqlSession.close();
		}
		Instant end = Instant.now();
		LOGGER.info("游标查询商品条数[{}],耗时[{}]ms", list.size(), ChronoUnit.MILLIS.between(start, end));
	}
}
