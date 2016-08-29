/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import su90.mybatisdemo.bo.impl.RegionsService;

/**
 *
 * @author superman90
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class RegionsServiceTest {
    
    @Autowired
    RegionsService regionsService;

    public void setRegionsService(RegionsService regionsService) {
        this.regionsService = regionsService;
    }
    
    
}
