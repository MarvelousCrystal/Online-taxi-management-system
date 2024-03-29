package com.example.car_hailingapp;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

/**
 * 用户登录注册功能实现类
 *
 */
public class Manager_InterfaceImple implements Manager_Interface {

    private static File sdpath= Environment.getExternalStorageDirectory();
    private  static File  myfile=new File(sdpath,"panq.txt");


    //创建类的时候就创建文件
    static {
        try {
            if(myfile.exists())
                System.out.println("文件已存在");
            else{
                myfile.createNewFile();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * 登录功能
     * @param context Activity的上面的某一层是Context,所以传值过来的是一个Activity,此处可以写成Context
     * @param number 用户账号
     * @param password 用户的密码
     * @return
     */
    @Override
    public boolean isloging(Context context,String number, String password)  {
        boolean flag =false;//判断是否登录成功
        BufferedReader br = null;
        try{
            //读取文件信息，看用户名与密码是否匹配
            br = new BufferedReader(new FileReader(myfile));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] date = line.split("=");
                System.out.println("用户账号："+date[0]+",密码："+date[1]);
                if (date[0].equals(number) && date[1].equals(password)) {
                    //信息匹配,登录成功
                    flag = true;
                    break;
                }
            }
        }catch (Exception e1){
            e1.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
        return flag;
    }

    /**
     *
     * @param context Activity的上面的某一层是Context,所以传值过来的是一个Activity,此处可以写成Context
     * @param manager 用户信息
     * @return
     */
    @Override
    public boolean register(Context context,Manager manager) {
        BufferedWriter bw=null;
        BufferedReader br=null;
        boolean isregister=false;//判断是否注册成功
        try{
            //先读取文件，查看用户名是否已注册
            br=new BufferedReader(new FileReader(myfile));
            boolean flag=myload(manager.getNumber());//通过myload函数判断用户名是否已存在
            if(!flag){
                //用户名不存在,即可以使用该用户名，注册成功
                isregister=true;
                //把用户信息写到文件中
                bw=new BufferedWriter(new FileWriter(myfile,true));
                bw.write(manager.getNumber()+"="+manager.getPassword());
                bw.newLine();
                bw.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (br!=null&bw != null) {
                try {
                    bw.close();
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
        return isregister;
    }
    /**
     * 判断用户名是否已存在
     * @param number 用户注册时输入的用户账号
     * @return
     * @throws IOException
     */
    private boolean myload(String number) throws IOException {
        boolean flag = false;
        Properties p = new Properties();
        BufferedReader br = new BufferedReader(new FileReader(myfile));
        p.load(br);
        br.close();
        Set<String> key = p.stringPropertyNames();//获取所有已注册的用户账号
        for (String user : key) {
            if (number.equals(user)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
