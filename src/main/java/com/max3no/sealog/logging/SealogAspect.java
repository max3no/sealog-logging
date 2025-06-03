package com.max3no.sealog.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static com.max3no.sealog.utils.IpUtils.getClientIp;
import static com.max3no.sealog.utils.SystemUtils.getHostName;

@Aspect
@Component
@Order(1)
public class SealogAspect {

    private final LogChainService logChainService;

    public SealogAspect(@Qualifier("aspectLogger") LogChainService logChainService) {
        this.logChainService = logChainService;
    }

    @Before("@annotation(sealog)")
    public void logAnnotatedMethod(JoinPoint joinPoint, Sealog sealog) {
        String method = joinPoint.getSignature().toShortString();
        String message = sealog.value().isEmpty() ? "Invoked: " + method : sealog.value();
        String ip = getClientIp();
        String host = getHostName();

        logChainService.log(
                Instant.now().toString(),       // timestamp
                "SealogAspect",                 // source
                message,                         // message
                ip,                             // ip
                host                            // host
        );
    }
}

