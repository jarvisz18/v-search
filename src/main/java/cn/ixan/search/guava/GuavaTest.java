package cn.ixan.search.guava;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GuavaTest {



    @Test
    public void testToArray(){
        List<Integer> list = ImmutableList.of(1,2,3,4);
        int[] array = Ints.toArray(list);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void testNumberCompare(){
        int compare = Ints.compare(3, 5);
        System.out.println(compare);
    }

    //todo:这个还需要测试
    @Test(expected = NullPointerException.class)
    public void fileTest(){
        File file = new File(getClass().getResource("guava.txt").getFile());
        List<String> lines = null;
        try {
            lines = Files.readLines(file, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(lines);
    }

    @Test
    public void testImmutableMap(){
        ImmutableMap<String, String> of = ImmutableMap.of("key1", "value1", "key2", "value2");
        System.out.println(of);
    }

    /**
     * 构造一成不变的集合
     */
    @Test
    public void testImmutableList(){
        ImmutableList<String> list = ImmutableList.of("a", "b", "c", "d");
        System.out.println(list);
    }
}
