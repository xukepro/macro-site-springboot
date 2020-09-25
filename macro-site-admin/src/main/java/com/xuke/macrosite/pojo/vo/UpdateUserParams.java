package com.xuke.macrosite.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by xuke on 2020/9/19
 */
@Data
@ApiModel
public class UpdateUserParams implements Serializable {
    private static final long serialVersionUID = -8226815404777678854L;

    @NotNull
    @ApiModelProperty(example = "1", required = true)
    private Integer id;

    @ApiModelProperty(example = "123456", required = false)
    private String password;

    @ApiModelProperty(example = "艾克", required = false)
    private String nickname;

    @ApiModelProperty(example = "pic_url", required = false)
    private String avatarUrl;

    @Email
    @ApiModelProperty(example = "123@qq.com", required = false)
    private String email;
}
