package com.vanseed.rhea.domain.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * description
 *
 * @author leon
 * @date 2019/04/09
 */
@Configuration
@PropertySource(value= "classpath:domain_rhea.properties")
public class DataSourceConfig {

    @Bean(name = "rheaDataSource")
    @ConfigurationProperties(prefix="rhea.datasource")
    public DataSource rheaDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }


    @Bean(name = "rheaPageInterceptor")
    public PageInterceptor rheaPageInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("reasonable","true");
        //properties.setProperty("dialect","mysql");    //4.0不需要配置mysql数据库的方言
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }
}
