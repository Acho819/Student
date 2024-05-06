package com.example.mapper;

import com.example.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CourseMapper {

    @Select("select * from course where name like concat('%',#{name},'%')" +
            " and  no like concat('%',#{no},'%') " +
            "and  teacher like concat('%',#{teacher},'%')")
    List<Course> selectAll(Course course);

    @Insert("insert into course (name,no,descr,times,teacher) values(#{name},#{no},#{descr},#{times},#{teacher})")
    void insert(Course course);

    @Update("update course set name=#{name},no=#{no},descr=#{descr},times=#{times},teacher=#{teacher} where id=#{id}")
    void updateById(Course course);

    @Delete("DELETE FROM course WHERE id = #{id}")
    void deleteById(@Param("id") Integer id);
}
