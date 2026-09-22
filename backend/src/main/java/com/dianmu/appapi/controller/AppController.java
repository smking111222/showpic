package com.dianmu.appapi.controller;

import com.dianmu.appapi.service.AppDataService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 页面数据接口。
 *
 * 返回格式：
 * {
 *   "code": 0,
 *   "message": "ok",
 *   "data": {
 *     "developer": { "avatar": "...", "name": "...", "slogan": "...", "email": "..." },
 *     "sections": [ { "title": "...", "platform": "ios", "apps": [ ... ] } ]
 *   }
 * }
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${app.cors.allowed-origins:*}")   // 前后端分离跨域；生产环境建议改为前端的具体域名
public class AppController {

    private final AppDataService appDataService;

    public AppController(AppDataService appDataService) {
        this.appDataService = appDataService;
    }

    /** 页面所需全部数据：开发者信息 + iOS/Mac 应用列表 */
    @GetMapping("/apps")
    public Map<String, Object> apps() {
        return Map.of(
                "code", 0,
                "message", "ok",
                "data", appDataService.getSiteData()
        );
    }

    /** 健康检查（Render 等平台探活用） */
    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of("status", "UP");
    }
}
