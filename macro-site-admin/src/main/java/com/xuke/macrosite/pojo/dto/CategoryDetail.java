package com.xuke.macrosite.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by xuke on 2020/9/16
 */
@Data
public class CategoryDetail implements Serializable {
    private static final long serialVersionUID = -1396470561462288013L;
    private Integer id;
    private String categoryName;
    private Integer articleTotal;
    private Date createdAt;
    private Date updatedAt;
}
