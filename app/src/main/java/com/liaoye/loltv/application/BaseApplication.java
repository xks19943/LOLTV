package com.liaoye.loltv.application;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.liaoye.loltv.api.Api;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;

/**
 * Created by xiaoming on 2017/8/29.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /*
            初始化okgo设置
         */
        HttpHeaders headers  = new HttpHeaders();
        headers.put("DAIWAN-API-TOKEN", Api.Token);
        OkGo.getInstance()
                .init(this)
                .setCacheMode(CacheMode.REQUEST_FAILED_READ_CACHE)  //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers);

        Utils.init(this);
    }
}
