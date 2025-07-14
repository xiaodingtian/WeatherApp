package com.example.coolweather;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class WelcomeActivity extends AppCompatActivity {
    ImageView logo,appName,splashImg;
    LottieAnimationView lottieAnimationView;
    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // 延迟结束后执行的跳转逻辑
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // 如果需要，可以结束当前Activity
        }
    };

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        logo=findViewById(R.id.logo);
        appName=findViewById(R.id.app_name);
        splashImg=findViewById(R.id.img);
        lottieAnimationView=findViewById(R.id.lottie);
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                splashImg.animate().translationY(-2200).setDuration(1000).setStartDelay(3000);
                appName.animate().translationY(1600).setDuration(1000).setStartDelay(3000);
                logo.animate().translationY(1600).setDuration(1000).setStartDelay(3000);
                lottieAnimationView.animate().translationY(1600).setDuration(1000).setStartDelay(3000);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                handler.postDelayed(runnable, 800); // 延迟1000毫秒（1秒）
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // 动画取消时执行的代码（可选）
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // 动画重复时执行的代码（可选）
            }
        });


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable); // 确保在Activity销毁时取消延迟任务
    }


}