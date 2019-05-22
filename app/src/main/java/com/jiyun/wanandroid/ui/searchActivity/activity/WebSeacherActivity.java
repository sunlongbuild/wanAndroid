package com.jiyun.wanandroid.ui.searchActivity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.jiyun.wanandroid.R;

public class WebSeacherActivity extends AppCompatActivity {

    private WebView mView;
    private TextView mText;
    private Toolbar mTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_seacher);
        initView();
    }

    private void initView() {
        mView = (WebView) findViewById(R.id.view);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        mView.loadUrl(id);
        mView.setWebViewClient(new WebViewClient());

        mText = (TextView) findViewById(R.id.text);
        mTab = (Toolbar) findViewById(R.id.tab);
        String name = intent.getStringExtra("name");
        mText.setText(name);
        mTab.setTitle("");
        setSupportActionBar(mTab);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
    }
    //设置监听事件
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
