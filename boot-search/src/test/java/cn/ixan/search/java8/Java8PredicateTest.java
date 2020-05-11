package cn.ixan.search.java8;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.function.Predicate;

public class Java8PredicateTest {

    @Test
    public void testPredicate() {
        String param = "predicate";
        Predicate<String> predicate = (s) -> s.length() > 0;
        boolean test = predicate.test(param);
        Assert.assertTrue(test);
    }

    @Test
    public void testObjectsNonNull(){
        String param = null;
        Predicate<String> predicate = Objects::nonNull;
        boolean test = predicate.test(param);
        Assert.assertFalse(test);
    }

    @Test
    public void testObjectsIsNull(){
        String param = null;
        Predicate<String> function = Objects::isNull;
        boolean test = function.test(param);
        Assert.assertTrue(test);
    }

    @Test
    public void testStringIsEmpty(){
        String param = "";
        Predicate<String> function = StringUtils::isEmpty;
        boolean test = function.test(param);
        Assert.assertTrue(test);
    }

    /**
     * 取反
     */
    @Test
    public void testPredicateNegate(){
        String param = "";
        Predicate<String> function = StringUtils::isEmpty;
        Predicate<String> negate = function.negate();
        boolean test = negate.test(param);
        Assert.assertFalse(test);
    }
}
