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
import su90.mybatisdemo.dao.domain.Employee;
import su90.mybatisdemo.dao.mapper.EmployeesMapper;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import su90.mybatisdemo.dao.domain.Job_History;
import su90.mybatisdemo.dao.mapper.DepartmentsMapper;
import su90.mybatisdemo.dao.mapper.Job_HistoryMapper;
import su90.mybatisdemo.dao.mapper.JobsMapper;

/**
 *
 * @author superman90
 */
@SpringBootTest
public class EmployeesMapperTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    EmployeesMapper employeesMapper;
    
    @Autowired
    JobsMapper jobsMapper;
    
    @Autowired
    DepartmentsMapper departmentsMapper;
    
    @Autowired
    Job_HistoryMapper job_HistoryMapper;

    public void setJob_HistoryMapper(Job_HistoryMapper job_HistoryMapper) {
        this.job_HistoryMapper = job_HistoryMapper;
    }

    public void setDepartmentsMapper(DepartmentsMapper departmentsMapper) {
        this.departmentsMapper = departmentsMapper;
    }

    public void setJobsMapper(JobsMapper jobsMapper) {
        this.jobsMapper = jobsMapper;
    }

    public void setEmployeesMapper(EmployeesMapper employeesMapper) {
        this.employeesMapper = employeesMapper;
    }
    
    @Test(groups={"find"})
    public void testFindAll(){
        List<Employee> result = employeesMapper.findAll();
        assertNotNull(result);
        assertEquals(result.size(), 107);
    }
    
    @Test(groups={"find"})
    public void testFindById(){
        Employee janette = employeesMapper.findById(156L);
        assertNotNull(janette.getJob());
        assertNotNull(janette.getManager());
        assertNotNull(janette.getDepartment());
    }
    
    @Test(groups={"find"})
    public void testFindById02(){
        Employee nobody = employeesMapper.findById(90L);
        assertNull(nobody);
    }
    
    @Test(groups={"find"})
    public void testFindByRawProperties01(){
        EmployeesMapper.EmployeeQuery sampleEQ = new EmployeesMapper
                .EmployeeQuery(employeesMapper.findById(148L));
        sampleEQ.setId(null);
        List<Employee> result = employeesMapper.findByRawProperties(sampleEQ);
        assertEquals(result.size(),1);
        assertNotNull(result.get(0));
        assertEquals(result.get(0).getFname(),"Gerald");
    }
    
    @Test(groups={"find"})
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
    }
    
    @Test(groups={"find"})
    public void testFindByRawProperties03(){
        EmployeesMapper.EmployeeQuery sampleEQ = new EmployeesMapper
                .EmployeeQuery();
        List<Employee> result = employeesMapper.findByRawProperties(sampleEQ);
        assertEquals(result.size(),1);
        assertNull(result.get(0));
    }
    
    @Test(groups={"find"})
    public void testFindByRawType(){
        Employee employee = employeesMapper.findById(148L);
        employee.setId(null);
        List<Employee> result = employeesMapper.findByRawType(employee);
        assertEquals(result.size(),1);
        assertNotNull(result.get(0));
        assertEquals(result.get(0).getFname(),"Gerald");
    }
    
    @Test(groups = {"find"})
    public void testCount(){
        Long result = employeesMapper.count();
        assertTrue(result>0);
    }
    
    @Test(groups = {"insert"},enabled = false)
//    @Test(groups = {"insert"})
    public void testInsert(){
        Employee employee = new Employee(
                "WENTAO", "LI", "wl256@njit.edu", "8625761649", 
                new Date((new java.util.Date()).getTime()), 
                jobsMapper.findById("IT_PROG"),
                60000.00, 0.15, 
                employeesMapper.findById(100L), 
                departmentsMapper.findById(60L));
        employeesMapper.insertOne(employee);
        
        Employee search = new Employee();
        search.setEmail("wl256@njit.edu");
        List<Employee> result = employeesMapper.findByRawType(search);
        
        assertTrue(result.size()>0);
        assertEquals(result.get(0).getFname(), "WENTAO");
    }
    
    @Test(groups = {"update"},dependsOnGroups = {"insert"},enabled = false)
    public void testUpdate(){
        Employee search = new Employee();
        search.setEmail("wl256@njit.edu");
        Employee tobeupdated = employeesMapper.findByRawType(search).get(0);
        
        tobeupdated.setLname("Leigh");
        
        employeesMapper.updateOne(tobeupdated);
        
        List<Employee> result = employeesMapper.findByRawType(search);
        
        assertTrue(result.size()>0);
        assertEquals(result.get(0).getLname(), "Leigh");
    }
    
    @Test(groups = {"delete"},dependsOnGroups = {"update","insert"},enabled = false)
    public void testDelete(){
        Employee search = new Employee();
        search.setEmail("wl256@njit.edu");
        Employee tobedeleted = employeesMapper.findByRawType(search).get(0);
        
        Job_History jhsearch = new Job_History();
        jhsearch.setEmployee(tobedeleted);
        
        List<Job_History> tobedeletedhistory= job_HistoryMapper.findByRawType(jhsearch);
        
        for(Job_History jh: tobedeletedhistory){
            job_HistoryMapper.deleteById(jh.getKey());
        }
        
        employeesMapper.deleteById(tobedeleted.getId());
        
        assertNull(employeesMapper.findById(tobedeleted.getId()));
    }
    
}
