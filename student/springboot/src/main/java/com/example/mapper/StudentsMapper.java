package com.example.mapper;

import com.example.entity.Account;
import com.example.entity.Students;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface StudentsMapper {

    @Select("select * from students where username = #{username}")
    Students selectByUsername(String username);

    @Insert("insert into students (username,password,name,phonenumber,email,sex,bith,tx,role) " +
            "values(#{username},#{password},#{name},#{phonenumber},#{email},#{sex},#{bith},#{tx},#{role})")
    void insert(Students students1);

    @Delete("delete from students where id = #{id}")
    void deleteById(Integer id);

    @Update("update students set username=#{username},password=#{password},name=#{name},phonenumber=#{phonenumber},email=#{email},sex=#{sex},bith=#{bith},tx=#{tx} where id=#{id}")
    void updateById(Students students);

    @Select("select * from students where username like concat('%',#{username},'%')")
    List<Students> selectAll(Students students);

    Students getLatestStudentInfo();

    @Update("update students set password=#{newPassword} where username=#{username}")
    void updatePassword(String username, String newPassword);

    @Update("update students set password=#{newPassword} where phonenumber=#{phonenumber}")
    void SavePassword(String phonenumber, String newPassword);
}
