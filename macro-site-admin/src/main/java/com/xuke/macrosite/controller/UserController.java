package com.xuke.macrosite.controller;

import com.xuke.macrosite.common.api.ResPage;
import com.xuke.macrosite.common.api.ResResult;
import com.xuke.macrosite.entity.User;
import com.xuke.macrosite.pojo.vo.LoginParams;
import com.xuke.macrosite.pojo.vo.RegisterParams;
import com.xuke.macrosite.pojo.dto.UserDetail;
import com.xuke.macrosite.pojo.vo.UpdateUserParams;
import com.xuke.macrosite.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by xuke on 2020/9/15
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "获得用户信息")
    @GetMapping("info/{uid}")
    public ResResult<UserDetail> getUserInfo(@PathVariable("uid") Integer uid) {
        return ResResult.success(userService.getUserInfo(uid));
    }

    @ApiOperation(value = "获得所有用户信息")
    @GetMapping("info/all")
    public ResResult<ResPage<UserDetail>> getAllUserInfo() {
        return ResResult.success(ResPage.restPage(userService.getAllUserInfo()));
    }

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public ResResult login(@RequestBody @Validated LoginParams params) {
        return userService.login(params);
    }

    @ApiOperation(value = "注册")
    @PostMapping("register")
    public ResResult register(@RequestBody @Validated RegisterParams params) {
        return userService.register(params);
    }

    @ApiOperation(value = "获得注册验证码")
    @GetMapping("authCode")
    public ResResult authCode(@RequestParam("email") String email) {
        return userService.sendAuthCode(email);
    }

    @ApiOperation(value = "修改用户信息")
    @PutMapping("info")
    public ResResult<Object> updateInfo(@RequestBody @Validated UpdateUserParams params) {
        return ResResult.success(userService.update(params));
    }
}
