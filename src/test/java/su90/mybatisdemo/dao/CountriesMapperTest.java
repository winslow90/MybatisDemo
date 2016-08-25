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
import su90.mybatisdemo.dao.domain.Country;
import su90.mybatisdemo.dao.mapper.CountriesMapper;
import static org.junit.Assert.*;
import su90.mybatisdemo.dao.domain.Region;

/**
 *
 * @author superman90
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CountriesMapperTest {
    
    @Autowired
    CountriesMapper countriesMapper;

    public void setCountriesMapper(CountriesMapper countriesMapper) {
        this.countriesMapper = countriesMapper;
    }
    
    @Test
    public void testFindAll(){
        List<Country> result = countriesMapper.findAll();
        assertNotNull(result);
        Country firstchild= result.get(0);
        Region firstchildregion = firstchild.getRegion();
        assertNotNull(firstchildregion);
    }
    
}
