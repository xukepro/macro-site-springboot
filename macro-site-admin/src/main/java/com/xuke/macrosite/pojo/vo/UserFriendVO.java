package com.xuke.macrosite.pojo.vo;

import com.xuke.macrosite.common.constant.ChatMsgType;
import com.xuke.macrosite.common.mongodb.document.FriendChatDocument;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用于聊天界面显示好友及聊天信息
 * Created by xuke on 2020/9/29
 */
@Data
@ApiModel("用户所加好友的详细信息")
public class UserFriendVO implements Serializable, Comparable<UserFriendVO> {

    private static final long serialVersionUID = 5587739687860747090L;

    private Integer type = ChatMsgType.FRIEND;

    private Integer id;

    @ApiModelProperty(value = "自己id")
    private Integer uid;

    @ApiModelProperty(value = "好友id")
    private Integer fid;

    @ApiModelProperty(value = "好友备注")
    private String remark;

    @ApiModelProperty(value = "好友昵称")
    private String nickname;

    @ApiModelProperty(value = "好友头像")
    private String avatar;

    @ApiModelProperty(value = "最近信息")
    private List<FriendChatDocument> recentMessage;

    @ApiModelProperty(value = "未读消息数量")
    private Integer unReadMsgCount;

    @ApiModelProperty(value = "加好友时间")
    private String createdAt;

    @ApiModelProperty(value = "好友相关信息修改时间")
    private Long updatedAt;

    @Override
    public int compareTo(UserFriendVO o) {
        return o.updatedAt - this.updatedAt > 0 ? 1 : -1;
    }
}
