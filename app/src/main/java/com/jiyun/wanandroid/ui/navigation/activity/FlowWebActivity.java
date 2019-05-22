package com.jiyun.wanandroid.ui.navigation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.wanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FlowWebActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.web)
    WebView mWeb;
    @BindView(R.id.img)
    ImageView mImg;
    private String web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_web);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {


        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        web = getIntent().getStringExtra("web");
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.loadUrl(web);
        mWeb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTvTitle.setText(title);
            }
        });
    }

    //选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "分享");
        menu.add(1, 2, 1, "收藏");
        menu.add(1, 3, 1, "用浏览器打开");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.img)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.img:
                finish();
                break;
        }
    }
}
