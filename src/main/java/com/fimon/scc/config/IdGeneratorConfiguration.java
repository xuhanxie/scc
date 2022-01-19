package com.fimon.scc.config;

import com.fimon.scc.utils.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IdGeneratorConfiguration {

    @Bean
    public IdGenerator idGenerator() {
        return new IdGenerator(0L, 0L);
    }

}
