package com.jiyun.wanandroid.ui.project.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.wanandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebActivity extends AppCompatActivity {

    @BindView(R.id.web)
    WebView mWeb;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.img)
    ImageView mImg;
    private String url;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        mTitle.setText(title);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.loadUrl(url);
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
