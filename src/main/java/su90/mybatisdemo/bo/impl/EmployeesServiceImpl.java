/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su90.mybatisdemo.bo.EmployeesService;
import su90.mybatisdemo.bo.base.BaseServiceImpl;
import su90.mybatisdemo.dao.base.BaseMapper;
import su90.mybatisdemo.dao.domain.Employee;
import su90.mybatisdemo.dao.mapper.EmployeesMapper;
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
    
}
