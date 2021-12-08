package com.ixan.boot.mapper;

import com.ixan.boot.config.BaseMapper;
import com.ixan.boot.domain.LogFile;
import org.springframework.stereotype.Repository;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/8/14 下午1:53
 * @description 日志记录
 */
@Repository
public interface LogOneLineFileMapper extends BaseMapper<LogFile> {
}
