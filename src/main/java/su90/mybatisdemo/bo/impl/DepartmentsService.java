/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class DepartmentsService extends BaseServiceImpl<Department, Long, DepartmentBean, Department>{

    @Autowired
    DepartmentsMapper departmentsMapper;
    
    @Override
    public BaseMapper<Department, Long, DepartmentBean, Department> getBaseMapper() {
        return departmentsMapper;
    }    
    
}
