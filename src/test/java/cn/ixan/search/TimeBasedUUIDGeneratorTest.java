package cn.ixan.search;

import cn.ixan.search.utils.base.TimeBasedUUIDGenerator;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/3/10 13:25
 * @description TimeBasedUUIDGenerator test
 */
public class TimeBasedUUIDGeneratorTest {
    public static void main(String[] args) {
        System.out.println(new TimeBasedUUIDGenerator().getBase64UUID());
    }
}
