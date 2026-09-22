package com.dianmu.appapi.model;

import java.util.List;

/**
 * 应用分组（如：iOS 应用 / Mac 应用）
 */
public record Section(
        String title,
        String platform,
        List<AppInfo> apps
) {
}
