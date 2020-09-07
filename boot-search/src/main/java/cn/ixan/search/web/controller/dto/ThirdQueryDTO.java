package cn.ixan.search.web.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Map;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/9/7 9:04
 * @description 通用查询实体
 */
@ApiModel(description = "通用索引查询类")
@Data
public class ThirdQueryDTO implements Serializable {
	@ApiModelProperty(value = "索引名称", example = "fulltext")
	@NotBlank(message = "索引名称不能为空")
	private String indexName;
	@ApiModelProperty(value = "索引类型", example = "_doc")
	private String indexType;
	@ApiModelProperty(value = "查询脚本", example = "{}")
	private Map<String, Object> query;
}
