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
public class CollectParams implements Serializable {
    private static final long serialVersionUID = -3119612310084225968L;
    private Integer aid;
    private Integer uid;
}
