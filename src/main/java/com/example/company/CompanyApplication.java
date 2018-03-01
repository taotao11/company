package com.example.company;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 *
 * 启动类
 */
//开始扫描Mapper包
@MapperScan("com.example.company.mapper")
@SpringBootApplication
public class CompanyApplication {
	//配置mybatis的分页插件pageHelper
	@Bean
	public PageHelper pageHelper(){
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum","true");
		properties.setProperty("rowBoundsWithCount","true");
		properties.setProperty("reasonable","true");
		properties.setProperty("dialect","mysql");    //配置mysql数据库的方言
		pageHelper.setProperties(properties);
		return pageHelper;
	}
	public static void main(String[] args) {
		SpringApplication.run(CompanyApplication.class, args);
	}
}
