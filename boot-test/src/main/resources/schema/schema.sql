/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1-3306
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : 127.0.0.1:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 11/07/2020 15:19:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for site_account
-- ----------------------------
DROP TABLE IF EXISTS `site_account`;
CREATE TABLE `site_account`
(
    `id`          int(11)                                                       NOT NULL AUTO_INCREMENT,
    `username`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
    `site`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '站点',
    `site_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '站点名称',
    `create_time` datetime                                                      NULL DEFAULT NULL,
    `update_time` datetime                                                      NULL DEFAULT NULL,
    `version`     tinyint(255)                                                  NULL DEFAULT NULL COMMENT '版本',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Compact;

-- 操作日志表
-- ----------------------------
-- Table structure for oper_log
-- ----------------------------
DROP TABLE IF EXISTS `oper_log`;
CREATE TABLE `oper_log`
(
    `oper_id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '主键ID',
    `oper_modul`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '功能模块',
    `oper_type`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '操作类型',
    `oper_desc`        text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         NULL COMMENT '操作描述',
    `oper_requ_param`  text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         NULL COMMENT '请求参数',
    `oper_resp_param`  text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         NULL COMMENT '返回参数',
    `oper_user_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '操作员id',
    `oper_user_name`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '操作员名称',
    `oper_method`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作方法',
    `oper_uri`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请求URI',
    `oper_ip`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '请求IP',
    `oper_create_time` datetime                                                      NULL DEFAULT NULL COMMENT '操作时间',
    `oper_ver`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '操作版本号',
    PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
-- ----------------------------
-- 错误日志表
-- Table structure for error_log
-- ----------------------------
DROP TABLE IF EXISTS `error_log`;
CREATE TABLE `error_log`
(
    `exc_id`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '主键ID',
    `exc_requ_param`   text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         NULL COMMENT '请求参数',
    `exc_name`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '异常名称',
    `exc_message`      text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci         NULL COMMENT '异常信息',
    `oper_user_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '操作员id',
    `oper_user_name`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '操作员名称',
    `oper_method`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作方法',
    `oper_uri`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请求URI',
    `oper_ip`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '请求IP',
    `oper_create_time` datetime                                                      NULL DEFAULT NULL COMMENT '操作时间',
    `oper_ver`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '操作版本号',
    PRIMARY KEY (`exc_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

-- 国家行政地域信息表
CREATE TABLE `china_regions`
(
    `id`          bigint(20) unsigned                    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `code`        varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '行政地域编码',
    `name`        varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '行政地域名称',
    `type`        tinyint(4)                             NOT NULL DEFAULT '1' COMMENT '行政地域类型，1:省份，2：城市，3：区域',
    `parent_code` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '上一级行政编码',
    `is_delete`   tinyint(4)                             NOT NULL DEFAULT '0' COMMENT '是否删除 1：已删除；0：未删除',
    `create_time` datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                               NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_code` (`code`) USING BTREE,
    KEY `idx_name` (`name`) USING BTREE,
    KEY `idx_type` (`type`) USING BTREE,
    KEY `idx_parent_code` (`parent_code`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='国家行政地域信息表';

-- 节假日表
CREATE TABLE `holiday`
(
    `id`           varchar(64) NOT NULL,
    `is_delete`    int(11)     DEFAULT NULL,
    `holiday`      datetime    DEFAULT NULL,
    `mark`         varchar(64) DEFAULT NULL,
    `current_year` int(11)     DEFAULT NULL,
    `create_time`  datetime    DEFAULT NULL,
    `update_time`  datetime    DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- log_file
create table log_file
(
    id      int(11) NOT NULL AUTO_INCREMENT,
    content text,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;
