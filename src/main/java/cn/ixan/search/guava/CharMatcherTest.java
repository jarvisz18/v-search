package cn.ixan.search.guava;

import com.google.common.base.CharMatcher;
import org.junit.Assert;
import org.junit.Test;

public class CharMatcherTest {

    @Test
    public void testRemoveFrom(){
        String s = CharMatcher.digit().removeFrom("some text 89983 and more");
        Assert.assertEquals("some text  and more",s);
    }

    @Test
    public void retainFromTest(){
        String s = CharMatcher.digit().retainFrom("some text 89983 and more");
        Assert.assertEquals("89983",s);
    }
}
