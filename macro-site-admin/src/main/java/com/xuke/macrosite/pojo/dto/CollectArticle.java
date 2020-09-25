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
public class CollectArticle implements Serializable {
    private static final long serialVersionUID = 8003929049399347067L;
    private int id;
    private String author;
    private String title;
    private String collectDate;
}
