package com.vanseed.rhea.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * JedisConnectionFacotory从Spring Data Redis 2.0开始已经不推荐直接显示设置连接的信息了；
 * 一方面为了使配置信息与建立连接工厂解耦，
 * 另一方面抽象出Standalone,Sentinel和RedisCluster三种模式的环境配置类和一个统一的jedis客户端连接配置类（用于配置连接池和SSL连接），
 * 使得我们可以更加灵活方便根据实际业务场景需要来配置连接信息。
 *
 * @author leon
 * @date 2019/04/17
 */
@Configuration
@PropertySource(value= "classpath:service_rhea.properties")
public class RedisConfig {
    //连接配置
    @Value("${spring.redis.session.database}")
    private int database_session;
    @Value("${spring.redis.session.host}")
    private String host_session;
    @Value("${spring.redis.session.port}")
    private int port_session;
    @Value("${spring.redis.session.password}")
    private String password_session;
    @Value("${spring.redis.session.timeout}")
    private int timeout;

    //连接池配置
    @Value("${spring.redis.pool.max-total}")
    private int maxTotal;
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis.pool.max-wait}")
    private int maxWait;

    //session redis template
    @Bean(name="sessionRedisTemplate")
    public StringRedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

    //session redis 连接工厂
    @Bean(name="sessionConnectionFactory")
    JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        //单机版jedis配置
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host_session);
        redisStandaloneConfiguration.setPort(port_session);
        redisStandaloneConfiguration.setDatabase(database_session);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password_session));

        //获得默认的连接池构造器
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
        //指定jedisPoolConifig来修改默认的连接池构造器
        jpcb.poolConfig(jedisPoolConfig);
        //通过构造器来构造jedis客户端配置
        JedisClientConfiguration jedisClientConfiguration = jpcb.build();
        //单机配置 + 客户端配置 = jedis连接工厂
        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
    }

    //redis连接池配置，公用
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        //最大空闲连接数
        jedisPoolConfig.setMaxIdle(maxIdle);
        //最小空闲连接数
        jedisPoolConfig.setMinIdle(minIdle);
        //当池内没有可用的连接时，最大等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWait);

        return jedisPoolConfig;
    }

}
