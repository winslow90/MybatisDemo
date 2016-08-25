/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import oracle.jdbc.pool.OracleDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 *
 * @author superman90
 */
@Configuration
//@MapperScan(basePackages = "su90.mybatisdemo.dao.mapper")
@ConfigurationProperties("oracle.datasource")
public class DaoConfig {
    
    @NotNull
    private String driver_class_name;
    @NotNull
    private String url;
    @NotNull
    private String username;
    @NotNull
    private String password;

    public void setDriver_class_name(String driver_class_name) {
        this.driver_class_name = driver_class_name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Bean
    DataSource dataSource(){
        OracleDataSource datasource;
        try {
            datasource = new OracleDataSource();
            datasource.setURL(url);
            datasource.setUser(username);
            datasource.setPassword(password);

            return datasource;
        } catch (SQLException ex) {
            Logger.getLogger(DaoConfig.class.getName()).log(Level.SEVERE, null, ex);          
        }
        return null;
    }
    
//    @Bean
//    TransactionFactory transactionFactory(){
//        return new JdbcTransactionFactory();
//    }
    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
    
    @Bean
    Environment mybatisEnvironment(){
        return new Environment("development", new ManagedTransactionFactory(),dataSource());
    }
    
    @Bean(name="ibatis_configuration")
    org.apache.ibatis.session.Configuration mybatisConfiguration(){
        org.apache.ibatis.session.Configuration conf = new org.apache.ibatis.session.Configuration(mybatisEnvironment());
        conf.addMappers("su90.mybatisdemo.dao.mappers");
//        conf.getLazyLoadTriggerMethods().clear();
//        conf.setCacheEnabled(true);
//        conf.setLazyLoadingEnabled(true);
//        conf.setAggressiveLazyLoading(true);
//        conf.setLazyLoadTriggerMethods(new HashSet<>(Arrays.asList(new String[] { "equals", "clone", "hashCode", "toString" })));
//        conf.setUseColumnLabel(true);
//        conf.setMultipleResultSetsEnabled(true);
        return conf;
    }
    
    @Bean
    SqlSessionFactory sqlSessionFactory(){
        SqlSessionFactory result = new SqlSessionFactoryBuilder().build(mybatisConfiguration());
        return result;
    }
    
    //never froget to close the session
//    SqlSession session = sqlSessionFactory.openSession();
//    try {
//    // do work
//    } finally {
//    session.close();
//    }
        
}
