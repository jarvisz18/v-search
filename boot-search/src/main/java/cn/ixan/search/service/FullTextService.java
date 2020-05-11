package cn.ixan.search.service;

import cn.ixan.search.domain.FullTextEntity;

public interface FullTextService {
    void index(Object source);

    void init(FullTextEntity entity);
}
