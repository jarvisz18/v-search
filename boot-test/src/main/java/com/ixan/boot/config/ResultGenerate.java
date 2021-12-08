package com.ixan.boot.config;

/**
 * @author stackzhang@126.com
 * @date Created in 2020/6/27 0:33
 * @description
 */
public class ResultGenerate {
    public static <T> Result<T> success(T data) {
        return new Result<T>().setCode("200")
                .setMsg("ok")
                .setData(data);
    }

    public static <T> Result<T> success(String msg) {
        return new Result<T>().setCode("200")
                .setMsg(msg);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<T>().setCode("500")
                .setMsg(msg);
    }

    public static <T> Result<T> fail() {
        return new Result<T>().setCode("500")
                .setMsg("操作失败，请稍后再试！");
    }
}
