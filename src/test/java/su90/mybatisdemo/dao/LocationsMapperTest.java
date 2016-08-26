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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import su90.mybatisdemo.dao.domain.Location;
import su90.mybatisdemo.dao.mapper.LocationsMapper;
import static org.junit.Assert.*;
import su90.mybatisdemo.dao.domain.Country;
import su90.mybatisdemo.dao.mapper.CountriesMapper;

/**
 *
 * @author superman90
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationsMapperTest {
    
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
    
    
    @Test
    public void testFindAll(){
        List<Location> result = locationsMapper.findAll();
        assertNotNull(result);
        assertNotNull(result.get(0).getCountry());
        assertNotNull(result.get(0).getCountry().getRegion());                
    }
    
    @Test
    public void testFindById(){
        Location oneLocation = locationsMapper.findById(1200L);
        assertNotNull(oneLocation);
        assertNotNull(oneLocation.getCountry());
        assertNotNull(oneLocation.getCountry().getRegion());     
    }
    
    @Test
    public void testFindByRawProperties01(){
        Location locationsample = new Location("2017 Shinjuku-ku", "1689", "Tokyo", "Tokyo Prefecture", countryMapper.findById("JP"));
//        locationsample.setAddress(null);
        List<Location> result = locationsMapper.findByRawProperties(locationsample);
        assertTrue(result.size()>0);
        assertNotNull(result.get(0).getCountry());
        assertNotNull(result.get(0).getCountry().getRegion());        
    }
    
    @Test
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
    
}
