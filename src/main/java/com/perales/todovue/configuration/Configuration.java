package com.perales.todovue.configuration;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configurable
@ComponentScan("com.perales.todovue")
@EnableTransactionManagement
@EnableJpaRepositories("com.perales.todovue.repository")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
public class Configuration {

    @Bean(name = "dataSource")
    public DataSource initDataSource() {
        InitialContext ic;
        DataSource dataSource = null;
        try {
            ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/todovue");                    
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return dataSource;
    }    
    
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean initEntityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        em.setPersistenceUnitName("todovue");
        em.setPackagesToScan("com.perales.todovue.model");
        em.setDataSource(initDataSource());
        em.setJpaVendorAdapter(jpaVendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }    

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
       JpaTransactionManager transactionManager = new JpaTransactionManager();
       transactionManager.setEntityManagerFactory(emf);
       return transactionManager;
    }
    
    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
     }    
}
