package com.example.leiguo.networkprograming;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketActivity extends AppCompatActivity {

    private TextView showTextView;
    private Button mButton;
    private Handler handler;
    private String line;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        showTextView = (TextView) findViewById(R.id.show_textview);
        mButton = (Button) findViewById(R.id.button);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123){
                    showTextView.setText("获得来自服务器的数据：" + line);
                }
            }
        };
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread()
                {
                    @Override
                    public void run() {
                        try {
                            //建立连接到远程服务器的Socket
                            Socket socket = new Socket("10.0.2.2", 30001);
                            //讲Socket对应的输入流包装成BufferedReader
                            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            //进行IO操作
                            line = br.readLine();
                            Message msg = new Message();
                            msg.what = 0x123;
                            handler.sendMessage(msg);
                            //关闭输入流和socket
                            br.close();
                            socket.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

    }
}
