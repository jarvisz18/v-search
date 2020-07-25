package cn.ixan.search.mapper;

import cn.ixan.search.domain.Shakespeare;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShakespeareMapper {
    List<Shakespeare> findAll();

    int total();

    List<Shakespeare> simpleQuery(@Param("from") int from, @Param("scrollSize") int scrollSize);

    void addBatch(@Param("list") List<Shakespeare> list);

    void save(Shakespeare shakespeare);

}
