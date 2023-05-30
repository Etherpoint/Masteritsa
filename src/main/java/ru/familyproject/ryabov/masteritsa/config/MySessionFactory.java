package ru.familyproject.ryabov.masteritsa.config;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.familyproject.ryabov.masteritsa.entity.Comment;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * EN: Class for configuration <b>SessionFactory</b><br>
 * RU: Класс для конфигурации <b>SessionFactory</b>
 * @see SessionFactory
 */
@Configuration
@EnableTransactionManagement
public class MySessionFactory {
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(Comment.class.getPackageName());
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://postgres:5432/family_shop");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("spring.jpa.hibernate.ddl-auto", "none");
        hibernateProperties.setProperty(
                "hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
        return hibernateProperties;
    }
}
