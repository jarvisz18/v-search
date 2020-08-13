package com.ixan.boot.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/6/29 15:30
 * @description user
 */
@Data
public class User implements Serializable {
    private String username;
    private Integer age;
}
