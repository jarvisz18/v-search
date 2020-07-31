package com.ixan.boot.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author stack_zhang@outlook.com
 * @date Created in 2020/6/26 23:24
 * @description
 */
@Data
@Table(name = "site_account")
public class Account implements Serializable {
    /**
     * Id 表示该字段对应数据库表的主键id
     * GeneratedValue中 strategy表示使用数据库自带的主键生成策略.
     * GeneratedValue中generator配置为"JDBC",在数据插入完毕之后,会自动将主键id填充到实体类中.类似普通mapper.xml中配置的selectKey标签
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "JDBC")
    private Integer id;
    private String username;
    private String password;
    private String site;
    private String site_name;
    private Date create_time;
    private Date update_time;
    private Integer version;
}
