# 欢迎使用HyperApi Client Java SDK 

@(HyperApi)[代码使用|帮助|Markdown]

**HyperApi Java SDK**是HyperApi服务端应用程序配套的客户端SDK工具，使用该SDK可以快速的与HyperApi进行通信，只要在客户端项目代码中引入该SDK即后只要关注编写业务代码即可。

-------------------

## HyperApi 简介

> HyperApi 是一款服务端应用项目，节约项目搭建时间，配套多种编程语言的客户端可SDK提供。

项目地址 https://github.com/CaoMengLong/hyper-api

### HyperApi Client Java SDK 源码使用
**下载安装至你的客户端项目中**
两种方法：
1.下载代码放入你自己的项目中。
2.下载代码编译出jar文件，引入到你的项目中。

**在代码中使用**
1.在HyperClient.java 中修改下配置
``` java
public class HyperClient {
    private String apiBaseUrl="http://127.0.0.1:8080";  //配置服务器地址
    private String appKey="121454155"; //配置应用APPKEY
    private String securityKey="e10adc3949ba59abbe56e057f20f883e"; //配置应用安全密钥
    
    ....   
}
```

2.接口的调用方法
``` java

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
                .setSecurityKey("e10adc3949ba59abbe56e057f20f883e");         //也可手动指定服务端配置参数。
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
```
---------
### HyperApi Client Java SDK 源码结构及说明

//TODO

---------

## 反馈与建议
- 可以直接开issues反馈

---------
感谢阅读这份帮助文档。非专业文档写手，难免有疏忽，尽请谅解。
 