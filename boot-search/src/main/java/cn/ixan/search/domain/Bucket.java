package cn.ixan.search.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "包数据")
public class Bucket {
    @ApiModelProperty(value = "文档数量")
    private Integer doc_count;
    @ApiModelProperty(value = "文档字段值")
    private String key;
}
