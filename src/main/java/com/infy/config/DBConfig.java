package com.infy.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan(basePackages = "com.infy")
public class DBConfig {
	
	@Bean 
	public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
	
		@Bean
		public DataSource dataSource() {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			//MySQL database we are using
			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dataSource.setUrl("jdbc:mysql://localhost:3306/world");
			//dataSource.setUrl("jdbc:mysql://localhost:3306/world?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT");
			//dataSource.setUrl("jdbc:mysql://localhost:3306/world?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT");
			dataSource.setUsername("root");
			dataSource.setPassword("thoya");
			return dataSource;
		}

		@Bean
		public JdbcTemplate jdbcTemplate() {
			JdbcTemplate jdbcTemplate = new JdbcTemplate();
			jdbcTemplate.setResultsMapCaseInsensitive(true);
			jdbcTemplate.setDataSource(dataSource());
			return jdbcTemplate;
		}
		
		
	
}