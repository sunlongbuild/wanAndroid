package com.jiyun.wanandroid.ui.search.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.utils.SystemShareUtils;
import com.jiyun.wanandroid.utils.ToastUtil;

public class WebSeacherActivity extends AppCompatActivity {

    private WebView mView;
    private TextView mText;
    private Toolbar mTab;
    private String id;
    private String shareTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_seacher);
        initView();
    }

    private void initView() {
        mView = (WebView) findViewById(R.id.view);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        mView.loadUrl(id);
        mView.setWebViewClient(new WebViewClient());

        mText = (TextView) findViewById(R.id.text);
        mTab = (Toolbar) findViewById(R.id.tab);
        String name = intent.getStringExtra("name");
        shareTitle = name;
        mText.setText(name);
        mTab.setTitle("");
        setSupportActionBar(mTab);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
    }
    @Override
        public boolean onCreateOptionsMenu(Menu menu) {//创建上下文菜单
            getMenuInflater().inflate(R.menu.share_collection_browser, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {//监听上下文选择
            switch (item.getItemId()) {
                case R.id.share:
                    getShareContent();//获取分享内容
                    break;
                case R.id.browser://用浏览器打开
                    if (id != null) {
                        SystemShareUtils.shareNet(id, this);
                    }
                    break;
                case android.R.id.home:
                    finish();
                    break;
            }
            return super.onOptionsItemSelected(item);
        }

        private void getShareContent() {
            if (id != null) {
                SystemShareUtils.shareText(this, getResources().getString(R.string.wanandroid) + "【" + shareTitle + "】" + ":" + id);
            } else {
                ToastUtil.showShort(getResources().getString(R.string.networrk_slow));
            }
        }
}
