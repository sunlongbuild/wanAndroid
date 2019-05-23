package com.jiyun.wanandroid.ui.setting.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseActivity;
import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.presenter.EmptyPresenter;
import com.jiyun.wanandroid.ui.MainActivity;
import com.jiyun.wanandroid.utils.DataCleanManager;
import com.jiyun.wanandroid.utils.SpUtil;
import com.jiyun.wanandroid.view.EmptyView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView, View.OnClickListener {

    @BindView(R.id.tool_setting)
    Toolbar toolSetting;
    @BindView(R.id.ch_no_image)
    CheckBox chNoImage;
    @BindView(R.id.rl_no_image)
    RelativeLayout mRlNoImage;
    @BindView(R.id.clear_cache)
    TextView mClearCache;
    @BindView(R.id.tv_cache)
    TextView mTvCache;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        try {
            //查看缓存的大小
            String cacheSize = DataCleanManager.getTotalCacheSize(SettingActivity.this);
            mTvCache.setText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        toolSetting.setTitle("");
        setSupportActionBar(toolSetting);//支持toolbar
        toolSetting.setNavigationIcon(R.mipmap.icon_left);//设置返回键
        toolSetting.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        detectionOptions();//关闭检测选择框是否勾选
    }

    private void detectionOptions() {
        boolean checked = chNoImage.isChecked();
        SpUtil.setParam(Constants.SETTING_NO_IMAGE, checked);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //当界面显示的时候  获取保存的状态
        boolean image = (boolean) SpUtil.getParam(Constants.SETTING_NO_IMAGE, false);
        chNoImage.setChecked(image);
        //getCache();//获取app的缓存
    }

    private void getCache() {

    }

    @OnClick({R.id.clear_cache,R.id.rl_no_author})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.clear_cache:
                clearcache();
                break;
            case R.id.rl_no_author:

                break;
        }
    }

    private void clearcache() {
        //清除操作
        DataCleanManager.clearAllCache(SettingActivity.this);
        try {
            //清除后的操作
            String s = DataCleanManager.getTotalCacheSize(SettingActivity.this);
            mTvCache.setText(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

