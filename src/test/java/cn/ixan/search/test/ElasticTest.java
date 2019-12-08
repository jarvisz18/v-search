package cn.ixan.search.test;

import cn.ixan.search.domain.Shakespeare;
import cn.ixan.search.mapper.ShakespeareMapper;
import cn.ixan.search.service.ShakespeareService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ElasticTest {
    @Autowired
    private ShakespeareService shakespeareService;
    @Autowired
    private ShakespeareMapper shakespeareMapper;

    @Test
    public void repeat(){
        List<Shakespeare> list = shakespeareMapper.simpleQuery(0, 1000);
        shakespeareService.bulkDataToES(list);
        log.info("导入ES[{}]条数据",list.size());
    }
}
