package com.example.service;

import com.example.entity.Account;
import com.example.entity.Students;
import com.example.exception.CustomException;
import com.example.mapper.StudentsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentsService {

    @Resource
    private StudentsMapper studentsMapper;
    private Students latestStudentInfo;


    //登录逻辑
    public Account login(Account account){
        Account dbStudents = studentsMapper.selectByUsername(account.getUsername());
        if (dbStudents == null){
            //账号不存在
            throw new CustomException("账号不存在");
        }
        //核对密码
        if (!account.getPassword().equals(dbStudents.getPassword())){
            throw new CustomException("账号或密码错误");
        }
        return dbStudents;
    }

    //将用户注册数据传至数据库
    public void enroll(Students students) {
        Students students1 = new Students();
        students1.setUsername(students.getUsername());
        students1.setPassword(students.getPassword());
        students1.setName(students.getName());
        students1.setPhonenumber(students.getPhonenumber());
        students1.setEmail(students.getEmail());
        students1.setSex(students.getSex());
        students1.setBith(students.getBith());
        students1.setRole(students.getRole());

        this.add(students1);
    }

    //注册逻辑
    public void add(Students students1) {
        Students dbStudents = studentsMapper.selectByUsername(students1.getUsername());
        if(dbStudents != null){
            throw new CustomException("账号已存在");
        }
        studentsMapper.insert(students1);
    }

    public void deleteById(Integer id) {
        studentsMapper.deleteById(id);
    }

    public void updateById(Students students) {
        studentsMapper.updateById(students);
    }

    public PageInfo<Students> selectPage(Integer pageNum,Integer pageSize, Students students) {
        PageHelper.startPage(pageNum,pageSize);
        List<Students> studentsList = studentsMapper.selectAll(students);
        return PageInfo.of(studentsList);
    }

    public Students getStudentInfoByUsername(String username) {
        return studentsMapper.selectByUsername(username);
    }

    //修改密码

    public void updatePassword(String username, String oldPassword, String newPassword) {
        // 查询用户信息
        Account dbStudents = studentsMapper.selectByUsername(username);
        if (dbStudents == null || !oldPassword.equals(dbStudents.getPassword())) {
            throw new CustomException("原密码错误，请重新输入");
        }
        // 如果旧密码匹配，则更新密码
        studentsMapper.updatePassword(username, newPassword);
    }
}
