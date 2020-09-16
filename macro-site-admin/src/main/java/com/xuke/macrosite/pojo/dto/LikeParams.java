package com.xuke.macrosite.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by xuke on 2020/9/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeParams implements Serializable {
    private static final long serialVersionUID = 8386442547321993807L;
    private Integer aid;
    private Integer uid;
}
