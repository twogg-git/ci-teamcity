package javatest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@EnableAsync
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "javatest.rest"})
@PropertySource(value = {"classpath:application.properties"})
public class ApiConfig {

    @Autowired
    private Environment env;

}
