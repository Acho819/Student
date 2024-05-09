package com.example.controller;


import com.example.common.Result;
import com.example.entity.Course;
import com.example.entity.StudentsCourse;
import com.example.service.StudentsCourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/StudentsCourse")
public class StudentsCourseController {

    @Resource
    StudentsCourseService studentsCourseService;

    //添加选课
    @PostMapping("/add")
    public Result add(@RequestBody StudentsCourse studentsCourse){
        studentsCourseService.add(studentsCourse);
        return Result.success();
    }

    //分页查询
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "5") Integer pageSize,
                             StudentsCourse studentsCourse){
        PageInfo<StudentsCourse> pageInfo = studentsCourseService.selectPage(pageNum, pageSize,studentsCourse);
        return Result.success(pageInfo);
    }

    //删除选课
    //删除
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        try {
            studentsCourseService.deleteById(id);
            return Result.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("删除失败:"+ e.getMessage());
        }
    }
}
