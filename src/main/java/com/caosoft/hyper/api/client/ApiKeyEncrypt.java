package com.caosoft.hyper.api.client;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.apache.tomcat.util.codec.binary.Base64;
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
public class ApiKeyEncrypt {
    private String methodURL;
    private String appKey;
    private String securityKey;
    private Long timeStamp;
    public ApiKeyEncrypt(String methodURL,String appKey,String securityKey){
        this.methodURL = methodURL;
        this.appKey = appKey;
        this.securityKey=securityKey;
        this.timeStamp=System.currentTimeMillis()/1000; //获取服务端的时间，使用10位的时间戳进行比较（秒）
    }

    /**
     * @author 曹梦龙 <138888611@qq.com>
     * @date  2018/10/21 16:37
     * @methodName
     * @describe  创建Sign签名并返回
     * @param
     * @return
     */
    public String getSign(){
        StringBuilder stringBuilder = new StringBuilder();
        //组织好需要等待加密计算的文本字符串
        String waitSignStr = stringBuilder.append(methodURL).append("?appkey=").append(appKey).append("&timestamp=").append(timeStamp.toString()).toString();

        //******加密计算开始*******
        Mac sha256_HMAC = null;
        try {
            sha256_HMAC = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //转化密钥
        SecretKeySpec secret_key = new SecretKeySpec(securityKey.getBytes(), "HmacSHA256");
        try {
            //加密初始化
            sha256_HMAC.init(secret_key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        //加密获得加密后的结果获得服务端的serverSign，准备与请求时传入的sign进行比较
        String sign = Base64.encodeBase64String(sha256_HMAC.doFinal(waitSignStr.getBytes()));

        return sign;
    }

    /**
     * @author 曹梦龙 <138888611@qq.com>
     * @date  2018/10/21 16:37
     * @methodName
     * @describe  返回加密时的时间戳
     * @param
     * @return
     */
    public String getTimeStamp(){
        return this.timeStamp.toString();
    }
}
