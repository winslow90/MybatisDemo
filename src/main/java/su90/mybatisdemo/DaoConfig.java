/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo;

import java.sql.SQLException;
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
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author superman90
 */
@Configuration
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
    
    @Bean
    TransactionFactory transactionFactory(){
        return new JdbcTransactionFactory();
    }
    
    @Bean
    Environment environment(){
        return new Environment("development",transactionFactory(),dataSource());
    }
    
    @Bean(name="ibatis_configuration")
    org.apache.ibatis.session.Configuration configuration(){
        org.apache.ibatis.session.Configuration result = new org.apache.ibatis.session.Configuration(environment());
        result.addMappers("su90.mybatisdemo.dao.mappers");
        return result;
    }
    
    @Bean
    SqlSessionFactory sqlSessionFactory(){
        SqlSessionFactory result = new SqlSessionFactoryBuilder().build(configuration());
        return result;
    }
        
}
