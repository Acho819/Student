package com.example.entity;

import java.sql.Date;

public class Students extends Account {
    private Integer id;
    private String phonenumber;
    private String email;
    private String sex;
    private Date bith;
    private String tx;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBith() {
        return bith;
    }

    public void setBith(Date bith) {
        this.bith = bith;
    }

    public String getTx() {
        return tx;
    }

    public void setTx(String tx) {
        this.tx = tx;
    }
}
