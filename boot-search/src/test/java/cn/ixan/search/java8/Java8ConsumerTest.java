package cn.ixan.search.java8;

import org.junit.Test;

import java.util.function.Consumer;

public class Java8ConsumerTest {

    @Test
    public void testConsumerToString(){
        Consumer<Person> function = p -> System.out.println("Hello,"+p.getUserName());
        function.accept(new Person("zhangxianlong","张贤龙"));
    }
}
