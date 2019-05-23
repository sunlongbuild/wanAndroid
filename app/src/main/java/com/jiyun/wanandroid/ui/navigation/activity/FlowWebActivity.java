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
import com.jiyun.wanandroid.utils.SystemShareUtils;
import com.jiyun.wanandroid.utils.ToastUtil;

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
    private String shareTitle;

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
                shareTitle = title;
            }
        });
    }


    /*
     * *  author gme
     *    time   2019年5月22日 11:31:52
     *    分享内容
     *
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//创建上下文菜单
        getMenuInflater().inflate(R.menu.share_collection_browser,menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {//监听上下文选择
        switch (item.getItemId()) {
            case R.id.share:
                getShareContent();//获取分享内容
                break;
            case R.id.browser://打开浏览器
                if (web != null){
                    SystemShareUtils.shareNet(web,this);
                }
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
    private void getShareContent() {
        if (shareTitle != null && web != null){
            SystemShareUtils.shareText(this,getResources().getString(R.string.wanandroid)+"【"+shareTitle+"】"+":"+web);
        }else {
            ToastUtil.showShort(getResources().getString(R.string.networrk_slow));
        }
    }

}
