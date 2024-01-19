package com.example.car_hailingapp;

import android.content.Context;

/**
 *这是一个用户登录注册接口
 */
public interface Manager_Interface {
    /**
     * 这是判断登录是否成功的方法
     * @param number 用户账号
     * @param password 用户的密码
     * @return  用户是否登录成功
     */
    public abstract boolean isloging(Context context,String number, String password) ;
    /**
     * 这是注册方法
     *
     */
    public abstract boolean register(Context context,Manager manager);

}