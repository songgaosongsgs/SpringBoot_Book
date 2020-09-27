package com.bookstore.work.config;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

import cn.hutool.core.util.StrUtil;

@Configuration
@MapperScan("com.heyimed.spd.*.dao")
public class MybatisPlusConfig {

    @Value("${spring.datasource.charset:#{null}}")
    private String charset;
    
    /**
     * mybatis-plus分页插件<br>
     * 文档：http://mp.baomidou.com<br>
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
	
    @Bean
    public BeanCodingConvertInterceptor beanCodingConvertInterceptor(){
    	BeanCodingConvertInterceptor beanCodingConvertInterceptor = new BeanCodingConvertInterceptor();
    	if(StrUtil.isNotBlank(charset)){
    		Properties properties = new Properties();
    		properties.setProperty("charset", charset);
    		beanCodingConvertInterceptor.setProperties(properties);
    	}
    	return beanCodingConvertInterceptor;
    }
}
