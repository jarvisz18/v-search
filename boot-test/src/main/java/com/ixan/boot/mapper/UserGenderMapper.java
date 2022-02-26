package com.ixan.boot.mapper;

import com.ixan.boot.config.BaseMapper;
import com.ixan.boot.domain.UserGender;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/11/11 11:07
 * @description UserGenderMapper
 * @version 1.0
 */
@Repository
public interface UserGenderMapper extends BaseMapper<UserGender> {
	void insertBatch(List<UserGender> userGender);
}
