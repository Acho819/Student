package com.example.service;


import com.example.entity.StudentsCourse;
import com.example.exception.CustomException;
import com.example.mapper.StudentsCourseMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentsCourseService {

    @Resource
    StudentsCourseMapper studentsCourseMapper;

    public void add(StudentsCourse studentsCourse){
        StudentsCourse course = studentsCourseMapper.selectid(studentsCourse);
        if(course != null){
            throw new CustomException("你已选过这个课程");

        }
        studentsCourseMapper.insert(studentsCourse);
    }
}
