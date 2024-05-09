package com.example.mapper;

import com.example.entity.StudentsCourse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentsCourseMapper {

    @Insert("insert into students_course(name,no,students_id,course_id) values(#{name},#{no},#{students_id},#{course_id})")
    void insert(StudentsCourse studentsCourse);

    @Select("select * from students_course where students_id = #{students_id} and course_id = #{course_id}")
    StudentsCourse selectid(StudentsCourse studentsCourse);

//    @Select("select * from students_course where name like concat ('%',#{name},'%') and no like concat ('%',#{no},'%')")
    List<StudentsCourse> selectAll(StudentsCourse studentsCourse);

    @Delete("delete from students_course where id = #{id}")
    void deleteByid(Integer id);
}
