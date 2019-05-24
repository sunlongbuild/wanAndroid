package com.jiyun.wanandroid;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jiyun.wanandroid.ui.MainActivity;

public class MaxActivity extends AppCompatActivity{

    private ImageView mMaxImg;
    private TextView mTime;

    int i = 2;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (i > 0) {
                i--;
                handler.sendEmptyMessageDelayed(1000, 1000);
            } else {
                Intent intent = new Intent(MaxActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max);
        initView();
    }

    private void firstRun() {
        SharedPreferences sharedPreferences = getSharedPreferences("FirstRun", 0);
        Boolean first_run = sharedPreferences.getBoolean("First", true);
        if (first_run) {
            sharedPreferences.edit().putBoolean("First", false).commit();
            //图片缩放
            ObjectAnimator alpha = ObjectAnimator.ofFloat(mMaxImg, "alpha", 1, 0, 1);
            alpha.setDuration(3000);
            alpha.start();
        } else {
            Intent intent = new Intent(MaxActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initView() {
        mMaxImg = (ImageView) findViewById(R.id.max_img);
        mTime = (TextView) findViewById(R.id.time);

        firstRun();
        handler.sendEmptyMessageDelayed(1000, 1000);
    }
}
