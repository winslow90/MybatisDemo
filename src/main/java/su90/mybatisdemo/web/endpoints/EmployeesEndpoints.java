/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.web.endpoints;

import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import su90.mybatisdemo.bo.DepartmentsService;
import su90.mybatisdemo.bo.EmployeesService;
import su90.mybatisdemo.bo.JobsService;
import su90.mybatisdemo.bo.ex.BeanAbsent;
import su90.mybatisdemo.bo.impl.DepartmentsServiceImpl;
import su90.mybatisdemo.bo.impl.EmployeesServiceImpl;
import su90.mybatisdemo.bo.impl.JobsServiceImpl;
import su90.mybatisdemo.dao.domain.Employee;
import su90.mybatisdemo.utils.UriUtils;
import su90.mybatisdemo.web.beans.EmployeeIn;
import su90.mybatisdemo.web.beans.EmployeeOut;

/**
 *
 * @author superman90
 */
@RestController
@RequestMapping(value = {"/api/employees"})
public class EmployeesEndpoints {
    
    @Autowired
    EmployeesService employeesService;
    @Autowired
    JobsService jobsService;
    @Autowired
    DepartmentsService departmentsService;
    
    @RequestMapping(value = "/getall",method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    public ResponseEntity<List<EmployeeOut>> getAll(){
        List<EmployeeOut> result = employeesService.getWebBeans();
        if (result.isEmpty()){
            return new ResponseEntity<>(result,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE}
    )
    @ApiOperation(value = "find one employee")
    public ResponseEntity<EmployeeOut> getOne(@PathVariable Long id){
        EmployeeOut result = employeesService.getWebBeanById(id);
        if (result==null){
            return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/set/", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            produces = {MediaType.TEXT_PLAIN_VALUE}
    )
    @ApiOperation(value = "create one employee if possible")
    public ResponseEntity<Void> createEmployee(@RequestBody EmployeeIn employeeIn){
        if (employeeIn.email==null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (employeesService.hasEntityWithEmail(employeeIn.email)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        
        if (employeeIn.job_id!=null&&jobsService.getEntryById(employeeIn.job_id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);                 
        }
        
        if (employeeIn.manager_id!=null&&employeesService.getEntryById(employeeIn.manager_id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        
        if (employeeIn.department_id!=null&&departmentsService.getEntryById(employeeIn.department_id)==null){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        
        Long thekey = employeesService.saveEntry(employeeIn.getDomain());
        
        if (thekey!=null){
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(UriUtils.generateUri(MvcUriComponentsBuilder.on(
                    EmployeesEndpoints.class).getOne(thekey)));
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }       
        
    }
    
    @RequestMapping(value = {"/update/{id}"},method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ApiOperation(value = "update one employee if possible")
    public ResponseEntity<EmployeeOut> updateEmployee(
            @PathVariable("id") Long id,
            @RequestBody EmployeeIn empIn
    ){
        Employee curemp = employeesService.getEntryById(id);
        
        if (curemp==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        Employee tobeupdated = empIn.getDomain();
        tobeupdated.setId(id);
               
        employeesService.updateEntry(tobeupdated);
        
        return new ResponseEntity<>(employeesService.getWebBeanById(id),HttpStatus.OK);
    }
    
    @RequestMapping(value = {"/delete/{id}"},method = RequestMethod.DELETE,
            produces = {MediaType.TEXT_PLAIN_VALUE}
    )
    public ResponseEntity<String> deleteUser(
            @PathVariable("id") Long id            
    ){
        try{            
            employeesService.deleteEntryById(id);
        }catch(BeanAbsent e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
        
    }
}
