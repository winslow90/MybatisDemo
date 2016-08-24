/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import su90.mybatisdemo.Application;
import su90.mybatisdemo.dao.mapper.RegionsMapper;
import su90.mybatisdemo.dao.domain.Regions;

import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;
import su90.mybatisdemo.DaoConfig;

/**
 * 
 * the same as the one below
 * @SpringBootTest
 * 
 * 
 * @ContextConfiguration(classes = Application.class,initializers = ConfigFileApplicationContextInitializer.class)
 * 
 * 
 * working when the daoconfig contains sqlsessiontemplate and sqlsessionfactory
 * 
 * @ContextConfiguration(classes = DaoConfig.class)
    @TestPropertySource(properties = {
        "oracle.datasource.driver_class_name: oracle.jdbc.OracleDriver",
       "oracle.datasource.url: jdbc:oracle:thin:@192.168.1.101:1521:db01",
       "oracle.datasource.username: mybatisdemo",
       "oracle.datasource.password: mybatisdemopw"
})
 *
 * oracle.datasource.driver_class_name: oracle.jdbc.OracleDriver
   oracle.datasource.url: jdbc:oracle:thin:@192.168.1.101:1521:db01
   oracle.datasource.username: mybatisdemo
   oracle.datasource.password: mybatisdemopw
 * @author superman90
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionsMapperTest {
    
    @Autowired
    RegionsMapper regionsMapper;

    public void setRegionsMapper(RegionsMapper regionsMapper) {
        this.regionsMapper = regionsMapper;
    }
    
    @Test
    public void testFindAll(){
        List<Regions> result = regionsMapper.findAll();
        assertNotNull(result);
    }
    
    @Test
    public void testFindById(){
        Regions result = this.regionsMapper.findById(1L);
        assertEquals(result.getId(), new Long(1L));
        assertEquals(result.getName(), "Europe"); 
    }
    
    @Test
    public void testInsertOneRegions(){
        Regions newregion = new Regions(Long.MIN_VALUE, "Pacific");
        regionsMapper.insertOneRegion(newregion);
        assertNotNull(newregion.getId());
        assertTrue(newregion.getId()>0);
        
        List<Regions> result = regionsMapper.findByName("pacific");
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), "Pacific");
    }
    
    
}
