package com.vanseed.rhea.domain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * description
 *
 * @author leon
 * @date 2019/04/09
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef="rheaEntityManagerFactory",
        transactionManagerRef="rheaTransactionManager",
        basePackages= {"com.vanseed.rhea.domain.repository"}) //设置Repository所在位置
public class JPAConfig {

    @Autowired
    @Qualifier("rheaDataSource")
    private DataSource rheaDataSource;

    @Bean(name = "rheaEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean rheaEntityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.vanseed.rhea.domain.model");
        factory.setDataSource(rheaDataSource);
        return factory;
    }

    @Bean(name = "rheaTransactionManager")
    public PlatformTransactionManager rheaTransactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
