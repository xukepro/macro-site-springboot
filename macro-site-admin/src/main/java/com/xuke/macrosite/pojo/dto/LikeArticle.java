package com.xuke.macrosite.pojo.dto;

import com.xuke.macrosite.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xuke on 2020/9/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeArticle implements Serializable {
    private static final long serialVersionUID = 3986010206187133070L;
    private int id;
    private String author;
    private String title;
    private String category;
    private List<Tag> tags;
    private Date likeDate;
}
