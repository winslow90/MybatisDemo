/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao;

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

/**
 *
 * @author superman90
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class,initializers = ConfigFileApplicationContextInitializer.class)
public class RegionsMapperTest {
    
    @Autowired
    RegionsMapper regionsMapper;

    public void setRegionsMapper(RegionsMapper regionsMapper) {
        this.regionsMapper = regionsMapper;
    }
    
    @Test
    public void testFindById(){
        Regions result = this.regionsMapper.findById(1L);
        System.out.print(result);
    }
    
    
}
