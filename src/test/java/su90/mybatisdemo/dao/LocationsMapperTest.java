/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import su90.mybatisdemo.dao.domain.Location;
import su90.mybatisdemo.dao.mapper.LocationsMapper;
import static org.testng.Assert.*;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import su90.mybatisdemo.dao.domain.Country;
import su90.mybatisdemo.dao.mapper.CountriesMapper;

/**
 *
 * @author superman90
 */
//@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationsMapperTest extends AbstractTestNGSpringContextTests{
        
    @Autowired
    LocationsMapper locationsMapper;
    
    @Autowired
    CountriesMapper countryMapper;

    public void setCountryMapper(CountriesMapper countryMapper) {
        this.countryMapper = countryMapper;
    }

    public void setLocatoinMapper(LocationsMapper locatoinMapper) {
        this.locationsMapper = locatoinMapper;
    }
    
    
    @Test(groups = {"find"})
    public void testFindAll(){
        List<Location> result = locationsMapper.findAll();
        assertNotNull(result);
        assertNotNull(result.get(0).getCountry());
        assertNotNull(result.get(0).getCountry().getRegion());                
    }
    
    @Test(groups = {"find"})
    public void testFindById(){
        Location oneLocation = locationsMapper.findById(1200L);
        assertNotNull(oneLocation);
        assertNotNull(oneLocation.getCountry());
        assertNotNull(oneLocation.getCountry().getRegion());     
    }
    
    @Test(groups = {"find"})
    public void testFindByRawProperties01(){
        Location locationsample = new Location("2017 Shinjuku-ku", "1689", "Tokyo", "Tokyo Prefecture", countryMapper.findById("JP"));
//        locationsample.setAddress(null);
        List<Location> result = locationsMapper.findByRawProperties(locationsample);
        assertTrue(result.size()>0);
        assertNotNull(result.get(0).getCountry());
        assertNotNull(result.get(0).getCountry().getRegion());        
    }
    
    @Test(groups = {"find"})
    public void testFindByRawProperties02(){
        Location locationsample = new Location("2017 Shinjuku-ku", "1689", "Tokyo", "Tokyo Prefecture", countryMapper.findById("JP"));
        locationsample.setAddress(null);
        locationsample.setPostal_code(null);
        locationsample.setCity(null);
        locationsample.setProvince(null);
        List<Location> result = locationsMapper.findByRawProperties(locationsample);
        assertTrue(result.size()==2);
        assertNotNull(result.get(0).getCountry());
        assertNotNull(result.get(0).getCountry().getRegion());        
    }
    
    @Test(groups = {"find"})
    public void testFindByRawType(){
        Location sample;
        List<Location>  result;
        
        sample = locationsMapper.findById(1200L);
        sample.setId(null);
        result = locationsMapper.findByRawType(sample);
        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getId(), new Long(1200L));
        
        sample = new Location();
        sample.setId(1200L);
        result = locationsMapper.findByRawType(sample);
        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getAddress(), "2017 Shinjuku-ku");
        
        sample = new Location();
        result = locationsMapper.findByRawType(sample);
        assertNotNull(result);
        assertEquals(result.size(), 1);
        assertNull(result.get(0));
    }
    
    @Test(groups = {"find"})
    public void testCount(){
        Long count = locationsMapper.count();        
        assertTrue(count>=0);
    }
    
    @Test(groups = {"insert"},enabled = false)
    public void testInsertOne(){
        Country country = countryMapper.findById("US");
        Location location = new Location("718 William St", "07029", "Harrison", "NJ", country);
        locationsMapper.insertOne(location);
        Location search = new Location();
        search.setPostal_code("07029");
        List<Location> result = locationsMapper.findByRawType(search);
        assertTrue(result.size()>0);
    }
    
    @Test(groups = {"updates"} , dependsOnGroups = {"insert"},enabled = false)
    public void testUpdateOne(){
        Location search = new Location();
        search.setPostal_code("07029");
        Location tobeupdated = locationsMapper.findByRawType(search).get(0);
        
        tobeupdated.setCity("Kearny");
        locationsMapper.updateOne(tobeupdated);
        
        Location updated = locationsMapper.findById(tobeupdated.getId());
        assertEquals(updated.getCity(), "Kearny");
    }
    
    @Test(groups = {"delete"}, dependsOnGroups = {"updates","insert"},enabled = false)
    public void testDelete(){
        Location search = new Location();
        search.setPostal_code("07029");
        Location tobedeleted = locationsMapper.findByRawType(search).get(0);
        
        locationsMapper.deleteById(tobedeleted.getId());
        
        assertNull(locationsMapper.findById(tobedeleted.getId()));
    }
}
