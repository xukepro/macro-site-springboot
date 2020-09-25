package com.xuke.macrosite.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by xuke on 2020/9/17
 */
@Data
public class LoginParams implements Serializable {
    private static final long serialVersionUID = 2029860104649940490L;

    @NotBlank
    @ApiModelProperty(example = "xuke", required = true)
    private String username;

    @NotBlank
    @ApiModelProperty(value = "123456", required = true)
    private String password;
}
