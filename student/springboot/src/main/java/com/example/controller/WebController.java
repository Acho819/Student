package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Result;
import com.example.common.Role;
import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.entity.Students;
import com.example.service.AdminService;
import com.example.service.StudentsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class WebController {

    @Resource
    private AdminService adminService;

    @Resource
    private StudentsService studentsService;

    /**
     * 默认请求接口
     */
    @GetMapping("/")
    public Result hello() {
        return Result.success();
    }

    /**
     * 登录接口
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        Account dbAccount;
        if(Role.ADMIN.name().equals(account.getRole())){
             dbAccount = adminService.login(account);
        }           //管理员登录方法
        else if(Role.STUDENTS.name().equals(account.getRole())){
             dbAccount = studentsService.login(account);
        }           //学生登录方法
        else {
            return Result.error("账号不存在");
        }
        return Result.success(dbAccount);
    }

    /**
     * 注册接口
     */
    @PostMapping("/enroll")
    public Result enroll(@RequestBody Students students) {
        if(ObjectUtil.isEmpty(students.getUsername()) || ObjectUtil.isEmpty(students.getPassword())){
            return Result.error("账号或密码为必填项");
        }
        studentsService.enroll(students);
        return Result.success();
    }

}
