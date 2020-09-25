package com.xuke.macrosite.pojo.dto;

import com.xuke.macrosite.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xuke on 2020/9/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleContent implements Serializable {
    private static final long serialVersionUID = -4636101708790684501L;
    private int id;
    private String author;
    private String title;
    private String category;
    private String content;
    private List<String> tags;
    private int pageViews;
    private int like;
    private int collect;
    private int comment;
    private String createdAt;
    private String updatedAt;
}
