package com.dianmu.appapi.service;

import com.dianmu.appapi.model.SiteData;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 应用数据服务：从 classpath 下的 apps.json 读取页面数据。
 *
 * 好处：新增 / 修改应用只需要编辑 apps.json，不用动 Java 代码。
 * 注意：apps.json 打包在 jar 内，修改后需重新构建部署
 *      （Render 绑定 GitHub 后，push 代码即自动重新部署）。
 */
@Service
public class AppDataService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private volatile SiteData siteData;

    @PostConstruct
    public void load() {
        try (InputStream in = new ClassPathResource("apps.json").getInputStream()) {
            this.siteData = objectMapper.readValue(in, SiteData.class);
        } catch (Exception e) {
            throw new IllegalStateException("加载 apps.json 失败，请检查文件格式", e);
        }
    }

    public SiteData getSiteData() {
        return siteData;
    }
}
