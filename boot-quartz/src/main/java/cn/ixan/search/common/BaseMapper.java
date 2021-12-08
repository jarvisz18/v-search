package cn.ixan.search.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/2 下午7:50
 * @description BaseMapper
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
