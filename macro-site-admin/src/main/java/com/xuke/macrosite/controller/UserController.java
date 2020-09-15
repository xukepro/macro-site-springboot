package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.pojo.dto.UserDetail;
import com.xuke.macrosite.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("info")
    public ResResult<List<UserDetail>> getMyUserInfo(@RequestParam("uid") Integer uid) {
        return ResResult.success(userService.getMyUserInfo(uid));
    }

    @GetMapping("allInfo")
    public ResResult<List<UserDetail>> getAllUserInfo() {
        return ResResult.success(userService.getAllUserInfo());
    }
}
