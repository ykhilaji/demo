package com.coliship.demo.realTime.task.converter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.dialect.PostgresDialect;

import java.util.List;
import java.util.Arrays;

@Configuration
public class R2dbcConfig {

    @Bean
    public R2dbcCustomConversions r2dbcCustomConversions() {
        List<?> converters = Arrays.asList(
                new JsonNodeReadConverter(),
                new JsonNodeWriteConverter()
        );
        return R2dbcCustomConversions.of(PostgresDialect.INSTANCE, converters);
    }
}