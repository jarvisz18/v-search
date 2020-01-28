package cn.ixan.search.controller;

import cn.ixan.search.domain.FullTextEntity;
import cn.ixan.search.service.FullTextService;
import cn.ixan.search.utils.DocUtil;
import cn.ixan.search.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 全文查询
 */
@RestController
@Slf4j
public class FullTextController {

    @Autowired
    private FullTextService fullTextService;

    @PostMapping("/init")
    public String init(){
        List<String> readWordFile = DocUtil.readWordFile("C:\\Users\\Administrator\\Desktop\\规范.docx");
        String s = DocUtil.wordToString(readWordFile);
        FullTextEntity entity = new FullTextEntity();
        entity.setId(UUIDUtils.uuid());
        entity.setFull_text(s);
        fullTextService.init(entity);
        return "ok";
    }
}
