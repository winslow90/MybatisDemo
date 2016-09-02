/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su90.mybatisdemo.bo.EmployeesService;
import su90.mybatisdemo.bo.base.BaseServiceImpl;
import su90.mybatisdemo.bo.ex.BeanAbsent;
import su90.mybatisdemo.dao.base.BaseMapper;
import su90.mybatisdemo.dao.domain.Employee;
import su90.mybatisdemo.dao.domain.Job_History;
import su90.mybatisdemo.dao.mapper.EmployeesMapper;
import su90.mybatisdemo.dao.mapper.Job_HistoryMapper;
import su90.mybatisdemo.dao.mapper.JobsMapper;
import su90.mybatisdemo.web.beans.EmployeeOut;

/**
 *
 * @author superman90
 */
@Service
@Transactional
public class EmployeesServiceImpl 
        extends BaseServiceImpl<Employee, Long, EmployeeOut, EmployeesMapper.EmployeeQuery>
        implements EmployeesService
{

    @Autowired
    EmployeesMapper employeesMapper;
    
    @Autowired
    Job_HistoryMapper job_HistoryMapper;    
    
    @Override
    public BaseMapper<Employee, Long, EmployeeOut, EmployeesMapper.EmployeeQuery> getBaseMapper() {
        return employeesMapper;
    }
    
    @Override
    public boolean hasEntityWithEmail(String email) {
        EmployeesMapper.EmployeeQuery query = new EmployeesMapper.EmployeeQuery();
        query.setEmail(email);
        return employeesMapper.findByRawProperties(query).size()>0;
    }

    @Override
    public void deleteEntryById(Long id) throws BeanAbsent{
        Employee tobedeleted = this.getEntryById(id);
        if (tobedeleted== null){
            throw new BeanAbsent("Can not delete absent bean");
        }
        
        Job_History jhsearch = new Job_History();
        jhsearch.setEmployee(tobedeleted);
        
        List<Job_History> tobedeletedhistory= job_HistoryMapper.findByRawType(jhsearch);
        
        for(Job_History jh: tobedeletedhistory){
            job_HistoryMapper.deleteById(jh.getKey());
        }
        
        employeesMapper.deleteById(tobedeleted.getId());        
    }
}
