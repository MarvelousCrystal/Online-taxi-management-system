package com.example.car_hailingapp;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;

/**
 * 订单系统的功能实现类
 */
public class Order_InterfaceImple implements Order_Interface {
    public Connection con;
    private static Statement st;
    private static ResultSet rs;
    private static PreparedStatement pstmt;
    private static String userName;//数据库用户名
    private static String userPwd;//数据库密码

    /**
     * 数据库的连接并创建数据库、表
     */
    public Connection connect() {
        String driverName = "net.sourceforge.jtds.jdbc.Driver";//"com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:jtds:sqlserver://192.168.1.108;DatabaseName=car-hailing";
        userName = "sa";
        userPwd = "Lhy13934040893";

        try {
            Class.forName(driverName); //jdk版本6.0以上可以省略这句话
            con = DriverManager.getConnection(dbURL, userName, userPwd);
            System.out.println("连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 添加数据到数据库中
     *
     * @param con
     */
    public String addOrder(Connection con, Order order) {
//        Toast.makeText(, "", Toast.LENGTH_SHORT).show();
        String result = null;//返回结果
        try {
            result = "测试result：当前进去try\n";
            String sql = "insert into MyOrder values(?,?,?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, order.getOnumber());//第一个参数
            pstmt.setString(10, order.getEvaluate());//第二个参数
            pstmt.setFloat(9, order.getPrice());//第三个参数
            pstmt.setString(2, order.getPnumber());
            pstmt.setString(3, order.getDnumber());
            java.sql.Date dayDateSql1 = new java.sql.Date(order.getStarttime().getTime());
            pstmt.setDate(4, dayDateSql1);
            java.sql.Date dayDateSql2 = new java.sql.Date(order.getEndtime().getTime());
            pstmt.setDate(5, dayDateSql2);
            pstmt.setFloat(6, order.getKm());
            pstmt.setString(7, order.getStartaddress());
            pstmt.setString(8, order.getDestination());
            result = "测试result：当前在pstmt.executeUpdate()前面\n";
            //进入下面这个函数，会出不来或者【直接停止】，从而跳过下面的if

            try {
                int row = pstmt.executeUpdate();//SQL 数据操作语言 (DML) 语句的行数 ,对于无返回内容的 SQL 语句，返回 0
                result = "测试result：当前在pstmt.executeUpdate()后面\n";
                con.commit();
                if (row > 0) {
                    result = "添加数据成功";
                } else {
                    result = "添加数据失败";
                }
            } catch (Exception e) {
                con.rollback();
            }


            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查询数据库信息
     *
     * @param con
     */
    public String findOrder(Connection con, String onumber) {
        String result = null;
        try {

            String sql = "select * from MyOrder where Ono=?";
            pstmt = con.prepareStatement(sql);
            //设置参数的值
            pstmt.setString(1, onumber);

            try {
                rs = pstmt.executeQuery();//int row=pstmt.executeUpdate();//SQL 数据操作语言 (DML) 语句的行数 ,对于无返回内容的 SQL 语句，返回 0
                result = "测试result：当前在pstmt.executeUpdate()后面\n";
                con.commit();
                while (rs.next()) {
                    //getString(n):获取第n列的内容，数据库中列数从1开始
                    result = new String("订单编号：" + rs.getString(1) + "\n" + "订单评价：" + rs.getString(10) + "\n" + "订单价格：" + rs.getString(9));
                }


            } catch (Exception e) {
                con.rollback();
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public  String findPassenger(Connection con,String Pnumber){
        String result = null;
        try {

            String sql = "select * from Passenger where Pno=?";
            pstmt = con.prepareStatement(sql);
            //设置参数的值
            pstmt.setString(1, Pnumber);

            try {
                rs = pstmt.executeQuery();//int row=pstmt.executeUpdate();//SQL 数据操作语言 (DML) 语句的行数 ,对于无返回内容的 SQL 语句，返回 0
                result = "测试result：当前在pstmt.executeUpdate()后面\n";
                con.commit();
                while (rs.next()) {
                    //getString(n):获取第n列的内容，数据库中列数从1开始
                    result = new String("乘客姓名：" + rs.getString(1) + "\n" + "乘客性别：" + rs.getString(2) + "\n" + "乘客手机号：" + rs.getString(3)+ "\n"
                            + "乘客身份证号：" + rs.getString(4)+ "\n" + "乘客账号：" + rs.getString(5)
                            + "\n" + "是否为vip：" + rs.getString(7));
                }


            } catch (Exception e) {
                con.rollback();
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public  String findDriver(Connection con,String Dnumber){
        String result = null;
        try {

            String sql = "select * from Driver where Dno=?";
            pstmt = con.prepareStatement(sql);
            //设置参数的值
            pstmt.setString(1, Dnumber);

            try {
                rs = pstmt.executeQuery();//int row=pstmt.executeUpdate();//SQL 数据操作语言 (DML) 语句的行数 ,对于无返回内容的 SQL 语句，返回 0
                result = "测试result：当前在pstmt.executeUpdate()后面\n";
                con.commit();
                while (rs.next()) {
                    //getString(n):获取第n列的内容，数据库中列数从1开始
                    result = new String("司机姓名：" + rs.getString(1) + "\n" + "司机性别：" + rs.getString(2) + "\n" + "司机手机号：" + rs.getString(3)+ "\n" + "司机身份证号：" + rs.getString(4)+ "\n" +
                            "驾照是否合格：" + rs.getString(5)+ "\n" + "车牌号：" + rs.getString(6)+ "\n"
                            + "家庭住址：" + rs.getString(7)+ "\n" + "司机账号：" + rs.getString(8));
                }


            } catch (Exception e) {
                con.rollback();
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}