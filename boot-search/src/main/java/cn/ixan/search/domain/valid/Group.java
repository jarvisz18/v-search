package cn.ixan.search.domain.valid;

import javax.validation.GroupSequence;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2020/3/1 14:07
 * @description group
 */
@GroupSequence({Insert.class, Update.class})
public interface Group {
}
