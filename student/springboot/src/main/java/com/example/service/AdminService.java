package com.example.service;

import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    /**
     * 登录逻辑
     */
    public Account login(Account account){
        Account dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if (dbAdmin == null){
            //账号不存在
            throw new CustomException("账号不存在");
        }
        //核对密码
        if (!account.getPassword().equals(dbAdmin.getPassword())){
            throw new CustomException("账号或密码错误");
        }
        return dbAdmin;
    }

}
