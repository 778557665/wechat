package com.wengzhoujun.wechat.scheduler;

import com.wengzhoujun.wechat.entity.MailInfo;
import com.wengzhoujun.wechat.util.MailUtil;
import com.wengzhoujun.wechat.util.WeatherUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class WeatherScheduler {

    private static final Logger logger = LoggerFactory.getLogger(WeatherScheduler.class);

    @PostConstruct
    private void sendEmail() {
        logger.info("-----------sendEmail-running-----------");
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Calendar calendar = Calendar.getInstance();
        Integer initialDelay = 32 * 3600 - (calendar.get(Calendar.HOUR_OF_DAY) * 3600 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND));
        executor.scheduleAtFixedRate(() -> {
            try {
                MailInfo mailInfo = new MailInfo();
                List<String> toList = new ArrayList<>();
                toList.add("624188642@qq.com");
                mailInfo.setToAddress(toList);
                Calendar now = Calendar.getInstance();
                mailInfo.setSubject(now.get(Calendar.YEAR) + "年"+ now.get(Calendar.MONTH) + "月" + now.get(Calendar.DAY_OF_MONTH) + "日天气");
                String content = WeatherUtil.getWeatherByCityCode("101210101");
                mailInfo.setContent(content);
                MailUtil.sendEmail(mailInfo);
                logger.info("-----------sendEmail success!-----------");
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("-----------sendEmail success!-----------");
        }, initialDelay, 24 * 3600, TimeUnit.SECONDS);
    }

}
