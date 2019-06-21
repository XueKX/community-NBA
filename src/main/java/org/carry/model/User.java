package org.carry.model;

import lombok.Data;

/**
 * @Author: CARRY
 * @CreateTime: 2019-06-15 21:21
 * @Description: 存入数据库的user表的字段
 */
@Data
public class User {
    private Long id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;

}

