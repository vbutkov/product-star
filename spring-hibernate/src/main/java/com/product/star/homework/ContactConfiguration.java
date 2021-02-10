package com.product.star.homework;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.schema.Action;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        var configuration = new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(Contact.class)
                .configure();
        return configuration.buildSessionFactory();
    }

    @Bean
    public ContactDao contactDao() {
        return new ContactDao(sessionFactory());
    }
}
