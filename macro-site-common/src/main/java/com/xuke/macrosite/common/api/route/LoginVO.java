package com.xuke.macrosite.common.api.route;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * Created by xuke on 2020/10/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("要添加的文章")
public class LoginVO {

    @NotNull
    @ApiModelProperty
    private Integer uid;
}
