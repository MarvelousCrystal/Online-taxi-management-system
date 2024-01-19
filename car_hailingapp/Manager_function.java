package com.example.car_hailingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Manager_function extends AppCompatActivity implements View.OnClickListener {
    private Button addButton;//增添订单按钮
    private Button findButton;//查询订单按钮
    private Button findDButton;
    private Button findPButton;
    //private Connection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_function);
        init();
        //dbConnect();


        addButton.setOnClickListener(this);
        //alterButton.setOnClickListener(this);
        findButton.setOnClickListener(this);
        findDButton.setOnClickListener(this);
        findPButton.setOnClickListener(this);
//        cancelButton.setOnClickListener(this);
    }

//    private void dbConnect() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                BookDaoImpl bookImpl=new BookDaoImpl();
//                bookImpl.connect();
//            }
//        }).start();
//
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_button:
                Intent intent1=new Intent(Manager_function.this,Dialog_Activity.class);
                startActivity(intent1);
                break;
            case R.id.find_button:
                Intent intent3=new Intent(Manager_function.this,Find_Activity.class);
                startActivity(intent3);
                break;
            case R.id.findD_button:
                Intent intent2=new Intent(Manager_function.this,FindD_Activity.class);
                startActivity(intent2);
                break;
            case R.id.findP_button:
                Intent intent4=new Intent(Manager_function.this,FindP_Activity.class);
                startActivity(intent4);
                break;
            default:
                break;
        }

    }
    private void init(){
        addButton=(Button)findViewById(R.id.add_button);
        findButton=(Button)findViewById(R.id.find_button);
        //alterButton=(Button)findViewById(R.id.alter_button);
        findDButton=(Button)findViewById(R.id.findD_button);
        findPButton=(Button)findViewById(R.id.findP_button);

    }
}
