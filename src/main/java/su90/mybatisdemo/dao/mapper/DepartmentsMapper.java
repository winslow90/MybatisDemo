/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.FetchType;
import su90.mybatisdemo.dao.base.BaseMapper;
import su90.mybatisdemo.dao.domain.Department;
import static org.apache.ibatis.jdbc.SqlBuilder.*;
import su90.mybatisdemo.dao.ex.InvalidBeanException;

/**
 *
 * @author superman90
 */
@Mapper
public interface DepartmentsMapper extends BaseMapper<Department, Long, Department>{

    static public class SqlBuilderHelper{
        public String buildFindByRawType(Department d){
            BEGIN();
            if (d.hasValidatedKey()||d.isValidated()){
                SELECT("*");
                FROM("departments");
//                (name!=null&&!name.isEmpty())||
                if (d.getName()!=null&&!d.getName().isEmpty()){
                    WHERE("department_name = #{name}");
                }
//                (manager!=null&&manager.getId()!=null)||
                if (d.getManager()!=null && d.getManager().getId()!=null){
                    WHERE("manager_id = #{d.manager.id");
                }
//                (location!=null&&location.getId()!=null);
                if (d.getLocation()!=null && d.getLocation().getId()!=null){
                    WHERE("location_id = #{d.location.id}");
                }                
            }else{
                SELECT("dummy");
                FROM("dual");                
            }
            
            return SQL();
        }
        
        public String buildFindByRawProperties(Department d){
            BEGIN();
            if (d.hasValidatedKey()||d.isValidated()){
                SELECT("*");
                FROM("departments");
//                (name!=null&&!name.isEmpty())||
                if (d.getName()!=null&&!d.getName().isEmpty()){
                    WHERE("upper(department_name) = upper(#{name})");
                }
//                (manager!=null&&manager.getId()!=null)||
                if (d.getManager()!=null && d.getManager().getId()!=null){
                    WHERE("manager_id = #{d.manager.id");
                }
//                (location!=null&&location.getId()!=null);
                if (d.getLocation()!=null && d.getLocation().getId()!=null){
                    WHERE("location_id = #{d.location.id}");
                }                
            }else{
                SELECT("dummy");
                FROM("dual");                
            }
            
            return SQL();
        }
        
        public String buildInsertOne(Department d){
            BEGIN();
            if (d.isValidated()){
                INSERT_INTO("departments");
                VALUES("department_id", "departments_seq.nextval");
                if (d.getName()!=null&&!d.getName().isEmpty()){
                    VALUES("department_name", "#{name}");
                }
//                (manager!=null&&manager.getId()!=null)||
                if (d.getManager()!=null && d.getManager().getId()!=null){
                    VALUES("manager_id", "#{manager.id}");
                }
//                (location!=null&&location.getId()!=null);
                if (d.getLocation()!=null && d.getLocation().getId()!=null){
                    VALUES("location_id", "#{location.id}");
                }  
            }else{
                throw new InvalidBeanException("The Department bean to be inserted is invalid");
            }
            return SQL();
        }
        
        public String buildUpdateOne(Department d){
            BEGIN();
            if (d.hasValidatedKey()&&d.isValidated()){
                UPDATE("departments");
                if (d.getName()!=null&&!d.getName().isEmpty()){
                    SET("department_name = #{name}");
                }
//                (manager!=null&&manager.getId()!=null)||
                if (d.getManager()!=null && d.getManager().getId()!=null){
                    SET("manager_id = #{manager.id}");
                }
//                (location!=null&&location.getId()!=null);
                if (d.getLocation()!=null && d.getLocation().getId()!=null){
                    SET("location_id = #{location.id}");
                }                  
                WHERE("department_id = #{id}");
            }else{
                throw new InvalidBeanException("The Department bean to be updated has to have a key");
            }
            return SQL();
        }
        
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
    
    @SelectProvider(type = SqlBuilderHelper.class, method = "buildFindByRawType")
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
    public List<Department> findByRawType(Department bean);
    
    @SelectProvider(type = SqlBuilderHelper.class, method = "buildFindByRawProperties")
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
    public List<Department> findByRawProperties(Department bean);
    
    @Select("select count(*) from departments")
    @Override
    public Long count();

    @InsertProvider(type = SqlBuilderHelper.class, method = "buildInsertOne")
    @Override
    public void insertOne(Department bean);
    
    @UpdateProvider(type = SqlBuilderHelper.class, method = "buildUpdateOne")
    @Override
    public void updateOne(Department bean);

    @Delete("delete from departments where department_id = #{id}")
    @Override
    public void deleteById(@Param("id") Long id);
    
}
