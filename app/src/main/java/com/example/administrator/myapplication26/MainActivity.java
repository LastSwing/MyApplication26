package com.example.administrator.myapplication26;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private TextView show;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = (TextView)findViewById(R.id.tv_show);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                show.setText(msg.arg1 + "");
            }
        };

        runnable = new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while (progress < 100){
                    Message message = new Message();
                    message.arg1 = progress;
                    handler.sendMessage(message);
                    progress += 1;
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                Message message = handler.obtainMessage();
                message.arg1 = -1;
                handler.sendMessage(message);
            }
        };

        start = (Button)findViewById(R.id.btn_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null, runnable, "WorkThread");
                workThread.start();
            }
        });


    }
}