/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import su90.mybatisdemo.dao.base.BaseMapper;
import su90.mybatisdemo.dao.domain.Department;

/**
 *
 * @author superman90
 */
@Mapper
public interface DepartmentsMapper extends BaseMapper<Department, Long, Department>{
    static public class SqlBuilderHelper{
        
    }
    
    @Select("select * from departments")
    @Results(value = {
        @Result(property = "id", column = "department_id"),
        @Result(property = "name", column = "department_name"),
        @Result(property = "manager", column = "manager_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.EmployeesMapper.findById",
                        fetchType = FetchType.LAZY
        )),
        @Result(property = "location", column = "location_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.LocationsMapper.findById",
                        fetchType = FetchType.LAZY
        ))            
    })
    @Override
    List<Department> findAll();
    
    @Select("select * from departments where department_id = #{id}")
    @Results(value = {
        @Result(property = "id", column = "department_id"),
        @Result(property = "name", column = "department_name"),
        @Result(property = "manager", column = "manager_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.EmployeesMapper.findById",
                        fetchType = FetchType.LAZY
        )),
        @Result(property = "location", column = "location_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.LocationsMapper.findById",
                        fetchType = FetchType.LAZY
        ))            
    })   
    @Override
    Department findById(Long id);

    @Override
    public List<Department> findByRawType(Department bean);
        
    @Override
    public Long count();

    @Override
    public void deleteById(Long id);

    @Override
    public void updateOne(Department bean);

    @Override
    public void insertOne(Department bean);

    @Override
    public List<Department> findByRawProperties(Department bean);

}
