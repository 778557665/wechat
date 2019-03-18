package com.wengzhoujun.wechat.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static Boolean isRequestSuccessful(HttpResponse httpresponse) {
        return httpresponse.getStatusLine().getStatusCode() == 200;
    }

    public static String HttpGet(Map<String, String> personMap, String url) throws Exception {
        StringBuilder urlBuilder = new StringBuilder(url);
        String equal = "=";
        String and = "&";
        for (Map.Entry<String, String> entry : personMap.entrySet()) {
            urlBuilder.append(entry.getKey()).append(equal).append(entry.getValue()).append(and);
        }
        url = urlBuilder.deleteCharAt(urlBuilder.lastIndexOf(and)).toString();
        HttpGet httpGet = new HttpGet(url);
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpresponse = null;
        try {
            httpresponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpresponse.getEntity();
            String response = EntityUtils.toString(httpEntity, "utf-8");
            return response;
        } catch (IOException e) {
            logger.info("http请求失败,url=" + url);
            e.printStackTrace();
        }
        return null;
    }
}
