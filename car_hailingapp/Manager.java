package com.example.car_hailingapp;

public class Manager {
    private String username;//用户名
    private String sex;
    private String phone;
    private String id;
    private String number;
    private String password;//用户密码
    public Manager(){}

    public Manager(String username, String sex,String phone,String id,String number,String password) {
        this.username = username;
        this.password = password;
        this.id = id;
        this.sex = sex;
        this.phone = phone;
        this.number = number;
    }

    public Manager(String number,String password) {
        this.password = password;
        this.number = number;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }
    public String getPhone() {
        return phone;
    }
    public String getId() {
        return id;
    }
    public String getNumber() {
        return number;
    }
}
