/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import su90.mybatisdemo.dao.domain.Employee;
import su90.mybatisdemo.dao.mapper.EmployeesMapper;
import static org.junit.Assert.*;

/**
 *
 * @author superman90
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeesMapperTest {
    
    @Autowired
    EmployeesMapper employeesMapper;

    public void setEmployeesMapper(EmployeesMapper employeesMapper) {
        this.employeesMapper = employeesMapper;
    }
    
    @Test
    public void testFindAll(){
        List<Employee> result = employeesMapper.findAll();
        assertNotNull(result);
        assertEquals(result.size(), 107);
    }
    
    @Test
    public void testFindById(){
        Employee janette = employeesMapper.findById(156L);
        assertNotNull(janette.getJob());
        assertNotNull(janette.getManager());
        assertNotNull(janette.getDepartment());
    }
    
    @Test
    public void testFindByRawProperties01(){
        EmployeesMapper.EmployeeQuery sampleEQ = new EmployeesMapper
                .EmployeeQuery(employeesMapper.findById(148L));
        sampleEQ.setId(null);
        List<Employee> result = employeesMapper.findByRawProperties(sampleEQ);
        assertEquals(result.size(),1);
        assertNotNull(result.get(0));
        assertEquals(result.get(0).getFname(),"Gerald");
    };
    
    @Test
    public void testFindByRawProperties02(){
        try
        {
            EmployeesMapper.EmployeeQuery sampleEQ = new EmployeesMapper
                    .EmployeeQuery();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            java.util.Date startUtilDate = formatter.parse("20040101");
            java.util.Date endUtilDate = formatter.parse("20050101");
            sampleEQ.setStartHiredate(new Date(startUtilDate.getTime()));
            sampleEQ.setEndHiredate(new Date(endUtilDate.getTime()));
            List<Employee> result = employeesMapper.findByRawProperties(sampleEQ);
            assertEquals(result.size(),10);
        }catch(ParseException ex){
            assertTrue(false);
        }
    };
    
    @Test
    public void testFindByRawProperties03(){
        EmployeesMapper.EmployeeQuery sampleEQ = new EmployeesMapper
                .EmployeeQuery();
        List<Employee> result = employeesMapper.findByRawProperties(sampleEQ);
        assertEquals(result.size(),1);
        assertNull(result.get(0));
    };
    
}
