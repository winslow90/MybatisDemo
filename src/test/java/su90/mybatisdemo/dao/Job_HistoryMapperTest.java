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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import su90.mybatisdemo.dao.domain.Job_History;
import su90.mybatisdemo.dao.mapper.Job_HistoryMapper;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import su90.mybatisdemo.dao.domain.Employee;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import su90.mybatisdemo.dao.domain.Department;
import su90.mybatisdemo.dao.domain.Job;
import su90.mybatisdemo.dao.mapper.DepartmentsMapper;
import su90.mybatisdemo.dao.mapper.EmployeesMapper;
import su90.mybatisdemo.dao.mapper.JobsMapper;

/**
 *
 * @author superman90
 */
@SpringBootTest
public class Job_HistoryMapperTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    Job_HistoryMapper job_HistoryMapper;
    
    @Autowired
    EmployeesMapper employeesMapper;
    
    @Autowired
    JobsMapper jobsMapper;
    
    @Autowired
    DepartmentsMapper departmentsMapper;

    public void setDepartmentsMapper(DepartmentsMapper departmentsMapper) {
        this.departmentsMapper = departmentsMapper;
    }

    public void setJobsMapper(JobsMapper jobsMapper) {
        this.jobsMapper = jobsMapper;
    }

    public void setEmployeesMapper(EmployeesMapper employeesMapper) {
        this.employeesMapper = employeesMapper;
    }

    public void setJob_HistoryMapper(Job_HistoryMapper job_HistoryMapper) {
        this.job_HistoryMapper = job_HistoryMapper;
    }
    
    @Test(groups={"find"})
    public void testFindAll(){
        List<Job_History> result = job_HistoryMapper.findAll();
        assertNotNull(result);
        assertEquals(result.size(), 10);
    }
    
    @Test(groups={"find"})
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
    
    @Test(groups={"find"})
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
    
    @Test(groups={"find"})
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
    
    @Test(groups={"find"})
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
    
    @Test(groups={"find"})
    public void testFindByRawProperties03(){
        List<Job_History> result = job_HistoryMapper.findByRawProperties(new Job_History());

        assertEquals(result.size(), 1);
        assertNull(result.get(0));
    }
    
    @Test(groups = {"find"})
    public void testFindByRawType(){
         try{            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            java.util.Date start_util_date = formatter.parse("20010113");        
            Job_History search = job_HistoryMapper.findByIdRaw(102L, new Date(start_util_date.getTime()));
            
            List<Job_History> result = job_HistoryMapper.findByRawType(search);
            
            assertEquals(result.size(), 1);
            assertNotNull(result.get(0));
            assertNotNull(result.get(0).getEmployee());
            assertNotNull(result.get(0).getJob());
            assertNotNull(result.get(0).getDepartment());
        }catch(ParseException ex){
            assertTrue(false);
        }
    }
    
    @Test(groups = {"find"})
    public void testCount(){
        Long result = job_HistoryMapper.count();
        assertTrue(result>0);
    }
    
    @Test(groups = {"insert"})
    public void testInsert(){
        Employee emp = employeesMapper.findById(167L);
        Department dept = departmentsMapper.findById(60L);
        Job job = jobsMapper.findById("IT_PROG");
        
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            java.util.Date start_util_date = formatter.parse("19900101071010");
            java.util.Date end_util_date = new java.util.Date();
            
            Job_History jh = new Job_History(emp, 
                    new Date(start_util_date.getTime()), 
                    new Date(end_util_date.getTime()), 
                    job, dept);
            
            job_HistoryMapper.insertOne(jh);
            
            Job_History search = job_HistoryMapper.findByIdRaw(167L, new Date(start_util_date.getTime()));
            List<Job_History> result = job_HistoryMapper.findByRawType(search);
            
            assertEquals(result.size(), 1);
            assertNotNull(result.get(0));
            assertNotNull(result.get(0).getEmployee());
            assertNotNull(result.get(0).getJob());
            assertNotNull(result.get(0).getDepartment());
            
        }catch(ParseException ex){
            assertTrue(false);
        }
                
    }
    
    @Test(groups = {"update"}, dependsOnGroups = {"insert"})
    public void testUpdate(){
        Employee emp = employeesMapper.findById(167L);
        Department dept = departmentsMapper.findById(20L);
        Job job = jobsMapper.findById("HR_REP");
        
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            java.util.Date start_util_date = formatter.parse("19900101071010");
            java.util.Date end_util_date = new java.util.Date();
            
            Job_History search = job_HistoryMapper.findByIdRaw(167L, new Date(start_util_date.getTime()));
            List<Job_History> searchresult = job_HistoryMapper.findByRawType(search);
            
            Job_History jh = searchresult.get(0);
            
            jh.setDepartment(dept);
            jh.setJob(job);
            jh.setEnd_date(new Date(end_util_date.getTime()));
            
            job_HistoryMapper.updateOne(jh);
            
            Job_History ujh  = job_HistoryMapper.findById(
                    new Job_History.Key(
                            emp, 
                            new Date(start_util_date.getTime())
                    )
            );
            
            assertEquals(ujh.getDepartment().getId(), new Long(20L));
            assertEquals(ujh.getJob().getId(), "HR_REP");
            
        }catch(ParseException ex){
            assertTrue(false);
        }
                
    }
    
    @Test(groups = {"delete"}, dependsOnGroups = {"update", "insert"})
    public void testDelete(){
        Employee emp = employeesMapper.findById(167L);
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            java.util.Date start_util_date = formatter.parse("19900101071010");
            
            Job_History.Key thekey = new Job_History.Key(emp, new Date(start_util_date.getTime()));
            
            job_HistoryMapper.deleteById(thekey);
            
            assertNull(job_HistoryMapper.findById(thekey));
            
        }catch(ParseException ex){
            assertTrue(false);
        }
        
    }
}
