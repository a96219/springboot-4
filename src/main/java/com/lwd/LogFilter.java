package com.lwd;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Order(-1)
@Component
public class LogFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(LogFilter.class);

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var responseWrapper = new ContentCachingResponseWrapper(response);

        filterChain.doFilter(request, responseWrapper);

        var characterEncoding = responseWrapper.getCharacterEncoding();
        String responseBody;
        var contentAsByteArray = responseWrapper.getContentAsByteArray();
        if (contentAsByteArray.length == 0) {
            responseBody = null;
        } else {
            responseBody = new String(contentAsByteArray, characterEncoding);
        }

        log.info("charSet:{}, response:{}", characterEncoding, responseBody);

        responseWrapper.copyBodyToResponse();
    }
}
