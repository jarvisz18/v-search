package cn.ixan.search.dao;

import cn.ixan.search.domain.SendLog;
import org.springframework.stereotype.Repository;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2023/5/7 21:57
 * @description SendLogRepository impl
 */
@Repository
public class SendLogRepositoryImpl extends BatchJpaRepositoryImpl<SendLog, Integer> {
}
