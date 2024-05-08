package com.example.mapper;

import com.example.entity.StudentsCourse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface StudentsCourseMapper {

    @Insert("insert into students_course(name,no,students_id,course_id) values(#{name},#{no},#{students_id},#{course_id})")
    void insert(StudentsCourse studentsCourse);

    @Select("select * from students_course where students_id = #{students_id} and course_id = #{course_id}")
    StudentsCourse selectid(StudentsCourse studentsCourse);
}
