package com.caosoft.hyper;

import com.caosoft.hyper.api.client.HyperClient;
import com.caosoft.hyper.api.client.service.HyperApiService;
import com.caosoft.hyper.api.starter.po.HyperResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    /**
     * @author 曹梦龙 <138888611@qq.com>
     * @date  2018/10/21 17:14
     * @methodName postDemo
     * @describe  测试调用服务/index/demo方法
     * @param []
     * @return void
     */
    public void postDemo(){
        HyperClient hyperClient = HyperClient.getInstance();
        HyperApiService hyperApiService = hyperClient.target(HyperApiService.class);
        Map<String, Object> objectMap =hyperClient.getApiKeyQueryMap("/index/demo");
        try {
            HyperResult hyperResult = hyperApiService.doPost("/index/demo",objectMap);
            ObjectMapper objectMapper =new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(hyperResult));
        }catch (FeignException exception){
            System.out.println(exception.status());
            System.out.println(exception.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * @author 曹梦龙 <138888611@qq.com>
     * @date  2018/10/21 17:14
     * @methodName postDemo
     * @describe  测试调用服务/index/demo方法
     * @param []
     * @return void
     */
    public void postDemo2(){
        HyperClient hyperClient = HyperClient.getInstance();
        try {
            HyperResult hyperResult = hyperClient.doPost("/index/demo");
            ObjectMapper objectMapper =new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(hyperResult));
        }catch (FeignException exception){
            System.out.println(exception.status());
            System.out.println(exception.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * @author 曹梦龙 <138888611@qq.com>
     * @date  2018/10/21 17:15
     * @methodName postDemoForm
     * @describe  测试调用服务/index/demoForm方法
     * @param []
     * @return void
     */
    public void postDemoForm(){
        HyperClient hyperClient = HyperClient.getInstance()
                .setHyperApiServer("http://127.0.0.1:8080")
                .setAppKey("121454155")
                .setSecurityKey("e10adc3949ba59abbe56e057f20f883e");
        HyperApiService hyperApiService = hyperClient.target(HyperApiService.class);
        Map<String, Object> objectMap =hyperClient.getApiKeyQueryMap("/index/demoForm");
        objectMap.put("name","CaoMengLong");
        try {
            HyperResult hyperResult = hyperApiService.doPost("/index/demoForm",objectMap);
            ObjectMapper objectMapper =new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(hyperResult));
        }catch (FeignException exception){
            System.out.println(exception.status());
            System.out.println(exception.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * @author 曹梦龙 <138888611@qq.com>
     * @date  2018/10/21 17:15
     * @methodName postDemoForm
     * @describe  测试调用服务/index/demoForm方法
     * @param []
     * @return void
     */
    public void postDemoForm2(){
        HyperClient hyperClient = HyperClient.getInstance()
                .setHyperApiServer("http://127.0.0.1:8080")
                .setAppKey("121454155")
                .setSecurityKey("e10adc3949ba59abbe56e057f20f883e");
        //Form表单需要传递的参数
        Map<String, Object> objectMap =new HashMap<>();
        objectMap.put("name","CaoMengLong");
        try {
            HyperResult hyperResult = hyperClient.doPost("/index/demoForm",objectMap);
            ObjectMapper objectMapper =new ObjectMapper();
            System.out.println(objectMapper.writeValueAsString(hyperResult));
        }catch (FeignException exception){
            System.out.println(exception.status());
            System.out.println(exception.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
