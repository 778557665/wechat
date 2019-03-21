package com.wengzhoujun.wechat.service.impl;

import com.wengzhoujun.wechat.entity.SysConfig;
import com.wengzhoujun.wechat.repository.SysConfigRepository;
import com.wengzhoujun.wechat.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigRepository sysConfigRepository;

    @Override
    public List<SysConfig> findAll() {
        return sysConfigRepository.findAll();
    }

    @Override
    public SysConfig save(SysConfig sysConfig) {
        return sysConfigRepository.save(sysConfig);
    }
}
