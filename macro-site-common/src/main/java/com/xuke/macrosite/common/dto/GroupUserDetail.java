package com.xuke.macrosite.common.dto;

import com.xuke.macrosite.entity.GroupUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xuke on 2020/10/21
 */
@Data
public class GroupUserDetail implements Serializable {

    private static final long serialVersionUID = 7985019421469957205L;

    private Integer id;

    @ApiModelProperty(value = "群id")
    private Integer gid;

    @ApiModelProperty(value = "用户ID")
    private Integer uid;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "群里的备注")
    private String remark;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "等级（0：普通成员，1：群主）")
    private Integer rank;

    @ApiModelProperty(value = "入群时间")
    private String createdAt;

    private String updatedAt;
}
