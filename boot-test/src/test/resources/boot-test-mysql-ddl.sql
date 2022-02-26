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