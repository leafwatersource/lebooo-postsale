package com.api.lebooo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @Auther lin
 * @Date 2022/4/18
 * 通过接口 验证防伪码真伪
 */
@Controller
public class AntiFakeController {

    private static final Logger logger = LoggerFactory.getLogger(AntiFakeController.class);

    /**
     * 测试、生产值相同
     */
    private static String  appKey = "40735743";
    private static String appSecret = "ed263a826c42ef48c199132864992dd4";
    private static String custid = "201757";

    /**
     * 需提供部署环境服务器ip 给防伪商平台
     */
    private static String clientip = "183.239.146.14";//ip

    /**
     * 1000	正确响应
     * 1001	接口认证失败
     * 1002	授权过期
     * 1003	请求路径不正确或参数缺失
     * 1004	码不正确
     * 1005	IP不在白名单
     * 1006	其它异常
     */
    public static String sendPOSTRequest(String url,HttpHeaders headers, MultiValueMap<String, String> params){
        RestTemplate client = new RestTemplate();
        HttpMethod method = HttpMethod.POST;
        // 将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        // 执行HTTP请求，将返回的结构使用String类格式化
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
        return response.getBody();
    }

    /**
     * 获取accesstoken
     */
    public static void getToken()throws Exception {

//        String getUrl = "http://apiv2.zpserp.com/api/v2/getToken";//测试获取token
        String getUrl = "https://apiv2.onlyid.cn/api/v2/getToken";//线上获取token

        HttpHeaders headers = new HttpHeaders();
        headers.add("clientip",clientip);

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("appKey",appKey);
        multiValueMap.add("appSecret",appSecret);
        String json = new String(sendPOSTRequest(getUrl, headers, multiValueMap).getBytes("ISO8859-1"), StandardCharsets.UTF_8);
        logger.info("-----json-----"+json);
        JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
        if ((jsonStr.get("req_code").getAsLong() == 1000)) {
            logger.info("-----成功-----"+jsonStr.get("req_code").getAsLong());
            JsonObject jsonObject = jsonStr.getAsJsonObject("response");
            String accessToken = jsonObject.get("accessToken").getAsString();
            logger.info("-----accessToken-----"+accessToken);

        }else {
            logger.info("-----失败-----");
        }

    }
    /**
     * 刷新accesstoken
     */
    public static void refreshToken()throws Exception {

//        String getUrl = "http://apiv2.zpserp.com/api/v2/refreshToken";//测试刷新token
        String getUrl = "https://apiv2.onlyid.cn/api/v2/refreshToken";//线上刷新token

        HttpHeaders headers = new HttpHeaders();
        headers.add("clientip",clientip);
        headers.add("accesstoken","Jn3hE0C1Hk6d97Sy13lzt1ABNlD916Kkt14q4J1H0d1YYQ0643xTK0qDJyT42pVX");

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("appKey",appKey);
        String json = new String(sendPOSTRequest(getUrl, headers, multiValueMap).getBytes("ISO8859-1"), StandardCharsets.UTF_8);
        logger.info("-----refreshToken-----"+json);
    }

    /**
     * 验证防伪码
     */
    public static void checkfw()throws Exception {
//        String getUrl = "http://apiv2.zpserp.com/api/v2/checkfw";//测试获取token
        String getUrl = "https://apiv2.onlyid.cn/api/v2/checkfw";//线上获取token

        HttpHeaders headers = new HttpHeaders();
        headers.add("clientip",clientip);
        headers.add("accesstoken","Jn3hE0C1Hk6d97Sy13lzt1ABNlD916Kkt14q4J1H0d1YYQ0643xTK0qDJyT42pVX");//accesstoken
        headers.add("useragent","User-Agent");

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("custid",custid);
        multiValueMap.add("code","2BT3G8XPJ75RPXW1");//防伪码
        String json = new String(sendPOSTRequest(getUrl, headers, multiValueMap).getBytes("ISO8859-1"), StandardCharsets.UTF_8);
        logger.info("-----checkfw-----"+json);
    }
    public static void main(String[] args)throws Exception  {
        getToken();
//        checkfw();
////        refreshToken();
    }


}
