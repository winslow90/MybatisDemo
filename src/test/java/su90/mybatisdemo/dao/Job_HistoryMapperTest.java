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
import su90.mybatisdemo.dao.domain.Job_History;
import su90.mybatisdemo.dao.mapper.Job_HistoryMapper;

import static org.junit.Assert.*;
import su90.mybatisdemo.dao.domain.Employee;

/**
 *
 * @author superman90
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Job_HistoryMapperTest {
    
    @Autowired
    Job_HistoryMapper job_HistoryMapper;

    public void setJob_HistoryMapper(Job_HistoryMapper job_HistoryMapper) {
        this.job_HistoryMapper = job_HistoryMapper;
    }
    
    @Test
    public void testFindAll(){
        List<Job_History> result = job_HistoryMapper.findAll();
        assertNotNull(result);
        assertEquals(result.size(), 10);
    }
    
    @Test
    public void testFindById01(){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            java.util.Date start_util_date = formatter.parse("20010113");        
            Job_History result = job_HistoryMapper.findByIdRaw(102L, new Date(start_util_date.getTime()));
            assertNotNull(result);
        }catch(ParseException ex){
            assertTrue(false);
        }
    }
    
    @Test
    public void testFindById02(){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Employee searchemp = new Employee();
            searchemp.setId(102L);
            java.util.Date start_util_date = formatter.parse("20010113");        
            Job_History result = job_HistoryMapper.findByIdObj(
                    searchemp,
                    new Date(start_util_date.getTime()));
            assertNotNull(result);
        }catch(ParseException ex){
            assertTrue(false);
        }
    }
    
    @Test
    public void testFindById03(){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            java.util.Date start_util_date = formatter.parse("20010113"); 
            Employee thesearchEmp = new Employee();
            thesearchEmp.setId(102L);
            Job_History result = job_HistoryMapper.findById(new Job_History.Key(thesearchEmp, new Date(start_util_date.getTime())));
            assertNotNull(result);
        }catch(ParseException ex){
            assertTrue(false);
        }
    }
    
    @Test
    public void testFindByRawProperties01(){
        try{            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            java.util.Date start_util_date = formatter.parse("20010113");        
            Job_History search = job_HistoryMapper.findByIdRaw(102L, new Date(start_util_date.getTime()));
            
            List<Job_History> result = job_HistoryMapper.findByRawProperties(search);
            
            assertEquals(result.size(), 1);
            assertNotNull(result.get(0));
            assertNotNull(result.get(0).getEmployee());
            assertNotNull(result.get(0).getJob());
            assertNotNull(result.get(0).getDepartment());
        }catch(ParseException ex){
            assertTrue(false);
        }
    }
    
    @Test
    public void testFindByRawProperties03(){
        List<Job_History> result = job_HistoryMapper.findByRawProperties(new Job_History());

        assertEquals(result.size(), 1);
        assertNull(result.get(0));
    }
    
}
