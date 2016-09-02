/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo;

import su90.mybatisdemo.bo.base.BaseService;
import su90.mybatisdemo.dao.base.BaseDomain;
import su90.mybatisdemo.dao.domain.Employee;
import su90.mybatisdemo.dao.mapper.EmployeesMapper;
import su90.mybatisdemo.web.beans.EmployeeOut;

/**
 *
 * @author superman90
 */
public interface EmployeesService extends BaseService<Employee, Long, EmployeeOut, EmployeesMapper.EmployeeQuery>{
    public boolean hasEntityWithEmail(String email);    
}
