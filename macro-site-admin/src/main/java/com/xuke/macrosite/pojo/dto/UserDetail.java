package com.xuke.macrosite.pojo.dto;

import com.xuke.macrosite.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by xuke on 2020/9/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail implements Serializable {
    private static final long serialVersionUID = 1085987183255908079L;
    private Integer id;
    private String username;
    private String nickname;
    private String email;
    private String avatar;
    private Integer totalComment;
    private Integer totalArticles;
    private Integer totalLike; // 总被点赞次数
    private Integer totalCollect; // 总文章被收藏次数
    private Integer totalPageViews; // 总文章浏览量
    private List<Role> roles;
    private Date lastLoginDate;
    private Date createdAt;
    private Date updatedAt;
}
