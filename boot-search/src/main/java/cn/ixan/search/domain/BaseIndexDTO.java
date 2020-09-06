package cn.ixan.search.domain;

import cn.ixan.search.domain.constant.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "基本索引查询类")
@Data
public class BaseIndexDTO {
    @ApiModelProperty(value = "索引名称", example = "fulltext")
    @NotBlank(message = "索引名称不能为空")
    private String indexName;

    @ApiModelProperty(value = "索引类型", example = "_doc")
    private String indexType = Constant.INDEX_TYPE;

    @ApiModelProperty(value = "字段名称", example = "title.keyword")
    @NotBlank(message = "字段名称不能为空")
    private String field;

    @ApiModelProperty(value = "返回大小", example = "10")
    private Integer size = 10;
}
