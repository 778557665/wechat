package com.wengzhoujun.wechat.scheduler;

import com.wengzhoujun.wechat.entity.MailInfo;
import com.wengzhoujun.wechat.util.MailUtil;
import com.wengzhoujun.wechat.util.WeatherUtil;
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
            mailInfo.setToAddress(toList);//收件人
            Calendar calendar = Calendar.getInstance();
            mailInfo.setSubject(calendar.get(Calendar.YEAR) + "年"+ calendar.get(Calendar.MONTH) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日天气");
            String content = WeatherUtil.getWeatherByCityCode("101210101");
            mailInfo.setContent(content);
            MailUtil.sendEmail(mailInfo);
            logger.info("-----------sendEmail success!-----------");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
