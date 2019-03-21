package com.wengzhoujun.wechat.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class HibernateRedisIdGenerator implements IdentifierGenerator, ApplicationContextAware {

    private static IObjectIdGenerator idGenerator;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        idGenerator = applicationContext.getBean("redisIdGenerator", IObjectIdGenerator.class);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        String simpleName = object.getClass().getSimpleName();
        long l = idGenerator.generateId(simpleName);
        System.out.println(simpleName + "主键:  " + l);
        return l;
    }
}
