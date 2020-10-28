package com.xuke.macrosite.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

/**
 * Created by xuke on 2020/10/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("要添加的群")
public class AddGroupParams implements Serializable {

    private static final long serialVersionUID = 4502543622136285922L;

    @Null
    @ApiModelProperty
    private Integer id;

    @NotNull
    @ApiModelProperty(value = "群主id", example = "1", required = true)
    private Integer uid;

    @NotBlank
    @ApiModelProperty(value = "群昵称", example = "测试群", required = true)
    private String name;

    @NotBlank
    @ApiModelProperty(value = "群头像", required = true)
    private String avatar;

    @NotNull
    @ApiModelProperty(value = "描述", required = true)
    private String remark;
}
