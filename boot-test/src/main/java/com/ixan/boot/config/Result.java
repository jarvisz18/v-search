package com.ixan.boot.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/6/27 0:32
 * @description
 */
@Accessors(chain = true)
@Data
public class Result<T> {
    private String code;
    private String msg;
    private T data;
}
