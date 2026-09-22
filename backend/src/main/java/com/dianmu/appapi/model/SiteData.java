package com.dianmu.appapi.model;

import java.util.List;

/**
 * 页面完整数据：开发者信息 + 应用分组列表
 */
public record SiteData(
        Developer developer,
        List<Section> sections
) {
}
