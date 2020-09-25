package com.xuke.macrosite.pojo.dto;

import com.xuke.macrosite.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xuke on 2020/9/17
 */
@Data
public class UserAdmin implements Serializable {
    private static final long serialVersionUID = 8686651459836212634L;

    private Integer id;

    private String username;

    private String password;

    private List<Role> roles;
}
