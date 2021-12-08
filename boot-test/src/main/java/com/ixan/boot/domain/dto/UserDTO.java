package com.ixan.boot.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/6/29 15:31
 * @description user dto
 */
@Data
public class UserDTO implements Serializable {
    private String username;
    private Integer age;
}
