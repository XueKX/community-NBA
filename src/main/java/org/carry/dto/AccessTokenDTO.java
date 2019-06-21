package org.carry.dto;

import lombok.Data;

/**
 * @Author: CARRY
 * @CreateTime: 2019-06-14 21:58
 * @Description: data transform DO 数据传输模型
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
