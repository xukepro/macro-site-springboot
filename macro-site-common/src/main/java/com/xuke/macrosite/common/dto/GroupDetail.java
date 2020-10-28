package com.xuke.macrosite.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xuke on 2020/10/20
 */
@Data
public class GroupDetail implements Serializable {

    protected static final long serialVersionUID = 7985019421469957205L;

    @ApiModelProperty(value = "群id")
    protected Integer gid;

    @ApiModelProperty(value = "群主id")
    protected Integer uid;

    @ApiModelProperty(value = "群昵称")
    protected String name;

    @ApiModelProperty(value = "群头像")
    protected String avatar;

    @ApiModelProperty(value = "描述")
    protected String remark;

    @ApiModelProperty(value = "用户数组")
    protected List<GroupUserDetail> userList;

    @ApiModelProperty(value = "群创建时间")
    protected String createdAt;

    protected Long updatedAt;
}
