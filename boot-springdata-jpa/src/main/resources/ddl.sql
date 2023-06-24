use test;
drop table if exists send_log;
CREATE TABLE if not exists `send_log`
(
    `id`             int         NOT NULL AUTO_INCREMENT,
    `type`           varchar(64) NOT NULL,
    `template_name`  varchar(64) NOT NULL,
    `entry_id`       int         NOT NULL COMMENT '创建人',
    `entry_datetime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP (3) COMMENT '创建时间',
    `update_id`      int         NOT NULL COMMENT '更新人',
    `update_time`    timestamp(3) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '更新时间',
    `h_version`      int     NOT NULL DEFAULT '0' COMMENT '版本',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- create sequence
drop table if exists seq;
CREATE TABLE if not exists `seq` (
    `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

INSERT INTO test.seq (next_val) VALUES(1);