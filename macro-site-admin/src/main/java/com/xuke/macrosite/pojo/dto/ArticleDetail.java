package com.xuke.macrosite.pojo.dto;

import com.xuke.macrosite.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Created by xuke on 2020/9/12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDetail {
    private int id;
    private String author;
    private String title;
    private String description;
    private String category;
    private String content;
    private List<Tag> tags;
    private int pageViews;
    private int like;
    private int collect;
    private int comment;
    private Date createAt;
    private Date updateAt;
}
