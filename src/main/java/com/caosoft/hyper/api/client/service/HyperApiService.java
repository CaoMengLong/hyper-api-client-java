package com.caosoft.hyper.api.client.service;

import com.caosoft.hyper.api.starter.po.HyperResult;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;

/**
 * **************************************************************
 * Copyright (c) 1996-2018 CaoSoft.com
 * All rights reserved
 *
 * @author 曹梦龙 <138888611@qq.com>
 * @name HyperApiService
 * @describe 调用服务演示
 * @create 2018/10/20
 * **************************************************************
 */

public interface HyperApiService {
    @RequestLine("POST /index/demo")
    HyperResult demo(@QueryMap Map<String, Object> queryMap);

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @RequestLine("POST /index/demoForm")
    HyperResult demoForm(@QueryMap Map<String, Object> queryMap);


    @RequestLine("POST {methodURL}")
    HyperResult doPost(@Param("methodURL") String methodURL, @QueryMap Map<String, Object> queryMap);

}
