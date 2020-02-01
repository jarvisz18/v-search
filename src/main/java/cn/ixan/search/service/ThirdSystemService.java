package cn.ixan.search.service;

import cn.ixan.search.domain.BaseIndexDTO;

import java.util.Map;

public interface ThirdSystemService {
    Map<String, Object> repeat(BaseIndexDTO baseIndexDTO);
}
