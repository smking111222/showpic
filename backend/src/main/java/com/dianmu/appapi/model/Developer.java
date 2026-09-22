package com.dianmu.appapi.model;

import java.util.List;

/**
 * 开发者信息
 */
public record Developer(
        String avatar,
        String name,
        String slogan,
        String email
) {
}
