package com.example.service;


import com.example.entity.StudentsCourse;
import com.example.exception.CustomException;
import com.example.mapper.StudentsCourseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public PageInfo<StudentsCourse> selectPage(Integer pageNum, Integer pageSize, StudentsCourse studentsCourse) {
        PageHelper.startPage(pageNum,pageSize);
        List<StudentsCourse> list = studentsCourseMapper.selectAll(studentsCourse);
        return PageInfo.of(list);
    }

    public void deleteById(Integer id) {
        studentsCourseMapper.deleteByid(id);
    }
}
