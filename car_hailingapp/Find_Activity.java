package com.example.car_hailingapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.ref.WeakReference;
import java.sql.Connection;
public class Find_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText findOrder;
    private EditText findResult;
    private Button find_yes;
    private Button find_no;
    private Connection con;

    //内部类
    static class MyHandler extends Handler {
        WeakReference<Find_Activity> fActivity;
        MyHandler(WeakReference<Find_Activity> activity){
            this.fActivity=activity;
        }
        public void handleMessage(@NonNull Message msg){
            super.handleMessage(msg);
            if (msg.what == 996) {
                String result=msg.getData().getString("KEY11");//获取发送来的消息
                String r=result;
                /*if(result!="添加数据成功"){
                    r="重复操作，请更换主码";
                }*/
                //Toast.makeText(activity.get(),"数据操作——"+r,Toast.LENGTH_LONG).show();//先注释，不然显示太多看不清
                if(fActivity.get().findResult!=null){
                    fActivity.get().findResult.setText("显示结果："+r);
                }
                //测试的另外一个字符串：
                String fromHandler=msg.getData().getString("KEY1");//获取发送来的消息
                //Toast.makeText(activity.get(),fromHandler,Toast.LENGTH_LONG).show();//先注释，不然显示太多看不清
            }
        }
    }
    MyHandler myHandler=new MyHandler(new WeakReference<>(this));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        setTitle("查询订单");
        init();
        find_yes.setOnClickListener(this);
        find_no.setOnClickListener(this);

    }

    private void init() {
        findOrder=(EditText)findViewById(R.id.find_ordernumber_text);
        findResult=(EditText)findViewById(R.id.find_result_text);
        find_yes=(Button)findViewById(R.id.find_yes_button);
        find_no=(Button)findViewById(R.id.find_no_button);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.find_yes_button:

                getresult();
                break;
            case R.id.find_no_button:
                Intent intent=new Intent(Find_Activity.this,Manager_function.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    private void getresult() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                String find_orderNumber=findOrder.getText().toString().trim();
                try {
                    Order_InterfaceImple impl=new Order_InterfaceImple();
                    con=impl.connect();
                    con.setAutoCommit(false);
                    String result=impl.findOrder(con,find_orderNumber);
                    if(result!=null){
                        Message message=new Message();
                        message.what=996;
                        Bundle bundle=new Bundle();
                        bundle.putString("KEY1","从Handler中传出去的一个字符串");//key-value，键对值方式发送消息
                        bundle.putString("KEY11",result);

                        message.setData(bundle);
                        myHandler.sendMessage(message);

                        //findResult.setText(result);
                    }else{
                        Message message=new Message();
                        message.what=996;
                        Bundle bundle=new Bundle();
                        bundle.putString("KEY1","从Handler中传出去的一个字符串");//key-value，键对值方式发送消息
                        bundle.putString("KEY11","没有该订单");

                        message.setData(bundle);
                        myHandler.sendMessage(message);
                        //findResult.setText("无查询结果");
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
