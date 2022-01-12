package com.ixan.boot.mapper;

import com.ixan.boot.config.BaseMapper;
import com.ixan.boot.domain.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.mapping.ResultSetType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2022/1/12 下午7:06
 * @description 商品
 */
@Repository
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
	@Options(fetchSize = 100, resultSetType = ResultSetType.FORWARD_ONLY)
	List<Goods> findAll();
}
