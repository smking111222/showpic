package com.dianmu.appapi.model;

/**
 * 单个应用信息
 *
 * @param name     应用名
 * @param image    图标图片地址（相对前端页面的路径或完整 URL）
 * @param icon     图片加载失败时的兜底 emoji
 * @param gradient 图标兜底时的渐变背景色
 * @param desc     应用简介
 * @param url      App Store 链接
 * @param isNew    是否显示 NEW 角标
 */
public record AppInfo(
        String name,
        String image,
        String icon,
        String gradient,
        String desc,
        String url,
        boolean isNew
) {
}
