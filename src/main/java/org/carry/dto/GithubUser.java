package org.carry.dto;

import lombok.Data;

/**
 * @Author: CARRY
 * @CreateTime: 2019-06-14 22:34
 * @Description: ${Description}
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
