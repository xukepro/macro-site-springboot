package com.xuke.macrosite.common.api.route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by xuke on 2020/10/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OffLineVO {

    @NotNull
    private Integer uid;
}
