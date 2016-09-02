/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import su90.mybatisdemo.bo.impl.EmployeesServiceImpl;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author superman90
 */
@SpringBootTest
public class EmployeesServiceTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    EmployeesService employeesService;
    
    @Test(enabled = true)
    public void testHasEntityWithEmail(){
        assertFalse(employeesService.hasEntityWithEmail("wl257"));
        assertTrue(employeesService.hasEntityWithEmail("SKING"));
    }
}
