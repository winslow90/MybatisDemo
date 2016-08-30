/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao;

import java.util.List;
import org.apache.ibatis.builder.BuilderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import su90.mybatisdemo.dao.domain.Country;
import su90.mybatisdemo.dao.mapper.CountriesMapper;
import static org.junit.Assert.*;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import su90.mybatisdemo.dao.domain.Region;
import su90.mybatisdemo.dao.mapper.RegionsMapper;

/**
 *
 * @author superman90
 */
@SpringBootTest
public class CountriesMapperTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    CountriesMapper countriesMapper;
    
    @Autowired
    RegionsMapper regionsMapper;

    public void setRegionsMapper(RegionsMapper regionsMapper) {
        this.regionsMapper = regionsMapper;
    }

    public void setCountriesMapper(CountriesMapper countriesMapper) {
        this.countriesMapper = countriesMapper;
    }
    
    @Test(groups = {"find"})
    public void testFindAll(){
        List<Country> result = countriesMapper.findAll();
        assertNotNull(result);
        Country firstchild= result.get(0);
        Region firstchildregion = firstchild.getRegion();
        assertNotNull(firstchildregion);
    }
    
    @Test(groups = {"find"})
    public void testFindById(){
        Country onecountry = countriesMapper.findById("JP");
        assertNotNull(onecountry);
        assertNotNull(onecountry.getRegion());
    }
    
    @Test(groups = {"find"})
    public void testFindByRawProperties01(){        
        Country sampleCountry = new Country("Japan", regionsMapper.findByName("Asia").get(0));
        sampleCountry.setName(null);
        List<Country> result = countriesMapper.findByRawProperties(sampleCountry);
        assertTrue(result.size()>0);
        assertNotNull(result.get(0).getRegion());
    }
    
    @Test(groups = {"find"})
    public void testFindByRawProperties02(){        
        Country sampleCountry = new Country("Japan", regionsMapper.findByName("Asia").get(0));
        sampleCountry.setRegion(null);
        List<Country> result = countriesMapper.findByRawProperties(sampleCountry);
        assertTrue(result.size()>0);
        assertNotNull(result.get(0).getRegion());
    }
    
    @Test(groups = {"find"})
    public void testFindByRawProperties03(){        
        Country sampleCountry = new Country();
        List<Country> result = countriesMapper.findByRawProperties(sampleCountry);
        assertTrue(result.size()==1);
        assertNull(result.get(0));
//        assertNotNull(result.get(0).getRegion());
    }
    
    @Test(groups = {"find"})
    public void testCount(){
        Long count = countriesMapper.count();
        assertTrue(count>0);
    }
    
    @Test(groups = {"find"})
    public void testFindByRawType(){
        Country sample;
        List<Country> result;
        
        sample = countriesMapper.findById("JP");
        sample.setId(null);
        result = countriesMapper.findByRawProperties(sample);
        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getId(), "JP");
        
        sample = new Country();
        sample.setId("JP");
        result = countriesMapper.findByRawType(sample);
        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getName(), "Japan");
        
        sample = new Country();
        result = countriesMapper.findByRawType(sample);
        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertNull(result.get(0));        
    }
    
    
    @Test(groups = {"insert"})
    public void testInsertOne(){
        Region asia = regionsMapper.findByName("asia").get(0);
        Country korean = new Country("KR", "South Korea", asia);
        countriesMapper.insertOne(korean);
        assertNotNull(countriesMapper.findById("KR"));
    }
    
    @Test(groups = {"insert"},expectedExceptions = {MyBatisSystemException.class})
    public void testInsertOneWithoutKey(){
        Region asia = regionsMapper.findByName("asia").get(0);
        Country korean = new Country( "South Korea", asia);
        countriesMapper.insertOne(korean);
    }
    
    @Test(groups = {"insert"},expectedExceptions = {MyBatisSystemException.class})
    public void testInsertOneWithOnlyKey(){
//        Region asia = regionsMapper.findByName("asia").get(0);
        Country korean = new Country();
        korean.setId("SK");
        countriesMapper.insertOne(korean);
    }
    
    @Test(groups = {"insert"},expectedExceptions = {MyBatisSystemException.class})
    public void testInsertOneWithEmpty(){
//        Region asia = regionsMapper.findByName("asia").get(0);
        Country korean = new Country();
        countriesMapper.insertOne(korean);
    }
    
    @Test(groups = {"update"}, dependsOnGroups = {"insert"})
    public void testUpdateOne(){
        Country korean = countriesMapper.findById("KR");
        korean.setName("Republic of Korea");
        countriesMapper.updateOne(korean);
        assertEquals(countriesMapper.findById("KR").getName(), "Republic of Korea");        
    }
    
    @Test(groups = {"delete"}, dependsOnGroups = {"insert","update"})
    public void testDelete(){
        countriesMapper.deleteById("KR");
        assertNull(countriesMapper.findById("KR"));
    }
}
