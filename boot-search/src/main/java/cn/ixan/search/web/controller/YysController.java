package cn.ixan.search.web.controller;

import cn.ixan.search.domain.Yys;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "YysController")
@RestController
public class YysController {

    @Autowired
    private JestClient jestClient;

    @ApiOperation(value = "like", hidden = true)
    @GetMapping("/like")
    public List<Yys> like() {
        List<Yys> list = new ArrayList<>();
        String query = "{\"query\":{\"wildcard\":{\"name.keyword\":{\"value\":\"黄河*\"}}}}";
        Search builder = new Search.Builder(query).addIndex("yys").addType("_doc").build();
        try {
            SearchResult execute = jestClient.execute(builder);
            if (execute.isSucceeded()) {
                List<SearchResult.Hit<Yys, Void>> hits = execute.getHits(Yys.class);
                list = hits.stream().map(e -> e.source).collect(Collectors.toList());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @ApiOperation(value = "yys", hidden = true)
    @GetMapping("/yys")
    public String test() {
        Yys yys = new Yys();
        yys.setId("111");
        yys.setName("黄河我爱你");

        Index build = new Index.Builder(yys)
                .index("yys")
                .type("_doc").build();
        try {
            jestClient.execute(build);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
