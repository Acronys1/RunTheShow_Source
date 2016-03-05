/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runtheshow.frontend.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author maxim
 */
@EnableJpaRepositories(basePackages = {"runtheshow.frontend.security"})
@EnableAutoConfiguration
@ComponentScan(basePackages = {"runtheshow.frontend"})
@EntityScan(basePackages = {"runtheshow.frontend.entities", "runtheshow.frontend.security"})
@EnableTransactionManagement
public class AppConfiguration {

    // la source de données MySQL
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/runtheshow");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("Run$The$Show");
        return driverManagerDataSource;
    }

    // le provider JPA - n'est pas nécessaire si on est satisfait des valeurs par
    // défaut utilisées par Spring boot
    // ici on le définit pour activer / désactiver les logs SQL
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
        return hibernateJpaVendorAdapter;
    }

    /*@Bean
    public EntityManagerFactory entityManagerFactory() {

        Properties props = new Properties();
        props.put("hibernate.hbm2ddl.auto", "create");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(getClass().getPackage().getName());
        factory.setJpaProperties(props);

        factory.afterPropertiesSet();

        return factory.getObject();
    }*/
    // l'EntityManageFactory et le TransactionManager sont définis avec des
    // valeurs par défaut par Spring boot
}
