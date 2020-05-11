package cn.ixan.search.java8;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Function;

public class Java8FunctionTest {

    @Test
    public void testIntegerValueOf(){
        Function<Integer, Integer> function = Integer::valueOf;
        Integer apply = function.apply(100);
        Assert.assertEquals(new Integer(100),apply);
    }

    @Test
    public void testStringValueOf(){
        Function<Object, String> function = String::valueOf;
        String apply = function.apply(1024);
        Assert.assertEquals("1024",apply);
    }
}
