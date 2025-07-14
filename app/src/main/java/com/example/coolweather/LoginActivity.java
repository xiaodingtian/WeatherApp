package com.example.coolweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.coolweather.base.UserDbHelper;
import com.example.coolweather.base.UserInfo;

public class LoginActivity extends AppCompatActivity {
    private EditText et_username;
    private EditText et_password;
    private CheckBox checkBox;
    private boolean is_login;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        checkBox=findViewById(R.id.checkBox);

        mSharedPreferences=getSharedPreferences("user",MODE_PRIVATE);
        //是否记住密码
        is_login=mSharedPreferences.getBoolean("is_login",false);
        if(is_login){
            String username=mSharedPreferences.getString("username",null);
            String password=mSharedPreferences.getString("password",null);
            et_username.setText(username);
            et_password.setText(password);
            checkBox.setChecked(true);
        }



        //点击注册
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到注册页面
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        //登录
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username =et_username.getText().toString();
                String password =et_password.getText().toString();
                if(TextUtils.isEmpty(username)&& TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }else{
                    UserInfo login= UserDbHelper.getInstance(LoginActivity.this).login(username);
                    if(login!=null)
                    {
                        if(username.equals(login.getUsername())&&password.equals(login.getPassword())){

                            SharedPreferences.Editor edit=mSharedPreferences.edit();
                            edit.putBoolean("is_login",is_login);
                            edit.putString("username",username);
                            edit.putString("password",password);
                            edit.commit();

                            Intent intent=new Intent(LoginActivity.this,WeatherActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "该账号未注册", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                is_login=isChecked;
            }
        });
    }
}