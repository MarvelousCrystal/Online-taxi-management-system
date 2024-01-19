package com.example.car_hailingapp;

import java.sql.Connection;

/**
 * 订单功能的接口
 */
interface Order_Interface {
    public  String addOrder(Connection con, Order order) ;//增添订单

    public  String findOrder(Connection con,String Onumber); //查询订单
    public  String findPassenger(Connection con,String Pnumber);
    public  String findDriver(Connection con,String Dnumber);
}
