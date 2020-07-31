package com.ixan.boot.config;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/6/29 15:40
 * @description dto convert
 */
public interface DTOConvert<T,S> {
    T convert(S s);
}
