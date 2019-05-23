package com.jiyun.wanandroid.ui.searchActivity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jiyun.wanandroid.R;
import com.jiyun.wanandroid.api.search.Service_Api;
import com.jiyun.wanandroid.entity.search.Bean;
import com.jiyun.wanandroid.ui.searchActivity.adapter.Search_Adapter;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SeacherActivity extends AppCompatActivity implements View.OnClickListener {
    private int num;
    private EditText mInput;
    private ImageView mSeach;
    private String input;
    private RecyclerView mRecycler;
    private List<Bean.DataBean.DatasBean> list;
    private ImageView mImgFanhui;
    private Toolbar mTob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        mInput = (EditText) findViewById(R.id.input);
        mSeach = (ImageView) findViewById(R.id.seach);
        mSeach.setOnClickListener(this);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mImgFanhui = (ImageView) findViewById(R.id.img_fanhui);
        mImgFanhui.setOnClickListener(this);
        mTob = (Toolbar) findViewById(R.id.tob);
        mTob.setTitle("");
        setSupportActionBar(mTob);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.seach:
                getInitData();
                break;
            case R.id.img_fanhui:
                finish();
                break;
        }
    }

    private void getInitData() {
        input = mInput.getText().toString();
        int num = 0;
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Service_Api.baseUrl)
                .build();
        Service_Api service_api = retrofit.create(Service_Api.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("k", input);
        Observable<Bean> search = service_api.getSearch(map, num);
        search.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        list = bean.getData().getDatas();
                        Search_Adapter search_adapter = new Search_Adapter(SeacherActivity.this, list);
                        mRecycler.setAdapter(search_adapter);
                        //Toast.makeText(SeacherActivity.this, "000"+list.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
