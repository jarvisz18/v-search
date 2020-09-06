package cn.ixan.search.service.impl;

import cn.ixan.search.domain.FullTextEntity;
import cn.ixan.search.service.FullTextService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class FullTextServiceImpl implements FullTextService {
	@Autowired
	private JestClient jestClient;

	@Override
	public void index(Object source) {
		Index build = new Index.Builder(source).index("fulltext")
				.type("_doc")
				.build();
		try {
			jestClient.execute(build);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(FullTextEntity entity) {
        Index build = new Index.Builder(entity).index("fulltext")
                .type("_doc")
                .build();
        try {
            jestClient.execute(build);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
