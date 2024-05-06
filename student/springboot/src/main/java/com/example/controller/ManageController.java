package com.example.controller;

import com.example.common.Result;
import com.example.entity.Students;
import com.example.service.StudentsService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/student")
public class ManageController {

    @Resource
    StudentsService studentsService;

    //新增
    @PostMapping("/add")
    public Result add(@RequestBody Students students){
        studentsService.add(students);
        return Result.success();
    }

    //删除
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        studentsService.deleteById(id);
        return Result.success();
    }

    //编辑
    @PutMapping("/update")
    public Result update(@RequestBody Students students){
        studentsService.updateById(students);
        return Result.success();
    }

    //查询
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "5") Integer pageSize,
                             Students students){
        PageInfo<Students> pageInfo=studentsService.selectPage(pageNum,pageSize,students);
        return Result.success(pageInfo);
    }

    //更新学生信息
    @GetMapping("/info/{username}")
    public Result getCurrentStudentInfo(@PathVariable String username) {
        try {
            // 根据当前登录的学生账号用户名查询学生信息
            Students studentInfo = studentsService.getStudentInfoByUsername(username);
            // 返回学生信息
            return Result.success(studentInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("获取学生信息失败");
        }
    }
}
