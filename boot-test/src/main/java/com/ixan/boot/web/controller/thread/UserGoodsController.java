package com.ixan.boot.web.controller.thread;

import com.ixan.boot.service.UserGoodsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/10/4 下午4:26
 * @description 用户秒杀设计
 * <p>区分RR跟RC，可以用唯一约束确保数据不会重复</p>
 */
@RestController
public class UserGoodsController {
	@Autowired
	private UserGoodsRecordService userGoodsRecordService;

	/**
	 * 勇敢迎接并发挑战---结果导致限购判断失效，引发生产事故
	 * --故事描述:
	 * 并发重复下单判定的核心实现:
	 * insert into tb_user_goods_record (id,user_id,goods_id,create_time,order_id)
	 * select ?,?,?,?,? from dual where not exists
	 * (select id from tb_user_goods_record where goods_id = '' and user_id = '')
	 * +++++++++++++++++++++++++++++++++++++++++++++++
	 * --问题复现
	 * 原因描述:
	 * 1.开发环境MySQL事务隔离级别用的是默认的可重复读，测试跟生产保持一致用的是读已提交
	 * 2.读已提交隔离级别下 insert into select not exist并发判断无效!
	 * 3.存在灰产恶意刷单，但程序未做限流等防御措施;
	 * +++++++++++++++++++++++++++++++++++++++++++++++
	 * --原因分析
	 * READ-COMMITTED #读已提交和 REPEATABLE-READ #可重复读 隔离级别比较(关于锁)
	 * 1.在RR隔离级别下，存在间隙锁，导致出现死锁的几率比RC大，秒杀的并发限购使用RR的间隙锁实现，RC下不存在间隙锁
	 * 2.在RR隔离级别下，修改数据时，条件列未命中索引会锁表！而在RC隔离级别下，只锁行(满足条件的)
	 * +++++++++++++++++++++++++++++++++++++++++++++++
	 * --解决方案:
	 * 1.建立user_id跟goods_id的联合唯一索引
	 * 2.对用户的恶意请求做监控，可以用黑名单策略;
	 * 3.针对当前业务，对用户请求做限流操作，根据`userId`判断
	 * --小节
	 * 1.RC 隔离级别下慎用 insert into select no exist;
	 * 2.生产环境建议使用RC隔离级别，提升程序并发性能;
	 * +++++++++++++++++++++++++++++++++++++++++++++++
	 * --建议:
	 * 大家以后开发测试，一定要注意环境是否一致!且很有必要，让各环境保持一致!
	 * +++++++++++++++++++++++++++++++++++++++++++++++
	 */
	@PostMapping("/order/shop")
	public void shop() {
		userGoodsRecordService.shop();
	}
}
