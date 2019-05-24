package com.jiyun.wanandroid.api.knowledge;

import com.jiyun.wanandroid.entity.knowledge.KnowledgeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface KnowledgeApi {

    String KnowledgeApi="http://www.wanandroid.com/";

    @GET("tree/json")
    Observable<KnowledgeBean> getKnowledge();
}
