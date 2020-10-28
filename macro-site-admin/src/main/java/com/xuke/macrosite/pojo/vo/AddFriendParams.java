package com.xuke.macrosite.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by xuke on 2020/10/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("要添加的好友")
public class AddFriendParams {

    @NotNull
    @ApiModelProperty(example = "1", required = true)
    private Integer uid;

    @NotNull
    @ApiModelProperty(example = "2", required = true)
    private Integer fid;
}
