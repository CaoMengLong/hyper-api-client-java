package com.caosoft.hyper.api.client;

import com.caosoft.hyper.api.client.service.HyperApiService;
import com.caosoft.hyper.api.starter.po.HyperResult;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * **************************************************************
 * Copyright (c) 1996-2018 CaoSoft.com
 * All rights reserved
 *
 * @author 曹梦龙 <138888611@qq.com>
 * @name com.caosoft.hyper.api.client
 * @describe describe
 * @create 2018/10/21
 * **************************************************************
 */
public class HyperClient {
    private String apiBaseUrl="http://127.0.0.1:8080";
    private String appKey="121454155";
    private String securityKey="e10adc3949ba59abbe56e057f20f883e";

    //设置HyperApiServer服务器地址
    public HyperClient setHyperApiServer(String apiBaseUrl){
        this.apiBaseUrl= apiBaseUrl;
        return this;
    }

    //设置Appkey
    public HyperClient setAppKey(String appKey){
        this.appKey= appKey;
        return this;
    }
    //设置SecurityKey
    public HyperClient setSecurityKey(String securityKey){
        this.securityKey= securityKey;
        return this;
    }


    public Map<String, Object> getApiKeyQueryMap(String methodURL){
        Map<String, Object> queryMap =  new HashMap<>();
        ApiKeyEncrypt apiKeyEncrypt = new ApiKeyEncrypt(methodURL,appKey,securityKey);
        queryMap.put("appkey",appKey);
        queryMap.put("sign",apiKeyEncrypt.getSign());
        queryMap.put("timestamp",apiKeyEncrypt.getTimeStamp());
        return queryMap;
    }

    public <T> T  target(Class<T> apiType){
       return (T) Feign.builder()
                .decoder(new JacksonDecoder())
               .encoder(new JacksonEncoder())
                .target(apiType, apiBaseUrl);
    }

    public HyperResult doPost(String methodURL){
        HyperApiService hyperApiService = this.target(HyperApiService.class);
        ApiKeyEncrypt apiKeyEncrypt = new ApiKeyEncrypt(methodURL,appKey,securityKey);
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("appkey",appKey);
        objectMap.put("sign",apiKeyEncrypt.getSign());
        objectMap.put("timestamp",apiKeyEncrypt.getTimeStamp());
        return hyperApiService.doPost(methodURL,objectMap);
    }

    public HyperResult doPost(String methodURL, Map<String,Object> objectMap){
        HyperApiService hyperApiService = this.target(HyperApiService.class);
        ApiKeyEncrypt apiKeyEncrypt = new ApiKeyEncrypt(methodURL,appKey,securityKey);
        objectMap.put("appkey",appKey);
        objectMap.put("sign",apiKeyEncrypt.getSign());
        objectMap.put("timestamp",apiKeyEncrypt.getTimeStamp());
        return hyperApiService.doPost(methodURL,objectMap);
    }




    /**
     * 获取对象
     */
    public static HyperClient getInstance(){
        return new HyperClient();
    }
}
