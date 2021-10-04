package com.ixan.boot.mapper;

import com.ixan.boot.config.BaseMapper;
import com.ixan.boot.domain.UserGender;
import com.ixan.boot.domain.UserGoodsRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2021/10/4 下午4:49
 * @description 秒杀订单记录表
 */
@Repository
public interface UserOrderRecordMapper extends BaseMapper<UserGender> {
	Integer addUserGoodsRecordOne(@Param("userGoodsRecord") UserGoodsRecord userGoodsRecord);
}
