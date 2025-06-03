package com.max3no.sealog;

import com.max3no.sealog.logging.RequestLoggingFilter;
import com.max3no.sealog.logging.SealogAspect;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import com.max3no.sealog.logging.LogChainService;

/*
 * Sealog Logging Framework
 * Created by Max3no - https://github.com/max3no/sealog-logging
 * Licensed under MIT
 */

@AutoConfiguration
@ConditionalOnProperty(name = "sealog.enabled", havingValue = "true", matchIfMissing = true)
public class SealogAutoConfiguration {

    @Bean(name = "aspectLogger")
    public LogChainService aspectLogChainService() {
        return new LogChainService("aspect-sealog.log");
    }

    @Bean(name = "requestLogger")
    public LogChainService requestLogChainService() {
        return new LogChainService("request-sealog.log");
    }

    @Bean
    @ConditionalOnMissingBean
    @Order(Ordered.HIGHEST_PRECEDENCE + 10)
    public RequestLoggingFilter requestLoggingFilter(@Qualifier("requestLogger") LogChainService logChainService) {
        return new RequestLoggingFilter(logChainService);
    }

    @Bean
    @ConditionalOnMissingBean
    public SealogAspect sealogLogAspect(@Qualifier("aspectLogger") LogChainService logChainService) {
        return new SealogAspect(logChainService);
    }
}

