package cn.ixan.example;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/7/25 12:27 PM
 * @description 使用迭代器删除列表中的元素
 */
public class IteratorTest {
    private static final List<String> STRING_LIST = new ArrayList<>(100);

    @Before
    public void init() {
        for (int i = 1; i <= 100; i++) {
            STRING_LIST.add("element-" + i);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLoopRemoveElement() {
        STRING_LIST.forEach(System.out::println);
        System.out.println("----开始删除列表中元素----");

        int bound = STRING_LIST.size();
        for (int i = 0; i < bound; i++) {
            String s = STRING_LIST.get(i);
            String substring = s.substring(s.lastIndexOf("-"));
            if (Integer.parseInt(substring) % 5 == 0) {
                STRING_LIST.remove(i);
            }
        }
        STRING_LIST.forEach(System.out::println);
    }

    @Test
    public void testIteratorRemoveElement() {
        STRING_LIST.forEach(System.out::println);
        System.out.println("----开始删除列表中元素----");
        //使用迭代器删除列表中的元素
        Iterator<String> iterator = STRING_LIST.iterator();

        while (iterator.hasNext()) {
            //多线程情况下加锁
            synchronized (STRING_LIST) {
                String s = iterator.next();
                String substring = s.substring(s.lastIndexOf("-"));
                if (Integer.parseInt(substring) % 5 == 0) {
                    iterator.remove();
                }

            }
        }
        STRING_LIST.forEach(System.out::println);
    }
}
