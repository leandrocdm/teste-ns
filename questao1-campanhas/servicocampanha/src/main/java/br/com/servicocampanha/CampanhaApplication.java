package br.com.servicocampanha;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class CampanhaApplication {
	public static void main(String[] args) {
		SpringApplication.run(CampanhaApplication.class, args);
	}
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();		
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:file:~/test");
		dataSource.setUsername("sa");
		dataSource.setPassword("");		
		return dataSource;
	}
}
