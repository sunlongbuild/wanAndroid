package com.jiyun.wanandroid.ui.project.activity;

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
import com.jiyun.wanandroid.utils.SystemShareUtils;
import com.jiyun.wanandroid.utils.ToastUtil;

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

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.loadUrl(url);
        mWeb.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTitle.setText(title);
            }
        });
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
    /*
     * *  author gme
     *    time    2019年5月22日 10:25:00
     *    分享内容
     *
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//创建上下文菜单
        getMenuInflater().inflate(R.menu.share_collection_browser,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//监听上下文选择
        switch (item.getItemId()) {
            case R.id.share:
                getShareContent();//获取分享内容
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getShareContent() {
        if (title != null & url != null){
            SystemShareUtils.shareText(this,getResources().getString(R.string.wanandroid)+"【"+title+"】"+":"+url);
        }else {
            ToastUtil.showShort(getResources().getString(R.string.networrk_slow));
        }
    }
}
