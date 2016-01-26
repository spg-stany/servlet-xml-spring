package ru.project.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableWebMvc
@ComponentScan("ru.project")
//@PropertySource("classpath:/config.properties}")
public class AppConfig {
    /*
    @Value("${url}")
    @Value("${username}")
    @Value("${password}")
    */

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:./db/servlet");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    /*
    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource());
        liquibase.setChangeLog("file:config/liquibase.xml");

        return liquibase;
    }
    */
    /*
    @Bean
    public DataSource dataSource() throws NamingException {
        JndiTemplate jndiTemplate = new JndiTemplate();
        DataSource dataSource = (DataSource) jndiTemplate.lookup("jdbc/ds");

        return dataSource;
    }
    */


}
