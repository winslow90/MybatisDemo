/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import su90.mybatisdemo.Application;
import su90.mybatisdemo.dao.mapper.RegionsMapper;
import su90.mybatisdemo.dao.domain.Region;

import static org.testng.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;
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
//@RunWith(SpringRunner.class)
//AbstractTransactionalTestNGSpringContextTests        not working?????????  use AbstractTestNGSpringContextTests and @Transactional instead
@SpringBootTest
public class RegionsMapperTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    RegionsMapper regionsMapper;

    public void setRegionsMapper(RegionsMapper regionsMapper) {
        this.regionsMapper = regionsMapper;
    }
    
    @Test(groups = {"find"})
    @Transactional
    public void testFindAll(){
        List<Region> result = regionsMapper.findAll();
        assertNotNull(result);
    }
    
    @Test(groups = {"find"})
    @Transactional
    public void testFindById(){
        Region result = this.regionsMapper.findById(1L);
        assertEquals(result.getId(), new Long(1L));
        assertEquals(result.getName(), "Europe"); 
    }
    
    @Test(groups = {"find"})
    @Transactional
    public void testFindByRawProperties(){
        List<Region> result = this.regionsMapper.findByRawProperties(new Region("americas"));
        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), "Americas");
        assertEquals(result.get(0).getId(), new Long(2L));
    }
    
//    @Test
//    public void testInsertUpdateDelete(){
//        testInsertOneRegions();
//        testInsertAnotherRegions();
//        testUpdateOneRegion();
//        testUpdateOneRegionwithEmpty();
//        testUpdateOneRegionwithNull();
//        testDeletebyId();
//        testDeletebyRegionId();
//    }
    
    @Test(groups = {"insert"})
    @Transactional
    public void testInsertOneRegions(){
        Region newregion = new Region(Long.MIN_VALUE, "Pacific");
        regionsMapper.insertOne(newregion);
        assertNotNull(newregion.getId());
        assertTrue(newregion.getId()>0);
        
        List<Region> result = regionsMapper.findByName("pacific");
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), "Pacific");
    }
    
    @Test(groups = {"insert"})
    @Transactional
    public void testInsertAnotherRegions(){
        Region newregion = new Region(Long.MIN_VALUE, "Artic");
        regionsMapper.insertOne(newregion);
        assertNotNull(newregion.getId());
        assertTrue(newregion.getId()>0);
        
        List<Region> result = regionsMapper.findByName("artic");
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), "Artic");
    }
    
    @Test(groups = {"update"},dependsOnGroups = {"insert"})
    @Transactional
    public void testUpdateOneRegion(){
        Region pacificregion = regionsMapper.findByName("Pacific").get(0);
        pacificregion.setName("Pacific01");
        regionsMapper.updateOne(pacificregion);
    }
    
    @Test(groups = {"update"},dependsOnGroups = {"insert"}, dependsOnMethods = {"testUpdateOneRegion"})
    @Transactional
    public void testUpdateOneRegionwithEmpty(){
        Region pacificregion = regionsMapper.findByName("Pacific01").get(0);
        pacificregion.setName("");
        regionsMapper.updateOne(pacificregion);
    }
    
    @Test(groups = {"update"},dependsOnGroups = {"insert"}, dependsOnMethods = {"testUpdateOneRegion"})
    @Transactional
    public void testUpdateOneRegionwithNull(){
        Region pacificregion = regionsMapper.findByName("Pacific01").get(0);
        pacificregion.setName(null);
        regionsMapper.updateOne(pacificregion);
    }
    
    @Test(groups = {"delete"},dependsOnGroups = {"update","insert"} )
    @Transactional
    public void testDeletebyId(){
        Region tobedeletedregion = regionsMapper.findByName("Pacific01").get(0);
        regionsMapper.deleteById(tobedeletedregion.getId());
        assertEquals(regionsMapper.findByName("Pacific01").size(), 0);
    }
    
    
    @Test(groups = {"delete"},dependsOnGroups = {"update","insert"})
    @Transactional
    public void testDeletebyRegionId(){
        Region tobedeletedregion = regionsMapper.findByName("artic").get(0);
        regionsMapper.deleteByRegionId(tobedeletedregion);
        assertEquals(regionsMapper.findByName("artic").size(), 0);
    } 
    
    @Test (groups = {"find"})
    @Transactional
    public void testCount(){
        Long total = regionsMapper.count();
        assertTrue(total > 0);
    }
    
    @Test(groups = {"find"})
    @Transactional
    public void testFindByRawType(){
        Region search;
        List<Region> result;
        
        search= new Region("Asia");        
        result = regionsMapper.findByRawType(search);        
        assertEquals(result.size(),1);
        assertEquals(result.get(0).getId(), new Long(3L));
        
        search= new Region();
        search.setId(3L);
        result = regionsMapper.findByRawType(search);
        assertEquals(result.size(),1);
        assertEquals(result.get(0).getName(), "Asia");
        
        search= new Region();
        result = regionsMapper.findByRawType(search);
        assertEquals(result.size(),1);
        assertNull(result.get(0));
        
    }
    
}
