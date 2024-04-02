package com.ahao.ticket.controller;

import com.ahao.ticket.domain.CommonUser;
import com.ahao.ticket.dto.PasswordDTO;
import com.ahao.ticket.service.CommonUserService;
import com.ahao.ticket.utils.RequestUtil;
import com.ahao.ticket.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;
import java.util.Queue;

/**
 * @Author: Mae
 * @Date: 2024/3/21
 * @Time: 21:27
 * @Description:
 */
@RequestMapping("/commonUser")
@RestController
public class CommonUserController {
    @Autowired
    private CommonUserService commonUserService;

    @PostMapping("/login")
    public Result<?> login(@RequestBody CommonUser commonUser) {
        LambdaQueryWrapper<CommonUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommonUser::getUsername, commonUser.getUsername());
        CommonUser one = commonUserService.getOne(queryWrapper);
        if (Objects.isNull(one)) {
            return Result.error("用户名或密码不正确");
        } else if (one.getPassword().equals(commonUser.getPassword())) {
            RequestUtil.getSession().setAttribute("user", one);
            return Result.ok(one);
        } else {
            return Result.error("用户名或密码不正确");
        }
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody CommonUser commonUser) {
        LambdaQueryWrapper<CommonUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommonUser::getUsername, commonUser.getUsername());
        CommonUser one = commonUserService.getOne(queryWrapper);
        if (Objects.isNull(one)) {
            commonUser.setCreateTime(new Date());
            commonUserService.save(commonUser);
            return Result.ok();
        } else {
            return Result.error("该用户名已存在");
        }
    }

    @GetMapping("/logout")
    public Result<?> logout() {
        RequestUtil.removeCurrent();
        return Result.ok();
    }

    @PostMapping("/editInfo")
    public Result<?> editInfo(@RequestBody CommonUser commonUser) {
        LambdaUpdateWrapper<CommonUser> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.eq(CommonUser::getId, commonUser.getId())
                .set(CommonUser::getAge, commonUser.getAge())
                .set(CommonUser::getEmail, commonUser.getEmail())
                .set(CommonUser::getSex, commonUser.getSex());
        commonUserService.update(userLambdaUpdateWrapper);
        return Result.ok(commonUser);
    }

    @PostMapping("/editPassword")
    public Result<?> editPassword(@RequestBody PasswordDTO passwordDTO) {
        CommonUser byId = commonUserService.getById(passwordDTO.getId());
        if (!byId.getPassword().equals(passwordDTO.getOldpassword())) {
            return Result.error("原密码输入错误！");
        } else {
            LambdaUpdateWrapper<CommonUser> userLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
            userLambdaUpdateWrapper.eq(CommonUser::getId, passwordDTO.getId()).set(CommonUser::getPassword, passwordDTO.getNewpassword());
            commonUserService.update(userLambdaUpdateWrapper);
        }
        return Result.ok();
    }
}
