package org.carry.dto;

import lombok.Data;
import org.carry.model.User;

/**
 * @Author: CARRY
 * @CreateTime: 2019-06-20 13:08
 * @Description: ${Description}
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
}
