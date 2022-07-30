package com.api.lebooo.master.api.controller;

import com.api.lebooo.master.service.UserService;
import com.api.lebooo.enums.CodeEnum;
import com.api.lebooo.model.User;
import com.api.lebooo.model.UserExample;
import com.api.lebooo.utils.JedisPoolUtils;
import com.api.lebooo.utils.ReferenceUtil;
import com.api.lebooo.utils.TokenUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import me.fishlord.common.result.ResultEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date 2022-05-07 11:07
 */
@Slf4j
@Controller
public class WxOpenidController {

    private static String APPID = "wxbe513ee647f2dc4b";
    private static String SECRET = "1c6d8c827429ded8ddeb5b799a4d43e9";

    @Autowired
    private JedisPoolUtils jedisPoolUtils;

    @Autowired
    private UserService userService;

    public static String sendPOSTRequest(String url, HttpHeaders headers, MultiValueMap<String, String> params){
        RestTemplate client = new RestTemplate();
        HttpMethod method = HttpMethod.POST;
        // 将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        // 执行HTTP请求，将返回的结构使用String类格式化
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
        return response.getBody();
    }

    public static String sendPOSTRequest2(String url){
        RestTemplate client = new RestTemplate();
        HttpMethod method = HttpMethod.POST;
        // 将请求头部和参数合成一个请求
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(null, null);
        // 执行HTTP请求，将返回的结构使用String类格式化
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
        return response.getBody();
    }


    /**
     * 微信授权回调接口
     *
     * @param code
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/wx/getCode")
    public ResultEntity getOpenid(String code) throws IOException {

        ResultEntity resultEntity = new ResultEntity();
        try {
            if (StringUtils.isBlank(code)){
                log.info("微信授权回调， code = {} ", code);
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setMsg("授权失败");
                return resultEntity;
            }

            /**
             * {
             *     "access_token":"56_7iTztOphBih_H7-3VFXFzVeuKR1ibxNpu8Vc8oWDdfznuI3eYFT2ON9lCaPzYGzUk7hzQ1j-x9XscAt5QQs7aw",
             *     "expires_in":7200,
             *     "refresh_token":"56_i8t5hzw5_yXBewnIbsg4YTGFNg5rmTtXcFOPHRHRsTU4LOo-GhGmcr0RyS9WPvN4EyIvnx6zlq89RNOUjYllJw",
             *     "openid":"o3p7XjgMAU-rGsT86Es58a7812",
             *     "scope":"snsapi_userinfo",
             *     "unionid":"ogAiB1bNUGmSLIW7ETU6AP71H-F0"
             * }
             *
             * {"errcode":40163,"errmsg":"code been used, rid: 627a0a69-15e596e7-64ff60f1"}
             */
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+SECRET+"&code="+code+"&grant_type=authorization_code";
            String json = new String(sendPOSTRequest2(url).getBytes("ISO8859-1"), StandardCharsets.UTF_8);
            if (json.hashCode() == 0){
                resultEntity.setMsg("请求失败");
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                return resultEntity;
            }
            JsonObject jsonStr = new Gson().fromJson(json, JsonObject.class);
            log.info("微信授权errcode， json = {} ", jsonStr);
            if (jsonStr.has("errcode")){
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                resultEntity.setMsg("参数缺失");
                return resultEntity;
            }
            String openid = jsonStr.get("openid").getAsString();
            if (StringUtils.isBlank(openid)){
                resultEntity.setMsg("请求失败");
                resultEntity.setCode(CodeEnum.SUCCESS_NO_FOUND.getCode());
                return resultEntity;
            }
            Map<String,Object> map = new HashMap<>();
            UserExample userExample = new UserExample();
            userExample.or().andIsDelEqualTo(0).andWxGzhOpenidEqualTo(openid);
            List<User> userList = userService.userList(userExample);
            if (userList.size() == 0){
                map.put("openid",openid);
                map.put("type",0);
                resultEntity.setCode(0);
                resultEntity.setData(map);
                return resultEntity;
            }
            if (userList.size() > 1){
                resultEntity.setCode(CodeEnum.SUCCESS_NO_RANGE.getCode());
                resultEntity.setMsg("账号异常，请联系力博得客服");
                resultEntity.setData(null);
                return resultEntity;
            }
            User user = userList.get(0);
            if (StringUtils.isNotBlank(user.getToken())) {
                jedisPoolUtils.del(ReferenceUtil.TOKENKEY+user.getToken());
            }
            String token = TokenUtil.getInstance().generateTokenUUID();
            User userUp = new User();
            userUp.setToken(token);
            userUp.setUpdateTime(new Date());
            userUp.setId(user.getId());
            userService.updateUser(userUp);
            jedisPoolUtils.setex(ReferenceUtil.TOKENKEY+token, ReferenceUtil.SECONDS, String.valueOf(user.getId()));

            map.put("openid",openid);
            map.put("token",token);
            map.put("type",1);
            resultEntity.setCode(CodeEnum.SUCCESS.getCode());
            resultEntity.setData(map);

        }catch (Exception e){
            e.printStackTrace();
            log.error("-----getOpenid()-----");
        }
        return resultEntity;
    }

}
