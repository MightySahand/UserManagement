package com.example.UserManagementProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class UserManagementProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManagementProjectApplication.class, args);
    }

    // An application context delegates the message resolution to a bean with exact bean name "messageSource"
    @Bean
    public MessageSource messageSource() {
        // ReloadableResourceBundleMessageSource is the most common implementation of MessageSource that resolves
        // messages from resource bundles from different locales.
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        // It's important to provide the basename as locale-specific file names will be resolved based on the name provided.
        messageSource.setBasename("classpath:Messages:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
