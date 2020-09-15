package com.xuke.macrosite.pojo.dto;

import com.xuke.macrosite.entity.Article;
import com.xuke.macrosite.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by xuke on 2020/9/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDetail {
    private Integer id;
    private User publisher;
    private Article article;
    private Integer parentId;
    private User receiver;
    private String content;
    private Date createAt;
    private Date updateAt;
}