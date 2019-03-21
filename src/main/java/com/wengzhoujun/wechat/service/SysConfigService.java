package com.wengzhoujun.wechat.service;

import com.wengzhoujun.wechat.entity.SysConfig;

import java.util.List;

public interface SysConfigService {
    List<SysConfig> findAll();

    SysConfig save(SysConfig sysConfig);
}
