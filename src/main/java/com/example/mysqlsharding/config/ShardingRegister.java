package com.example.mysqlsharding.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.mysqlsharding.mapper.DBModelMappr;
import com.example.mysqlsharding.model.DBModel;
import com.example.mysqlsharding.util.BeanDefinitionRegistrarUtil;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ShardingRegister  implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DBModelMappr dbModelMappr;

    private final MybatisProperties properties;

    private final Interceptor[] interceptors;

    private final TypeHandler[] typeHandlers;

    private final LanguageDriver[] languageDrivers;

    private final ResourceLoader resourceLoader;

    private final DatabaseIdProvider databaseIdProvider;

    private final List<ConfigurationCustomizer> configurationCustomizers;

    public ShardingRegister(MybatisProperties properties, ObjectProvider<Interceptor[]> interceptorsProvider,
                                    ObjectProvider<TypeHandler[]> typeHandlersProvider, ObjectProvider<LanguageDriver[]> languageDriversProvider,
                                    ResourceLoader resourceLoader, ObjectProvider<DatabaseIdProvider> databaseIdProvider,
                                    ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
        this.properties = properties;
        this.interceptors = interceptorsProvider.getIfAvailable();
        this.typeHandlers = typeHandlersProvider.getIfAvailable();
        this.languageDrivers = languageDriversProvider.getIfAvailable();
        this.resourceLoader = resourceLoader;
        this.databaseIdProvider = databaseIdProvider.getIfAvailable();
        this.configurationCustomizers = configurationCustomizersProvider.getIfAvailable();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try{
            //获取数据源集合
            List<DBModel> dbModelList = dbModelMappr.list();
            Iterator<DBModel> dbModelIterator = dbModelList.iterator();
            //循环集合注册
            while(dbModelIterator.hasNext()) {
                //获取数据源实例
                DBModel dbModel = dbModelIterator.next();
                //创建数据源
                DruidDataSource druidDataSource = new DruidDataSource();
                druidDataSource.setDriverClassName(dbModel.getDriver());
                druidDataSource.setUrl(dbModel.getUrl());
                druidDataSource.setUsername(dbModel.getUsername());
                druidDataSource.setPassword(dbModel.getPassword());
                //创建sqlSessionFactory
                SqlSessionFactory newSqlSessionFactory = sqlSessionFactory(druidDataSource);
                //驼峰
                newSqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(properties.getConfiguration().isMapUnderscoreToCamelCase());
                //创建sqlsessionTempleate对象  这个对象可以帮我很好的管理sqlsession 的线程问题 和关闭问题
                //sqlsessionTempleate 我们这里如果要注册到spring 我们只有动态去注册
                BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(SqlSessionTemplate.class);
                //这里sqlsessionTempleate 创建需要构造方法传递SqlSessionFactory 对象
                beanDefinitionBuilder.addConstructorArgValue(newSqlSessionFactory);
                //注册到spring
                BeanDefinitionRegistrarUtil.registerBeanDefinition(dbModel.getBeanName(), beanDefinitionBuilder.getBeanDefinition());
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("--------多数据源配置出现异常------");
        }
    }


    private SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        if (StringUtils.hasText(this.properties.getConfigLocation())) {
            factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
        }
        //applyConfiguration(factory);
        if (this.properties.getConfigurationProperties() != null) {
            factory.setConfigurationProperties(this.properties.getConfigurationProperties());
        }
        if (!ObjectUtils.isEmpty(this.interceptors)) {
            factory.setPlugins(this.interceptors);
        }
        if (this.databaseIdProvider != null) {
            factory.setDatabaseIdProvider(this.databaseIdProvider);
        }
        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (this.properties.getTypeAliasesSuperType() != null) {
            factory.setTypeAliasesSuperType(this.properties.getTypeAliasesSuperType());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.typeHandlers)) {
            factory.setTypeHandlers(this.typeHandlers);
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            factory.setMapperLocations(this.properties.resolveMapperLocations());
        }
        Set<String> factoryPropertyNames = Stream
                .of(new BeanWrapperImpl(SqlSessionFactoryBean.class).getPropertyDescriptors()).map(PropertyDescriptor::getName)
                .collect(Collectors.toSet());
        Class<? extends LanguageDriver> defaultLanguageDriver = this.properties.getDefaultScriptingLanguageDriver();
        if (factoryPropertyNames.contains("scriptingLanguageDrivers") && !ObjectUtils.isEmpty(this.languageDrivers)) {
            // Need to mybatis-spring 2.0.2+
            factory.setScriptingLanguageDrivers(this.languageDrivers);
            if (defaultLanguageDriver == null && this.languageDrivers.length == 1) {
                defaultLanguageDriver = this.languageDrivers[0].getClass();
            }
        }
        if (factoryPropertyNames.contains("defaultScriptingLanguageDriver")) {
            // Need to mybatis-spring 2.0.2+
            factory.setDefaultScriptingLanguageDriver(defaultLanguageDriver);
        }
        //重构配置
        factory.afterPropertiesSet();
        return factory.getObject();
    }

//    private void applyConfiguration(SqlSessionFactoryBean factory) {
//        Configuration configuration = this.properties.getConfiguration();
//        if (configuration == null && !StringUtils.hasText(this.properties.getConfigLocation())) {
//            configuration = new Configuration();
//        }
//        if (configuration != null && !CollectionUtils.isEmpty(this.configurationCustomizers)) {
//            for (ConfigurationCustomizer customizer : this.configurationCustomizers) {
//                customizer.customize(configuration);
//            }
//        }
//        factory.setConfiguration(configuration);
//    }
}
