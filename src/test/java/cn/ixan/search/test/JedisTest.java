package cn.ixan.search.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JedisTest {
    @Autowired
    private Jedis jedis;

    @Test
    public void test(){
        jedis.set("name","stackzhang");
        String name = jedis.get("name");
        System.out.println(name);
    }
}
