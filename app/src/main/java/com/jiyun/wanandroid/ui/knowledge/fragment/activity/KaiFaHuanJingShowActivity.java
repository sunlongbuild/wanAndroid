package com.jiyun.wanandroid.ui.knowledge.fragment.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.jiyun.wanandroid.R;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;

public class KaiFaHuanJingShowActivity extends AppCompatActivity {

    private TextView mTxtToolbar;
    private Toolbar mToolbar;
    private String link;
    private AgentWeb magentWeb;
    private LinearLayout mLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kai_fa_huan_jing_show);
        initView();
    }

    private void initView() {
        mTxtToolbar = (TextView) findViewById(R.id.txt_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        StatusBarUtil.setLightMode(this);

        link = getIntent().getStringExtra("link");


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
                }
                super.onReceivedTitle(view, title);
            }
        });


    }

    @Override
    protected void onResume() {
        magentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        magentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }
}
