-- 用户订单秒杀记录表
CREATE TABLE `tb_user_goods_record`
(
    `id`          varchar(64) NOT NULL,
    `user_id`     varchar(64) DEFAULT NULL,
    `goods_id`    varchar(64) DEFAULT NULL,
    `create_time` datetime    DEFAULT NULL,
    `order_id`    varchar(64) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_id` (`user_id`, `goods_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- test.goods definition

CREATE TABLE `goods`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT,
    `goods_name` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10001
  DEFAULT CHARSET = utf8;

-- 账户数据
CREATE TABLE `site_account`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `username`    varchar(100) DEFAULT NULL,
    `password`    varchar(100) DEFAULT NULL,
    `site`        varchar(100) DEFAULT NULL,
    `site_name`   varchar(100) DEFAULT NULL,
    `create_time` datetime     DEFAULT NULL,
    `update_time` datetime     DEFAULT NULL,
    `version`     int(11)      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8