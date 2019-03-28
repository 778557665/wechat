package com.wengzhoujun.wechat.scheduler;

import com.alibaba.fastjson.JSONObject;
import com.wengzhoujun.wechat.entity.MailInfo;
import com.wengzhoujun.wechat.enums.SubjectEnum;
import com.wengzhoujun.wechat.util.HttpUtil;
import com.wengzhoujun.wechat.util.MailUtil;
import com.wengzhoujun.wechat.util.WeatherUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
public class WeatherScheduler {

    private static final Logger logger = LoggerFactory.getLogger(WeatherScheduler.class);

    @Scheduled(cron = "0 0 8 * * ? ")
    private void sendEmail() {
        try {
            MailInfo mailInfo = new MailInfo();
            List<String> toList = new ArrayList<>();
            toList.add("624188642@qq.com");
            toList.add("778557665@qq.com");
            mailInfo.setToAddress(toList);
            Calendar now = Calendar.getInstance();
            int week = now.get(Calendar.DAY_OF_WEEK);
            String subject = returnSubjectByDay(week);
            String txt = getRandomString();
            subject = subject.concat("，").concat(txt);
            if(subject.length() > 70){
                subject = subject.substring(0, 65);
                subject.concat("..");
            }
            mailInfo.setSubject(subject);
            String content = WeatherUtil.getWeatherByCityCode("101210101");
            content = content.concat(txt);
            mailInfo.setContent(content);
            MailUtil.sendEmail(mailInfo);
            logger.info("-----------sendEmail success!-----------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String returnSubjectByDay(int code){
        SubjectEnum subjectEnum = SubjectEnum.getEnumByCode(code);
        return subjectEnum.getValue();
    }

    private static String getRandomString() throws Exception {
        String response = HttpUtil.httpGet("https://api.xygeng.cn/dailywd/api/");
        if(StringUtils.isBlank(response)){
            return "";
        }
        JSONObject jsonObject = JSONObject.parseObject(response);
        String txt = jsonObject.getString("txt");
        return txt;
    }
}
