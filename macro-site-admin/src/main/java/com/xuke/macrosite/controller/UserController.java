package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.pojo.dto.UserDetail;
import com.xuke.macrosite.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/15
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("info/{uid}")
    public ResResult<List<UserDetail>> getMyUserInfo(@PathVariable("uid") Integer uid) {
        return ResResult.success(userService.getMyUserInfo(uid));
    }

    @GetMapping("info/all")
    public ResResult<List<UserDetail>> getAllUserInfo() {
        return ResResult.success(userService.getAllUserInfo());
    }
}
