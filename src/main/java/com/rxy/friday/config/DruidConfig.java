package com.rxy.friday.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author rxy
 * @date 2020/2/20  14:34  星期四
 **/
@Configuration
public class DruidConfig {
    /***
     * 将配置添加到容器中
     * @Author: rxy
     * @Param: []
     * @return: {@link DataSource}
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource(){
        return new DruidDataSource();
    }
}
