package com.ixan.boot;
/**
 * <p> 1.actuator 查看日志级别
 * http://localhost:8088/zero/actuator/loggers
 * <p> 2.actuator 查看某package日志级别
 * http://localhost:8088/zero/actuator/loggers/package
 * <p>3.修改日志级别，发送POST请求
 * POST http://localhost:8088/zero/actuator/loggers/[包路径]
 * {
 * "configuredLevel":"warn"
 * }
 * <p>4.查看boot应用健康状态
 * http://localhost:8088/zero/actutor/health
 * <p>5.进入仪表盘
 * http://localhost:8088/zero/hystrix
 */