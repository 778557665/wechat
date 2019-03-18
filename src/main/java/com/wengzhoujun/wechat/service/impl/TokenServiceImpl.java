package com.wengzhoujun.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wengzhoujun.wechat.service.TokenService;
import com.wengzhoujun.wechat.util.HttpUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TokenServiceImpl implements TokenService {

    private final String GRANT_TYPE = "client_credential";

    private final String APPID = "wxe05a666920824122";

    private final String SECRET = "d251fe07d4bc399c7d98ead885ffb46e";

    private ValueOperations<String, String> wechatTokenConfigOperations;

    public TokenServiceImpl(StringRedisTemplate oneRedisTemplate) {
        oneRedisTemplate.afterPropertiesSet();
        this.wechatTokenConfigOperations = oneRedisTemplate.opsForValue();
    }

    @Override
    public String getToken() throws Exception {
        String cacheKey = "WechatToken";
        String token = wechatTokenConfigOperations.get(cacheKey);
        if(null != token){
            return token;
        }
        String url = "https://api.weixin.qq.com/cgi-bin/token?";
        Map<String, String> param = new HashMap<>(4);
        param.put("grant_type", GRANT_TYPE);
        param.put("appid", APPID);
        param.put("secret", SECRET);
        String response = HttpUtil.HttpGet(param, url);
        JSONObject jsonObject = JSONObject.parseObject(response);
        if(null != jsonObject){
            token = jsonObject.getString("access_token");
            Long expires = jsonObject.getLong("expires_in");
            if(null != token){
                wechatTokenConfigOperations.set(cacheKey, token, expires, TimeUnit.SECONDS);
                return token;
            }
        }
        return null;
    }
}
