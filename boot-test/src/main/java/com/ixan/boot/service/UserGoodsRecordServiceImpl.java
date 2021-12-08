package com.ixan.boot.service;

import com.ixan.boot.domain.UserGoodsRecord;
import com.ixan.boot.mapper.UserOrderRecordMapper;
import com.ixan.boot.utils.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/10/4 下午5:14
 * @description 秒杀
 */
@Service
public class UserGoodsRecordServiceImpl implements UserGoodsRecordService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserGoodsRecordServiceImpl.class);
	@Autowired
	private UserOrderRecordMapper userOrderRecordMapper;

	/**
	 * #查看当前会话隔离级别
	 * select @@tx_isolation;
	 * #查看系统当前隔离级别
	 * select @@global.tx_isolation;
	 * #修改全局会话隔离级别 已经打开的会话隔离级别无法修改
	 * SET GLOBAL TRANSACTION ISOLATION LEVEL READ COMMITTED;
	 * SET GLOBAL TRANSACTION ISOLATION LEVEL REPEATABLE READ;
	 * #修改当前会话级别
	 * set tx_isolation='READ-COMMITTED';
	 * set tx_isolation='REPEATABLE-READ';
	 * <p>
	 * ##关于事务隔离级别
	 * READ-UNCOMMITTED  #读未提交
	 * READ-COMMITTED    #读已提交
	 * REPEATABLE-READ   #可重复读
	 * SERIALIZABLE      #串行化，可读不可写，写数据必须等待另外一个事务结束
	 * alter table tb_user_goods_record add unique index(user_id,goods_id);
	 */
	@Override
	public void shop() {
		Random random = new Random(100);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10000; i++) {
			executorService.submit(() -> {
				UserGoodsRecord userGoodsRecord = new UserGoodsRecord();
				userGoodsRecord.setId(UuidUtil.get32UUID());
				userGoodsRecord.setOrderId(UuidUtil.get32UUID());
				userGoodsRecord.setCreateTime(new Date());
				userGoodsRecord.setUserId("zhangsan" + random.nextInt(10));
				userGoodsRecord.setGoodsId("iPhone13");
				Integer integer = userOrderRecordMapper.addUserGoodsRecordOne(userGoodsRecord);
				if (integer == 1) {
					LOGGER.info("[{}]当前已经成功秒杀:iPhone13", userGoodsRecord.getUserId());
				}
			});
		}
	}
}
