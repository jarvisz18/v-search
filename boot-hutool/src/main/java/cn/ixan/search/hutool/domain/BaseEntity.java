package cn.ixan.search.hutool.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * @author stackzhang@126.com
 * @version 1.0
 * @date Created in 2021/12/22 下午7:11
 * @description 基础实体
 */
public class BaseEntity implements Serializable {
	@Override
	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullStringAsEmpty);
	}
}
