package cn.ixan.search.mapper;

import cn.ixan.search.domain.Shakespeare;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShakespeareMapper {
    int total();
    List<Shakespeare> simpleQuery(@Param("from") int from, @Param("scrollSize") int scrollSize);

    void save(Shakespeare shakespeare);

}
