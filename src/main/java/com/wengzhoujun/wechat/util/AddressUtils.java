package com.wengzhoujun.wechat.util;

import net.ipip.ipdb.City;
import net.ipip.ipdb.CityInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class AddressUtils {

    private static Logger logger = LoggerFactory.getLogger(AddressUtils.class);

    public static String getRealAddress(String ip){
        try {
            // City类可用于IPDB格式的IPv4免费库，IPv4与IPv6的每周高级版、每日标准版、每日高级版、每日专业版、每日旗舰版
            City db = new City("E:\\ipiptest.ipdb");
            // db.find(address, language) 返回索引数组
            logger.info(Arrays.toString(db.find(ip, "CN")));
            // db.findInfo(address, language) 返回 CityInfo 对象
            CityInfo info = db.findInfo(ip, "CN");
            return info.getCountryName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
