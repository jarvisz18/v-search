package cn.ixan.search.java8;

import org.junit.Test;

import java.util.Comparator;

public class Java8Comparator {

    @Test
    public void testCompareTo(){
        Comparator<Person> function = (p1, p2) -> p1.getUserName().compareTo(p2.getUserName());
        Person person1 = new Person("dongchenglong", "董成龙");
        Person person2 = new Person("zhangxianlong", "张贤龙");
        int compare = function.compare(person1, person2);
        System.out.println(compare);
    }
}
