package com.ixan.boot.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/6/29 15:31
 * @description user dto
 */
@Data
public class UserDTO implements Serializable {
    private String username;
    private Integer age;
}
