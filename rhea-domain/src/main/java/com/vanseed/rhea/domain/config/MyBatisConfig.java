package com.vanseed.rhea.domain.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * description
 *
 * @author leon
 * @date 2019/04/09
 */
@Configuration
@MapperScan(basePackages = {"com.vanseed.rhea.domain.mybatis"}, sqlSessionTemplateRef = "rheaSqlSessionTemplate")
public class MyBatisConfig {

	@Autowired
	@Qualifier("rheaPageInterceptor")
	PageInterceptor rheaPageInterceptor;

	/*
	 * 重新定义了SqlSessionFactory，需要手动加载配置文件。
	 */
	@Bean(name = "rheaMybatisConfig")
	@ConfigurationProperties(prefix="mybatis.configuration")
	public org.apache.ibatis.session.Configuration rheaMybatisConfig(){
		return new org.apache.ibatis.session.Configuration();
	}

	/*
	 * 自定义SqlSessionFactory（可配置多数据源）
	 */
	@Bean(name = "rheaSqlSessionFactory")
	public SqlSessionFactory rheaSqlSessionFactory(@Qualifier("rheaDataSource") DataSource dataSource, @Qualifier("rheaMybatisConfig") org.apache.ibatis.session.Configuration config) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

		//设置数据源
		bean.setDataSource(dataSource);
		//设置配置项
		bean.setConfiguration(config);
		//重新定义了SqlSessionFactory会导致使用PageHelper.startPage(1,1)无效，需要重新配置pagehelper拦截器插件
		Interceptor[] plugins =  new Interceptor[]{rheaPageInterceptor};
		bean.setPlugins(plugins);
		//添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			bean.setMapperLocations(resolver.getResources("classpath:com/vanseed/rhea/domain/mybatis/*.xml"));
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Bean(name = "rheaSqlSessionTemplate")
	public SqlSessionTemplate rheaSqlSessionTemplate(@Qualifier("rheaSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
		return template;
	}
}
