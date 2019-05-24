package com.jiyun.wanandroid.ui.search.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.api.search.Service_Api;
import com.jiyun.wanandroid.entity.search.SearchBean;
import com.jiyun.wanandroid.ui.search.adapter.LishiAdapter;
import com.jiyun.wanandroid.ui.search.adapter.NaviRecAdapter;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchhomeActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mInput;
    private ImageView mSeach;
    private ImageView mImgFanhui;
    private Toolbar mTob;
    private RecyclerView mLf;
    private ArrayList<SearchBean.DataBean> list;
    private ArrayList<String>strings=new ArrayList<>();
    private NaviRecAdapter naviRecAdapter;
    private RecyclerView mLishi;
    /**
     * 清空
     */
    private TextView mText2;
    private LishiAdapter lishiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchhome);
        initView();
        initData();
    }

    private void initData() {
        Retrofit build = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Service_Api.baseUrl)
                .build();
        Service_Api service_api = build.create(Service_Api.class);
        Observable<SearchBean> data = service_api.getData();
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SearchBean>() {
                    @Override
                    public void accept(SearchBean searchBean) throws Exception {
                        list.addAll(searchBean.getData());
                        naviRecAdapter.setList(list);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.seach:
//                getInitData();
                if(strings.size()>10){
                    strings.remove(strings.size()-1);
                    strings.add(0, mInput.getText().toString());
                }else {
                    strings.add( 0,mInput.getText().toString());
                }
                lishiAdapter.notifyDataSetChanged();
                Intent intent = new Intent(SearchhomeActivity.this, SeacherActivity.class);
                intent.putExtra("name", mInput.getText().toString());
                startActivity(intent);

                break;
            case R.id.img_fanhui:
                finish();
                break;
            case R.id.text_2:
                strings.clear();
                lishiAdapter.notifyDataSetChanged();
                break;
        }
    }


    private void initView() {
        list = new ArrayList<>();
        mLf = (RecyclerView) findViewById(R.id.lf);
        mInput = (EditText) findViewById(R.id.input);
        mSeach = (ImageView) findViewById(R.id.seach);
        mSeach.setOnClickListener(this);
        mImgFanhui = (ImageView) findViewById(R.id.img_fanhui);
        mImgFanhui.setOnClickListener(this);
        mTob = (Toolbar) findViewById(R.id.tob);
        mTob.setTitle("");
        setSupportActionBar(mTob);
        naviRecAdapter = new NaviRecAdapter(this);
        mLf.setAdapter(naviRecAdapter);
        mLf.setLayoutManager(new LinearLayoutManager(this));

        mLishi = (RecyclerView) findViewById(R.id.lishi);

        mText2 = (TextView) findViewById(R.id.text_2);
        mText2.setOnClickListener(this);
        lishiAdapter = new LishiAdapter(strings, this);
        mLishi.setAdapter(lishiAdapter);
        mLishi.setLayoutManager(new LinearLayoutManager(this));
    }
}
