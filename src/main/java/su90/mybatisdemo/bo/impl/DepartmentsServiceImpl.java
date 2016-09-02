/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su90.mybatisdemo.bo.DepartmentsService;
import su90.mybatisdemo.bo.base.BaseServiceImpl;
import su90.mybatisdemo.dao.base.BaseMapper;
import su90.mybatisdemo.dao.domain.Department;
import su90.mybatisdemo.dao.mapper.DepartmentsMapper;
import su90.mybatisdemo.web.beans.DepartmentBean;

/**
 *
 * @author superman90
 */
@Service
@Transactional
public class DepartmentsServiceImpl 
        extends BaseServiceImpl<Department, Long, DepartmentBean, Department>
        implements DepartmentsService
{

    @Autowired
    DepartmentsMapper departmentsMapper;
    
    @Override
    public BaseMapper<Department, Long, DepartmentBean, Department> getBaseMapper() {
        return departmentsMapper;
    }    
    
}
