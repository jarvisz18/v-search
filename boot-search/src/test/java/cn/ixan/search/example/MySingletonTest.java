package cn.ixan.search.example;

import org.junit.Test;

public class MySingletonTest {

    @Test
    public void testSingleton(){
        IdlerSingleton instance = IdlerSingleton.getInstance();
        System.out.println("实例1:"+instance);
        IdlerSingleton instance2 = IdlerSingleton.getInstance();
        System.out.println("实例2:"+instance2);

    }

}
