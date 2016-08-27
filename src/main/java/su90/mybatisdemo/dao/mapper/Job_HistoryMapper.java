/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.mapper;

import java.sql.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import su90.mybatisdemo.dao.domain.Employee;
import su90.mybatisdemo.dao.domain.Job_History;
import static org.apache.ibatis.jdbc.SqlBuilder.*;

/**
 *
 * @author superman90
 */
@Mapper
public interface Job_HistoryMapper {
    static public class SqlBuilderHelper{
        public String buildFindByRawProperties(Job_History jh){
            BEGIN();
            if (jh.isValidated()){
                SELECT("*");
                FROM("job_history");
//                (employee!=null&& employee.getId()!=null)||
                if (jh.getEmployee()!=null&&jh.getEmployee().getId()!=null){
                    WHERE("employee_id = #{employee.id}");
                }
//                (start_date!=null)||
                if (jh.getStart_date()!=null){
                    WHERE("start_date <= #{start_date}");
                }
//                (end_date!=null)||
                if (jh.getEnd_date()!=null){
                    WHERE("end_date <= #{end_date}");
                }
//                (job!=null&&job.getId()!=null)||
                if (jh.getJob()!=null){
                    WHERE("job_id = #{job.id}");
                }
//                (department!=null&&department.getId()!=null;
                if (jh.getDepartment()!=null){
                    WHERE("department_id = #{department.id}");
                }
            }else{
                SELECT("dummy");
                FROM("dual");
            }            
            return SQL();
        }
    }
    
    @Select("select * from job_history")
    @Results(value = {
        @Result(property = "employee", column = "employee_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.EmployeesMapper.findById",
                        fetchType = FetchType.LAZY
                )),
        @Result(property = "start_date",column = "start_date"),
        @Result(property = "end_date", column = "end_date"),
        @Result(property = "job", column="job_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.JobsMapper.findById",
                        fetchType = FetchType.LAZY
                        
                )),
        @Result(property = "department", column = "department_id",
                one = @One(
                       select = "su90.mybatisdemo.dao.mapper.DepartmentsMapper.findById",
                       fetchType = FetchType.LAZY
                ))
    })
    List<Job_History> findAll();
    
    @Select("select * from job_history where employee_id = #{employee_id} and start_date = #{start_date}")
    @Results(value = {
        @Result(property = "employee", column = "employee_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.EmployeesMapper.findById",
                        fetchType = FetchType.LAZY
                )),
        @Result(property = "start_date",column = "start_date"),
        @Result(property = "end_date", column = "end_date"),
        @Result(property = "job", column="job_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.JobsMapper.findById",
                        fetchType = FetchType.LAZY
                        
                )),
        @Result(property = "department", column = "department_id",
                one = @One(
                       select = "su90.mybatisdemo.dao.mapper.DepartmentsMapper.findById",
                       fetchType = FetchType.LAZY
                ))
    })
    Job_History findByIdRaw(@Param("employee_id") Long employee_id, @Param("start_date") Date start_date);
    
    @Select("select * from job_history where employee_id = #{employee.id} and start_date = #{start_date}")
    @Results(value = {
        @Result(property = "employee", column = "employee_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.EmployeesMapper.findById",
                        fetchType = FetchType.LAZY
                )),
        @Result(property = "start_date",column = "start_date"),
        @Result(property = "end_date", column = "end_date"),
        @Result(property = "job", column="job_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.JobsMapper.findById",
                        fetchType = FetchType.LAZY
                        
                )),
        @Result(property = "department", column = "department_id",
                one = @One(
                       select = "su90.mybatisdemo.dao.mapper.DepartmentsMapper.findById",
                       fetchType = FetchType.LAZY
                ))
    })
    Job_History findByIdObj(@Param("employee") Employee employee_with_id, @Param("start_date") Date start_date);
    
    @SelectProvider(type = SqlBuilderHelper.class, method = "buildFindByRawProperties")
    @Results(value = {
        @Result(property = "employee", column = "employee_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.EmployeesMapper.findById",
                        fetchType = FetchType.LAZY
                )),
        @Result(property = "start_date",column = "start_date"),
        @Result(property = "end_date", column = "end_date"),
        @Result(property = "job", column="job_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.JobsMapper.findById",
                        fetchType = FetchType.LAZY
                        
                )),
        @Result(property = "department", column = "department_id",
                one = @One(
                       select = "su90.mybatisdemo.dao.mapper.DepartmentsMapper.findById",
                       fetchType = FetchType.LAZY
                ))
    })
    List<Job_History> findByRawProperties(Job_History job_History);
    
}
