package com.example.controller;

import com.example.common.Result;
import com.example.entity.Students;
import com.example.service.StudentsService;
import jdk.jfr.internal.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    //学生修改密码
    @PostMapping("/check-password")
    public Result checkPassword(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String oldPassword = request.get("oldPassword");
        String newPassword = request.get("newPassword");
       studentsService.updatePassword(username, oldPassword,newPassword);
       return Result.success();
    }
    //学生忘记密码
    @PostMapping("/reset")
    public Result ResetPassword(@RequestBody Map<String, String> request){
        String username=request.get("username");
        studentsService.SelectUsername(username);
        return Result.success();
    }
    @PostMapping("/SavePassword")
    public Result SavePassword(@RequestBody Map<String,String> request) {
        String username = request.get("username");
        String phonenumber = request.get("phonenumber");
        String newPassword = request.get("newPassword");
        studentsService.SavePassword(username, phonenumber,newPassword);
        return Result.success();
    }
}