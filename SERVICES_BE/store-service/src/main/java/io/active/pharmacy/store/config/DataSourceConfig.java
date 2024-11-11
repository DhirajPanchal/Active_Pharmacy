package io.active.pharmacy.store.config;


import io.netty.util.internal.StringUtil;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.ConnectionFactoryOptions.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.r2dbc.spi.ConnectionFactoryOptions.PASSWORD;
import static io.r2dbc.spi.ConnectionFactoryOptions.USER;

//
//
// WIP : NOT USED YET
//
//

@Configuration
public class DataSourceConfig {

    @Bean
    public ConnectionFactory connectionFactory(R2DBCConfigurationProperties properties) {

        ConnectionFactoryOptions baseOptions = ConnectionFactoryOptions.parse(properties.getUrl());
        Builder ob = ConnectionFactoryOptions.builder().from(baseOptions);
        if (!StringUtil.isNullOrEmpty(properties.getUsername())) {
            ob = ob.option(USER, properties.getUsername());
        }

        if (!StringUtil.isNullOrEmpty(properties.getPassword())) {
            ob = ob.option(PASSWORD, properties.getPassword());
        }

        return ConnectionFactories.get(ob.build());
    }
}
