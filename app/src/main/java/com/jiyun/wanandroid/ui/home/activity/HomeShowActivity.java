package com.jiyun.wanandroid.ui.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.utils.Logger;
import com.jiyun.wanandroid.utils.SystemShareUtils;
import com.jiyun.wanandroid.utils.ToastUtil;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;

public class HomeShowActivity extends AppCompatActivity {


    private TextView mTxtToolbar;
    private Toolbar mToolbar;
    private AgentWeb magentWeb;
    private LinearLayout mLl;
    private String link;
    private String shareTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_show);
        initView();
    }

    private void initView() {
        StatusBarUtil.setLightMode(this);

        link = getIntent().getStringExtra("link");
        Logger.logD("link", "链接地址" + link);

        mTxtToolbar = (TextView) findViewById(R.id.txt_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mLl = (LinearLayout) findViewById(R.id.ll);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.mipmap.back_white);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!magentWeb.back()) {
                    finish();
                }
            }
        });
        magentWeb = AgentWeb.with(this)
                .setAgentWebParent(mLl, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()
                .createAgentWeb()
                .ready()
                .go(link);

        /*获取网页标题*/
        magentWeb.getWebCreator().getWebView().setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (!TextUtils.isEmpty(title)) {
                    mTxtToolbar.setText(title);
                    shareTitle = title;
                }
                super.onReceivedTitle(view, title);
            }
        });


    }




    /*
     * *  author gme
     *    time   2019年5月22日 10:20:50
     *    分享内容
     *
     */
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
                if (link != null){
                    SystemShareUtils.shareNet(link,this);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getShareContent() {
        if (shareTitle != null & link != null) {
            SystemShareUtils.shareText(this, getResources().getString(R.string.wanandroid) + "【" + shareTitle + "】" + ":" + link);
        } else {
            ToastUtil.showShort(getResources().getString(R.string.networrk_slow));
        }
    }
}
