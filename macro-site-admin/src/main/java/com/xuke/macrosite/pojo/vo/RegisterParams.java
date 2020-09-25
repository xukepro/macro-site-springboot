package com.xuke.macrosite.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by xuke on 2020/5/27
 */
@Data
public class RegisterParams implements Serializable {
    private static final long serialVersionUID = -4524921689681455534L;

    @NotBlank
    @ApiModelProperty(example = "xuke", required = true)
    private String username;

    @NotBlank
    @ApiModelProperty(example = "123456", required = true)
    private String password;

    @NotBlank
    @Email
    @ApiModelProperty(example = "123@qq.com", required = true)
    private String email;

    @NotBlank
    @ApiModelProperty(example = "153745", required = true)
    private String authCode;
}
