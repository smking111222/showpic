package com.dianmu.appapi.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 主动声明 Content-Disposition: inline。
 *
 * 背景：阿里云 FC 网关对未声明 Content-Disposition 的响应会追加
 * "Content-Disposition: attachment"，导致浏览器访问 index.html 时
 * 变成下载文件而不是渲染页面。显式声明 inline 后 FC 不再干预。
 */
@Component
public class InlineDispositionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (response instanceof HttpServletResponse httpResponse) {
            httpResponse.setHeader("Content-Disposition", "inline");
        }
        chain.doFilter(request, response);
    }
}
