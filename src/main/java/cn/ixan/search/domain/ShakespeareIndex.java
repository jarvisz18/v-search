package cn.ixan.search.domain;

import lombok.Data;

@Data
public class ShakespeareIndex {
    private String index;
    private String type;
    private String id;
    private Shakespeare source;
    private Double score;

}
