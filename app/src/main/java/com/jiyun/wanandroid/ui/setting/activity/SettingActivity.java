package com.jiyun.wanandroid.ui.setting.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.base.BaseActivity;
import com.jiyun.wanandroid.base.Constants;
import com.jiyun.wanandroid.presenter.EmptyPresenter;
import com.jiyun.wanandroid.utils.Logger;
import com.jiyun.wanandroid.utils.SpUtil;
import com.jiyun.wanandroid.utils.SystemShareUtils;
import com.jiyun.wanandroid.view.EmptyView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView, View.OnClickListener {

    @BindView(R.id.tool_setting)
    Toolbar toolSetting;
    @BindView(R.id.ch_no_image)
    CheckBox chNoImage;

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
        setSupportActionBar(toolSetting);//支持toolbar
        toolSetting.setNavigationIcon(R.mipmap.icon_left);//设置返回键
        toolSetting.setTitle(getResources().getString(R.string.setting));
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

}

