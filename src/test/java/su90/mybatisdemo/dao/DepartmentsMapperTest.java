/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import su90.mybatisdemo.dao.domain.Department;
import su90.mybatisdemo.dao.mapper.DepartmentsMapper;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import su90.mybatisdemo.dao.domain.Employee;
import su90.mybatisdemo.dao.domain.Location;
import su90.mybatisdemo.dao.mapper.EmployeesMapper;
import su90.mybatisdemo.dao.mapper.LocationsMapper;

/**
 *
 * @author superman90
 */
@SpringBootTest
public class DepartmentsMapperTest extends AbstractTestNGSpringContextTests {
    
    @Autowired
    DepartmentsMapper departmentsMapper;
    
    @Autowired
    EmployeesMapper employeesMapper;
    
    @Autowired
    LocationsMapper locationsMapper;

    public void setLocationsMapper(LocationsMapper locationsMapper) {
        this.locationsMapper = locationsMapper;
    }

    public void setEmployeesMapper(EmployeesMapper employeesMapper) {
        this.employeesMapper = employeesMapper;
    }

    public void setDepartmentsMapper(DepartmentsMapper departmentsMapper) {
        this.departmentsMapper = departmentsMapper;
    }
    
    @Test(groups = {"find"})
    public void testFinalAll(){
        List<Department> result = departmentsMapper.findAll();
        assertNotNull(result);
        assertNotNull(result.get(0));
        assertNotNull(result.get(0).getLocation());
        assertNotNull(result.get(0).getLocation().getCountry());
        assertNotNull(result.get(0).getLocation().getCountry().getRegion());
    }
    
    @Test(groups = {"find"})
    public void testFindById(){
        Department onedepart = departmentsMapper.findById(20L);
        assertNotNull(onedepart);
        assertNotNull(onedepart.getLocation());
        assertNotNull(onedepart.getManager());
    }
    
    @Test(groups = {"find"})
    public void testFindByRawType(){
        Department search = new Department();
        search.setName("Purchasing");
        
        List<Department> result = departmentsMapper.findByRawType(search);
        
        assertTrue(result.size()>0);
        assertNotNull(result.get(0));
    }
    
    @Test(groups = {"find"})
    public void testFindByRawProperties(){
        Department search = new Department();
        search.setName("purchasing");
        
        List<Department> result = departmentsMapper.findByRawProperties(search);
        
        assertTrue(result.size()>0);
        assertNotNull(result.get(0));
    }
    
    @Test(groups = {"find"})
    public void testCount(){
        Long result = departmentsMapper.count();
        assertTrue(result>=0);
    }
    
    @Test(groups = {"insert"},enabled = false)
    public void testInsert(){
        Employee manager = employeesMapper.findById(166L);
        Location location =  locationsMapper.findById(1600L);
        
        Department newdept = new Department("Dummy Dept", manager, location);
        
        departmentsMapper.insertOne(newdept);
        
        Department search= new Department();
        search.setName("dummy dept");
        assertTrue(departmentsMapper.findByRawProperties(search).size()==1);
        assertNotNull(departmentsMapper.findByRawProperties(search).get(0));
    }
    
    @Test(groups = {"update"}, dependsOnGroups = {"insert"},enabled = false)
    public void testUpdate(){
        Employee manager = employeesMapper.findById(167L);
        
        Department search= new Department();
        search.setName("dummy dept");
        Department old = departmentsMapper.findByRawProperties(search).get(0);
        
        old.setManager(manager);
        
        departmentsMapper.updateOne(old);
        
        Department newdept = departmentsMapper.findByRawProperties(search).get(0);
        
        assertEquals(newdept.getManager().getId(), new Long(167L));
    }
    
    @Test(groups = {"delete"}, dependsOnGroups = {"update","insert"},enabled = false)
    public void testDelete(){
        Department search= new Department();
        search.setName("dummy dept");
        Department tobedeleted = departmentsMapper.findByRawProperties(search).get(0);
        
        departmentsMapper.deleteById(tobedeleted.getId());
        
        assertNull(locationsMapper.findById(tobedeleted.getId()));
    }
}
