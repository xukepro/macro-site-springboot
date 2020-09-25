package com.xuke.macrosite.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by xuke on 2020/9/20
 */
@Data
public class TagDetail implements Serializable {
    private static final long serialVersionUID = -1041836464790772324L;
    private Integer id;
    private String tagName;
    private Integer totalArticle;
}
