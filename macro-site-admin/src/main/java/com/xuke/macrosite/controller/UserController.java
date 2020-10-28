package com.xuke.macrosite.controller;

import com.xuke.macrosite.entity.User;
import com.xuke.macrosite.pojo.dto.LoginInfo;
import com.xuke.macrosite.pojo.vo.LoginParams;
import com.xuke.macrosite.pojo.vo.RegisterParams;
import com.xuke.macrosite.pojo.dto.UserDetail;
import com.xuke.macrosite.pojo.vo.UpdateUserParams;
import com.xuke.macrosite.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuke on 2020/9/15
 */
@Api(tags = "UserController")
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "获得用户信息")
    @GetMapping("info/{uid}")
    public UserDetail getUserInfo(@PathVariable("uid") Integer uid) {
        return userService.getUserInfo(uid);
    }

    @ApiOperation(value = "获得所有用户信息")
    @GetMapping("info/all")
    public List<UserDetail> getAllUserInfo() {
        return userService.getAllUserInfo();
    }

    @ApiOperation(value = "登录")
    @PostMapping("login")
    public LoginInfo login(@RequestBody @Validated LoginParams params) {
        return userService.login(params);
    }

    @ApiOperation(value = "注册")
    @PostMapping("register")
    public User register(@RequestBody @Validated RegisterParams params) {
        return userService.register(params);
    }

    @ApiOperation(value = "获得注册验证码")
    @GetMapping("authCode")
    public String authCode(@RequestParam("email") String email) {
        return userService.sendAuthCode(email);
    }

    @ApiOperation(value = "修改用户信息")
    @PutMapping("info")
    public UserDetail updateInfo(@RequestBody @Validated UpdateUserParams params) {
        return userService.update(params);
    }
}
