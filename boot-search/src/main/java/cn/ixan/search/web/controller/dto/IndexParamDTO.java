package cn.ixan.search.web.controller.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author stack_zhang@outlook.com
 * @version 1.0
 * @date Created in 2020/9/7 9:36
 * @description 索引新增修改参数
 */
@ApiModel(description = "通用索引参数类")
@Data
public class IndexParamDTO {
	@ApiModelProperty(value = "主键")
	private String id;
	@ApiModelProperty(value = "索引名称", example = "shakespeare")
	private String index;
	@ApiModelProperty(value = "索引类型", example = "_doc")
	private String type;
	@ApiModelProperty(value = "对象", example = "{}")
	private Object object;
}
