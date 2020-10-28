package com.xuke.macrosite.pojo.vo;

import com.xuke.macrosite.common.constant.ChatMsgType;
import com.xuke.macrosite.common.dto.GroupDetail;
import com.xuke.macrosite.common.dto.GroupUserDetail;
import com.xuke.macrosite.common.mongodb.document.FriendChatDocument;
import com.xuke.macrosite.common.mongodb.document.GroupChatDocument;
import com.xuke.macrosite.entity.GroupUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 用于聊天界面显示群及群聊信息
 * Created by xuke on 2020/10/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("用户所加群的详细信息")
public class UserGroupVO extends GroupDetail implements Serializable, Comparable<UserGroupVO> {

    private static final long serialVersionUID = -3907816354934631282L;

    private Integer type = ChatMsgType.GROUP;

    @ApiModelProperty(value = "用户map, 方便前端调用")
    private HashMap<Integer, GroupUserDetail> userMap;

//    @ApiModelProperty(value = "该用户群里的备注")
//    private String userRemark;
//
//    @ApiModelProperty(value = "等级（0：普通成员，1：群主）")
//    private Integer rank;

    @ApiModelProperty(value = "最近信息")
    private List<GroupChatDocument> recentMessage;

    @ApiModelProperty(value = "未读消息数量")
    private Integer unReadMsgCount;

    @Override
    public String toString() {
        return "UserGroupVO{" +
                "userMap=" + userMap +
                ", recentMessage=" + recentMessage +
                ", unReadMsgCount=" + unReadMsgCount +
                ", gid=" + gid +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", remark='" + remark + '\'' +
                ", userList=" + userList +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    @Override
    public int compareTo(UserGroupVO o) {
        return o.updatedAt - this.updatedAt > 0 ? 1 : -1;
//        List<GroupChatDocument> recentMessage = o.recentMessage;
//        if (recentMessage.size() == 0) {
//            return -1;
//        }
//        if (this.recentMessage.size() == 0) {
//            return 1;
//        }
//        return (int)(recentMessage.get(0).getCreatedAt() - this.recentMessage.get(0).getCreatedAt());
    }
}
