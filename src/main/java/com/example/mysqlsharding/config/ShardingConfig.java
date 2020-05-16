package com.example.mysqlsharding.config;

import com.example.mysqlsharding.util.BeanDefinitionRegistrarUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(BeanDefinitionRegistrarUtil.class)
@Configuration
public class ShardingConfig {
}
