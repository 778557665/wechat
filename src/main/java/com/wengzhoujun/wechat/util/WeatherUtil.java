package com.wengzhoujun.wechat.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

public class WeatherUtil {

    public static String getWeatherByCityCode(String cityCode) throws Exception {
        String url = "http://t.weather.sojson.com/api/weather/city/".concat(cityCode);
        String response = HttpUtil.httpGet(url);
        if (StringUtils.isNotBlank(response)) {
            JSONObject jsonObject = JSONObject.parseObject(response);
            StringBuilder stringBuilder = new StringBuilder("齐霸天，");

            if (null != jsonObject) {
                JSONObject data = jsonObject.getJSONObject("data");
                if (null != data) {
                    JSONArray forecast = data.getJSONArray("forecast");
                    JSONObject today = (JSONObject) forecast.get(0);
                    if (null != today) {
                        StringBuilder max = new StringBuilder("，最高：");
                        StringBuilder min = new StringBuilder("，最低：");
                        String notice = today.getString("notice");
                        String high = today.getString("high");
                        String low = today.getString("low");
                        stringBuilder.append("今天").append(today.getString("type")).append(",").append(notice)
                                .append(max).append(high.replace("高温 ", ""))
                                .append(min).append(low.replace("低温 ", ""));
                        String br = "<br>";
                        stringBuilder.append(br).append("下面老追为您准备详细的天气信息哦~").append(br).
                                append("今天pm2.5指数:").append(today.getString("aqi")).append(br)
                                .append("空气质量:").append(data.getString("quality")).append(br)
                                .append("风向:").append(today.getString("fx")).append(br)
                                .append("风速:").append(today.getString("fl")).append(br);
                    }
                }
            }
            return stringBuilder.toString();
        }
        return null;
    }
}
