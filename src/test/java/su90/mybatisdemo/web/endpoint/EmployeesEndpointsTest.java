/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.web.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import su90.mybatisdemo.utils.UriUtils;
import su90.mybatisdemo.web.beans.EmployeeOut;
import su90.mybatisdemo.web.endpoints.EmployeesEndpoints;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author superman90
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeesEndpointsTest extends AbstractTestNGSpringContextTests {
    
//    RestTemplate restTemplate = new RestTemplate();
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void getEmployee(){
        EmployeeOut result = 
                restTemplate.getForObject(
                        "/api/employees/get/100"
                , EmployeeOut.class);        
        assertNotNull(result);
        assertEquals(result.fname, "Steven");   
    }
}
