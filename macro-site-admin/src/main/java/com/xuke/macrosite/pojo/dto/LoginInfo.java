package com.xuke.macrosite.pojo.dto;

import com.xuke.macrosite.entity.Article;
import com.xuke.macrosite.entity.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 登录返回用户信息
 * Created by xuke on 2020/9/17
 */
@Data
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = 1663436259495680800L;

    private Integer id;

    private String username;

    private String nickname;

    private String token;

    private String avatarUrl;

    private List<String> roles;

    private List<Integer> likeArticles;

    private List<Integer> collectArticles;

    private String lastLoginDate;
}
