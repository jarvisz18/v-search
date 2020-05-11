package cn.ixan.search.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Shakespeare implements Serializable {
    private String type;
    /**
     * 主键,自动递增
     */
    private String line_id;
    private String play_name;
    private String speech_number;
    private String line_number;
    private String speaker;
    private String text_entry;

}
