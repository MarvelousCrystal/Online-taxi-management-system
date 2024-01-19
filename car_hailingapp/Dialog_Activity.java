package com.example.car_hailingapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;


@SuppressWarnings("ALL")
public class Dialog_Activity extends AppCompatActivity implements View.OnClickListener{
    private EditText Onumber;//订单编号文本框
    private EditText Pnumber;//司机编号文本框
    private EditText Dnumber;//乘客编号文本框
    private EditText starttime;
    private EditText endtime;
    private EditText price;
    private EditText km;
    private EditText startaddress;
    private EditText destination;
    private EditText evaluate;
    private EditText add_result;//操作结果文本框

    private Button yes_button;//确定文本框
    private Button no_button;//取消文本框
    private Connection con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        //handler=new Handler();
        setTitle("增添订单");//设置标题
        init();//控件初始化
        yes_button.setOnClickListener(this);
        no_button.setOnClickListener(this);
    }

    /**
     * 初始化各种控件
     */
    private void init(){
        Onumber=(EditText)findViewById(R.id.add_Onumber_text);
        Pnumber=(EditText)findViewById(R.id.add_Pnumber_text);
        Dnumber=(EditText)findViewById(R.id.add_Dnumber_text);
        starttime=(EditText)findViewById(R.id.add_starttime_text);
        endtime=(EditText)findViewById(R.id.add_endtime_text);
        price=(EditText)findViewById(R.id.add_price_text);
        km=(EditText)findViewById(R.id.add_km_text);
        startaddress=(EditText)findViewById(R.id.add_startaddress_text);
        destination=(EditText)findViewById(R.id.add_destination_text);
        evaluate=(EditText)findViewById(R.id.add_evaluate_text);
        add_result=(EditText)findViewById(R.id.add_result_text);
        yes_button=(Button)findViewById(R.id.add_yes_button);
        no_button=(Button)findViewById(R.id.add_no_button);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_yes_button:
                getResult();
                break;
            case R.id.add_no_button:
                Intent intent=new Intent(Dialog_Activity.this,Manager_function.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    /*public static Date strToDate()throws ParseException{
        Date date = new Date();
        String DATE = "yyy-MM--dd HH:mm:ss";
        SimpleDateFormat sf = new SimpleDateFormat(DATE);
        return
    }*/


    //-------------------START-------------------------------------------------------------
    static class MyHandler extends Handler{

        WeakReference<Dialog_Activity> activity;
        MyHandler(WeakReference<Dialog_Activity> activity){
            this.activity=activity;
        }
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            if (msg.what == 996) {
                String result=msg.getData().getString("KEY11");//获取发送来的消息
                String r=result;
                if(result!="添加数据成功"){
                    r="重复操作，请更换主码";
                }
                //Toast.makeText(activity.get(),"数据操作——"+r,Toast.LENGTH_LONG).show();//先注释，不然显示太多看不清
                if(activity.get().add_result!=null){
                    activity.get().add_result.setText("显示结果："+r);
                }
                //测试的另外一个字符串：
                String fromHandler=msg.getData().getString("KEY1");//获取发送来的消息
                //Toast.makeText(activity.get(),fromHandler,Toast.LENGTH_LONG).show();//先注释，不然显示太多看不清
            }
        }
    }
    //实例化一个刚刚写的Handler对象：
    MyHandler myHandler=new MyHandler(new WeakReference<>(this));
    //-------------------END-------------------------------------------------------------

    /**
     * 操作的类
     * @param
     */
    private void getResult () {

        new Thread(new Runnable() {
            @Override
            public void run () {
                try{
                String DATE ="yyyy-MM-dd HH:mm:ss"; //"yyyy-MM-dd";//
                @SuppressLint("SimpleDateFormat") SimpleDateFormat sf = new SimpleDateFormat(DATE);
                String ono=Onumber.getText().toString().trim();
                String pno=Pnumber.getText().toString().trim();
                String dno=Dnumber.getText().toString().trim();
                Date ost=sf.parse(starttime.getText().toString().trim());
                Date oet=sf.parse(endtime.getText().toString().trim());
                float okm = Float.parseFloat(km.getText().toString().trim());
                String osadd = startaddress.getText().toString().trim();
                String odes = destination.getText().toString().trim();
                float oprice = Float.parseFloat(price.getText().toString().trim());
                String oevaluate = evaluate.getText().toString().trim();

                Order order=new Order(ono,pno,dno,ost,oet,okm,osadd,odes,oprice,oevaluate);
                Order_InterfaceImple impl=new Order_InterfaceImple();
                con=impl.connect();
                con.setAutoCommit(false);
                String result=impl.addOrder(con,order);//返回值为null。。。。。
                /*
                一旦在子线程（这里）发生消息，就会回去调用上面的消息处理函数
                 */
                    //-------------------START-------------------------------------------------------------
                //Looper.prepare();//
                    Message message=new Message();
                    message.what=996;
                    Bundle bundle=new Bundle();
                    bundle.putString("KEY1","从Handler中传出去的一个字符串");//key-value，键对值方式发送消息
                    bundle.putString("KEY11",result);

                    message.setData(bundle);
                    myHandler.sendMessage(message);
                //Looper.loop();//死循环，监听随时的消息，应该放在最后。
                    //-------------------END-------------------------------------------------------------
                }catch (Exception e){
                    e.printStackTrace();
                }

            }


        }).start();
    }
}
