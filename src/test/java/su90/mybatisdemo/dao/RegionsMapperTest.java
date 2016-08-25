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
import su90.mybatisdemo.dao.domain.Region;

import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
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
        List<Region> result = regionsMapper.findAll();
        assertNotNull(result);
    }
    
    @Test
    public void testFindById(){
        Region result = this.regionsMapper.findById(1L);
        assertEquals(result.getId(), new Long(1L));
        assertEquals(result.getName(), "Europe"); 
    }
    
    @Test
    public void testInsertUpdateDelete(){
        testInsertOneRegions();
        testInsertAnotherRegions();
        testUpdateOneRegion();
        testUpdateOneRegionwithEmpty();
        testUpdateOneRegionwithNull();
        testDeletebyId();
        testDeletebyRegionId();
    }
    
//    @Test
    public void testInsertOneRegions(){
        Region newregion = new Region(Long.MIN_VALUE, "Pacific");
        regionsMapper.insertOneRegion(newregion);
        assertNotNull(newregion.getId());
        assertTrue(newregion.getId()>0);
        
        List<Region> result = regionsMapper.findByName("pacific");
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), "Pacific");
    }
    
//    @Test
    public void testInsertAnotherRegions(){
        Region newregion = new Region(Long.MIN_VALUE, "Artic");
        regionsMapper.insertOneRegion(newregion);
        assertNotNull(newregion.getId());
        assertTrue(newregion.getId()>0);
        
        List<Region> result = regionsMapper.findByName("artic");
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), "Artic");
    }
    
//    @Test
    public void testUpdateOneRegion(){
        Region pacificregion = regionsMapper.findByName("Pacific").get(0);
        pacificregion.setName("Pacific01");
        regionsMapper.updateOneRegions(pacificregion);
    }
    
//    @Test
    public void testUpdateOneRegionwithEmpty(){
        Region pacificregion = regionsMapper.findByName("Pacific01").get(0);
        pacificregion.setName("");
        regionsMapper.updateOneRegions(pacificregion);
    }
    
//    @Test
    public void testUpdateOneRegionwithNull(){
        Region pacificregion = regionsMapper.findByName("Pacific01").get(0);
        pacificregion.setName(null);
        regionsMapper.updateOneRegions(pacificregion);
    }
    
//    @Test
    public void testDeletebyId(){
        Region tobedeletedregion = regionsMapper.findByName("Pacific01").get(0);
        regionsMapper.deleteById(tobedeletedregion.getId());
        assertEquals(regionsMapper.findByName("Pacific01").size(), 0);
    }
    
    
//    @Test
    public void testDeletebyRegionId(){
        Region tobedeletedregion = regionsMapper.findByName("artic").get(0);
        regionsMapper.deleteByRegionId(tobedeletedregion);
        assertEquals(regionsMapper.findByName("artic").size(), 0);
    } 
    
}
