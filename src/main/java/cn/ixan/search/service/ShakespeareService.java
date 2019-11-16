package cn.ixan.search.service;

import cn.ixan.search.domain.Shakespeare;

import java.util.List;

public interface ShakespeareService {
    boolean clean();
    boolean init();
    boolean backup();
    List<Shakespeare> query(Integer from, Integer size);

    int save(Integer from, Integer size);

}
