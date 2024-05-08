package com.example.controller;


import com.example.common.Result;
import com.example.entity.StudentsCourse;
import com.example.service.StudentsCourseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/StudentsCourse")
public class StudentsCourseController {

    @Resource
    StudentsCourseService studentsCourseService;

    @PostMapping("/add")
    public Result add(@RequestBody StudentsCourse studentsCourse){
        studentsCourseService.add(studentsCourse);
        return Result.success();
    }
}
