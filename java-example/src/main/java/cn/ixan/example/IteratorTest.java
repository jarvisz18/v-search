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
    private static final List<String> list = new ArrayList<>(100);

    @Before
    public void init() {
        for (int i = 1; i <= 100; i++) {
            list.add("element-" + i);
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLoopRemoveElement() {
        list.forEach(System.out::println);
        System.out.println("----开始删除列表中元素----");

        int bound = list.size();
        for (int i = 0; i < bound; i++) {
            String s = list.get(i);
            String substring = s.substring(s.lastIndexOf("-"));
            if (Integer.parseInt(substring) % 5 == 0) {
                list.remove(i);
            }
        }
        list.forEach(System.out::println);
    }

    @Test
    public void testIteratorRemoveElement() {
        list.forEach(System.out::println);
        System.out.println("----开始删除列表中元素----");
        //使用迭代器删除列表中的元素
        Iterator<String> iterator = list.iterator();

        while (iterator.hasNext()) {
            //多线程情况下加锁
            synchronized (list) {
                String s = iterator.next();
                String substring = s.substring(s.lastIndexOf("-"));
                if (Integer.parseInt(substring) % 5 == 0) {
                    iterator.remove();
                }

            }
        }
        list.forEach(System.out::println);
    }
}
