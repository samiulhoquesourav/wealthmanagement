/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2019
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.buildit.wealth.management.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class HibernateConfiguration {

    @Autowired
    private Environment env;

    @Autowired
    private GlobalConfiguration globalConfiguration;

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        final LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        factoryBean.setDataSource(getDataSource());
        factoryBean.setPackagesToScan("com.buildit.weatlth.management.entity");

        final Properties props = new Properties();

        // Setting Hibernate properties
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        //props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.put("hibernate.jdbc.lob.non_contextual_creation", env.getProperty("hibernate.jdbc.lob.non_contextual_creation"));

        // Setting C3P0 properties
        props.put("hibernate.c3p0.min_size", env.getProperty("hibernate.c3p0.min_size"));
        props.put("hibernate.c3p0.max_size", env.getProperty("hibernate.c3p0.max_size"));
        props.put("hibernate.c3p0.acquire_increment", env.getProperty("hibernate.c3p0.acquire_increment"));
        props.put("hibernate.c3p0.timeout", env.getProperty("hibernate.c3p0.timeout"));
        props.put("hibernate.c3p0.max_statements", env.getProperty("hibernate.c3p0.max_statements"));

        factoryBean.setHibernateProperties(props);

        return factoryBean;
    }

    @Bean
    public DataSource getDataSource() {

        System.out.println("####db url::" + globalConfiguration.getDbUrl());

        return DataSourceBuilder.create().username(globalConfiguration.getDbUserName()).password(globalConfiguration.getDbPassword())
                .url(globalConfiguration.getDbUrl()).driverClassName(globalConfiguration.getDbDriverClass()).build();
    }

    @Bean
    public HibernateTransactionManager getTransactionManager() {
        final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }

}
