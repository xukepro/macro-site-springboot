package com.xuke.macrosite.pojo.dto;

import com.xuke.macrosite.mongodb.document.FriendChatDocument;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xuke on 2020/9/29
 */
@Data
public class UserFriendDetail implements Serializable {

    private static final long serialVersionUID = 5587739687860747090L;
    private Integer id;
    private Integer uid;
    private Integer fid;
    private String remark;
    private String nickname;
    private String avatar;
    private List<FriendChatDocument> recentMessage;
    private Integer unReadMsgCount;
    private Date createdAt;
    private Date updatedAt;
}
