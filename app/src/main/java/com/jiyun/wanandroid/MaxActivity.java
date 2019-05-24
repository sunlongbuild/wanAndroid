package com.jiyun.wanandroid;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
<<<<<<< HEAD
import android.os.Message;
=======
>>>>>>> e9872ac8263e2e55812e4b571aee5d264e78a347
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.jiyun.wanandroid.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaxActivity extends AppCompatActivity{

    @BindView(R.id.one_animation)
    LottieAnimationView mOneAnimation;
    @BindView(R.id.two_animation)
    LottieAnimationView mTwoAnimation;
    @BindView(R.id.three_animation)
    LottieAnimationView mThreeAnimation;
    @BindView(R.id.four_animation)
    LottieAnimationView mFourAnimation;
    @BindView(R.id.five_animation)
    LottieAnimationView mFiveAnimation;
    @BindView(R.id.six_animation)
    LottieAnimationView mSixAnimation;
    @BindView(R.id.seven_animation)
    LottieAnimationView mSevenAnimation;
    @BindView(R.id.eight_animation)
    LottieAnimationView mEightAnimation;
    @BindView(R.id.nine_animation)
    LottieAnimationView mNineAnimation;
    @BindView(R.id.ten_animation)
    LottieAnimationView mTenAnimation;

    @Override
    protected void onDestroy() {
        cancelAnimation();
        super.onDestroy();
    }
    private Handler handler = new Handler();
    int time=1;

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
        ButterKnife.bind(this);
        initData();
        inSkip();
//
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

<<<<<<< HEAD
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
=======
    private void inSkip() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (time==0) {
                    startActivity(new Intent(MaxActivity.this, MainActivity.class));
                    finish();
                }else {
                    time--;
                    inSkip();
                }
            }
        },1000);
    }

    private void initData() {
        startAnimation(mOneAnimation, "W.json");
        startAnimation(mTwoAnimation, "A.json");
        startAnimation(mThreeAnimation, "N.json");
        startAnimation(mFourAnimation, "A.json");
        startAnimation(mFiveAnimation, "N.json");
        startAnimation(mSixAnimation, "D.json");
        startAnimation(mSevenAnimation, "R.json");
        startAnimation(mEightAnimation, "I.json");
        startAnimation(mNineAnimation, "O.json");
        startAnimation(mTenAnimation, "D.json");
    }
    private void cancelAnimation() {
        cancelAnimation(mOneAnimation);
        cancelAnimation(mTwoAnimation);
        cancelAnimation(mThreeAnimation);
        cancelAnimation(mFourAnimation);
        cancelAnimation(mFiveAnimation);
        cancelAnimation(mSixAnimation);
        cancelAnimation(mSevenAnimation);
        cancelAnimation(mEightAnimation);
        cancelAnimation(mNineAnimation);
        cancelAnimation(mTenAnimation);
    }

    private void startAnimation(LottieAnimationView mLottieAnimationView, String animationName) {
        mLottieAnimationView.setAnimation(animationName);
        mLottieAnimationView.playAnimation();
    }
    private void cancelAnimation(LottieAnimationView mLottieAnimationView) {
        if (mLottieAnimationView != null) {
            mLottieAnimationView.cancelAnimation();
        }
>>>>>>> e9872ac8263e2e55812e4b571aee5d264e78a347
    }
}
