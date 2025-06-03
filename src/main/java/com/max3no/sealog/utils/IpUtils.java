package com.max3no.sealog.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/*
 * Sealog Logging Framework
 * Created by Max3no - https://github.com/max3no/sealog-logging
 * Licensed under MIT
 */

public class IpUtils {

    public static String getClientIp() {
        try {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                String forwardedFor = request.getHeader("X-Forwarded-For");
                if (forwardedFor != null && !forwardedFor.isEmpty()) {
                    return forwardedFor.split(",")[0].trim(); // First IP in chain
                }
                return request.getRemoteAddr();
            }
        } catch (Exception e) {
            // Handle or ignore
        }
        return "unknown-ip";
    }
}
