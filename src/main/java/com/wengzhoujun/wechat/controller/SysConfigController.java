package com.wengzhoujun.wechat.controller;

import com.wengzhoujun.wechat.entity.SysConfig;
import com.wengzhoujun.wechat.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/wechat/sysconfig")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    @GetMapping(value = "/getAll")
    public List<SysConfig> getAllConfig() {
        return sysConfigService.findAll();
    }

    @PostMapping("/save")
    public String save(SysConfig sysConfig) {
        sysConfig.setCreateTime(new Date());
        sysConfigService.save(sysConfig);
        return "success";
    }
}
