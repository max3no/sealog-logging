package com.max3no.sealog.logging;

import com.max3no.sealog.utils.IpUtils;
import com.max3no.sealog.utils.SystemUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.time.Instant;

import static com.max3no.sealog.utils.IpUtils.getClientIp;
import static com.max3no.sealog.utils.SystemUtils.getHostName;

public class RequestLoggingFilter extends OncePerRequestFilter {

    private final LogChainService logChainService;

    public RequestLoggingFilter(@Qualifier("requestLogger") LogChainService logChainService) {
        this.logChainService = logChainService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        String method = request.getMethod();
        String logMessage = method + " " + path;
        String ip = getClientIp();
        String host = getHostName();

        logChainService.log(
                Instant.now().toString(),       // timestamp
                "RequestLoggingFilter",         // source
                logMessage,                     // message
                ip,                             // ip
                host                            // host
        );

        filterChain.doFilter(request, response);
    }
}

