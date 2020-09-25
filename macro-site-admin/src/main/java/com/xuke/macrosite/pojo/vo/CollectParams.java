package com.xuke.macrosite.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by xuke on 2020/9/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectParams implements Serializable {
    private static final long serialVersionUID = -3119612310084225968L;

    @NotNull
    private Integer aid;

    @NotNull
    private Integer uid;
}
