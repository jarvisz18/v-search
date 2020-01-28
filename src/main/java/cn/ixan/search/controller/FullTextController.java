package cn.ixan.search.controller;

import cn.ixan.search.domain.FullTextEntity;
import cn.ixan.search.domain.ImExtInfo;
import cn.ixan.search.service.FullTextService;
import cn.ixan.search.utils.DocParserUtil;
import cn.ixan.search.utils.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 全文查询
 */
@Api("全文查询")
@RestController
@Slf4j
public class FullTextController {

    @Autowired
    private FullTextService fullTextService;

    @PostMapping("/readFileFromPath")
    @ApiOperation(value = "readFileFromPath",notes = "读取文件从输入路径")
    public String readFileFromPath(@RequestParam String path){
        ImExtInfo imExtInfo = DocParserUtil.readFile(path);
        fullTextService.index(imExtInfo);
        return "ok";
    }

    @PostMapping("/readFile")
    @ApiOperation(value = "readFile",notes = "读取文件")
    public String readFile(){
        String path = "C:\\Users\\Administrator\\Desktop\\规范.docx";
        ImExtInfo imExtInfo = DocParserUtil.readFile(path);
        fullTextService.index(imExtInfo);
        return "ok";
    }

    @PostMapping("/init")
    @ApiOperation(value = "init",notes = "读取文件初始化")
    public String init(){
        List<String> readWordFile = DocParserUtil.readWordFile("C:\\Users\\Administrator\\Desktop\\规范.docx");
        String s = DocParserUtil.wordToString(readWordFile);
        FullTextEntity entity = new FullTextEntity();
        entity.setId(UUIDUtils.uuid());
        entity.setFull_text(s);
        fullTextService.init(entity);
        return "ok";
    }
}
