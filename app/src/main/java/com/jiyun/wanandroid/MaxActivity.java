package com.jiyun.wanandroid;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.wanandroid.ui.MainActivity;

public class MaxActivity extends AppCompatActivity{

    private ImageView mMaxImg;
    private TextView mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_max);
        initView();
    }

    private void initView() {
        mMaxImg = (ImageView) findViewById(R.id.max_img);
        mTime = (TextView) findViewById(R.id.time);
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        animation.setDuration(3000);
        animation.setRepeatCount(-1);
        mMaxImg.startAnimation(animation);
        animation.start();
        //倒计时
        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(MaxActivity.this, MainActivity.class));
                finish();
            }
        }.start();

    }
}
