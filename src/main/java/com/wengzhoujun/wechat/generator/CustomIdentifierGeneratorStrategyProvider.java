package com.wengzhoujun.wechat.generator;

import org.hibernate.jpa.spi.IdentifierGeneratorStrategyProvider;

import java.util.HashMap;
import java.util.Map;

public class CustomIdentifierGeneratorStrategyProvider implements IdentifierGeneratorStrategyProvider {
    @Override
    public Map<String, Class<?>> getStrategies() {
        Map<String, Class<?>> map = new HashMap<>();
        map.put("redis_id", HibernateRedisIdGenerator.class);
        return map;
    }
}
