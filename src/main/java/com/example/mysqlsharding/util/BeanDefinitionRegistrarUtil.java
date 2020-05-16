package com.example.mysqlsharding.util;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class BeanDefinitionRegistrarUtil implements ImportBeanDefinitionRegistrar {

    private static BeanDefinitionRegistry beanDefinitionRegistry;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        this.beanDefinitionRegistry = registry;
    }

    public static void registerBeanDefinition(String beanName, BeanDefinition beanDefinition){
        beanDefinitionRegistry.registerBeanDefinition(beanName,beanDefinition);
    }
}
