package com.example.coolweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coolweather.base.UserDbHelper;

public class RegisterActivity extends AppCompatActivity {
    private EditText et_usermame;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //初始化控件
        et_usermame=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        //返回
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        //点击注册
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=   et_usermame.getText().toString();
                String password=   et_password.getText().toString();
                if(TextUtils.isEmpty(username)&& TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }else {
                    int row= UserDbHelper.getInstance(RegisterActivity.this).register(username,password,"攒无~~~");
                    if(row>0){
                        Toast.makeText(RegisterActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                        finish();
                    }


                }
            }
        });
    }
}