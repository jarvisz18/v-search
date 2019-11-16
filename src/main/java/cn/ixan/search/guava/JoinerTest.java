package cn.ixan.search.guava;

import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class JoinerTest {

    @Test
    public void joinTestThree(){
        int[] numbers = {1,2,3,4,5};
        List<Integer> parts = Ints.asList(numbers);
        String join = Joiner.on(",").join(parts);
        Assert.assertEquals("1,2,3,4,5",join);
    }

    @Test
    public void joinTestTwo(){
        int[] numbers = {1,2,3,4,5};
        String join = Joiner.on(",").join(Ints.asList(numbers));
        Assert.assertEquals("1,2,3,4,5",join);
    }

    @Test
    public void joinTestOne(){
        String[] subdirs = {"usr","local","opt"};
        String join = Joiner.on("/").join(subdirs);
        Assert.assertEquals("usr/local/opt",join);
    }
}
