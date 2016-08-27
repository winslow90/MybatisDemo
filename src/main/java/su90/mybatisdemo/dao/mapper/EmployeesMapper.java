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
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import su90.mybatisdemo.dao.domain.Employee;
import su90.mybatisdemo.dao.domain.Job;
import static org.apache.ibatis.jdbc.SqlBuilder.*;
import su90.mybatisdemo.dao.domain.Department;

/**
 *
 * @author superman90
 */
@Mapper
public interface EmployeesMapper {
    
    static public class SqlBuilderHelper{
        public String buildFindByRawProperties(EmployeeQuery eq){
            BEGIN();
            if (eq.isValidated()){
                BEGIN();
                SELECT("*");
                FROM("employees");
//                (fname!=null&&!fname.isEmpty())||
                if (eq.getFname()!=null&&!eq.getFname().isEmpty()){
                    WHERE("upper(first_name) like upper(#{fname})");
                }
//                (lname!=null&&!lname.isEmpty())||
                if (eq.getLname()!=null&&!eq.getLname().isEmpty()){
                    WHERE("upper(last_name) like upper(#{lname})");
                }
//                (email!=null&&!email.isEmpty())||
                if (eq.getEmail()!=null && !eq.getEmail().isEmpty()){
                    WHERE("upper(email) like upper(#{email})");
                }
//                (phone!=null&&!phone.isEmpty())||
                if (eq.getPhone()!=null && !eq.getPhone().isEmpty()){
                    WHERE("upper(phone_number) like upper(#{phone})");
                }
//                (hiredate!=null)||
                if (eq.getHiredate()!=null){
                    WHERE("hire_date = #{hiredate}");
                }else{
                    if (eq.getStartHiredate()!=null&&eq.getEndHiredate()!=null)
                        WHERE("hire_date between #{startHiredate} and #{endHiredate}");
                    else{
                        if (eq.getStartHiredate()!=null)
                            WHERE("hire_date>= #{startHiredate}");
                        if (eq.getEndHiredate()!=null)
                            WHERE("hire_date<= #{endHiredate}");
                    }
                }
//                (job!=null&&job.getId()!=null)||
                if (eq.getJob()!=null && eq.getJob().getId()!=null){
                    WHERE("job_id = #{job.id}");
                }
//                (salary!=null&&salary>=0)||
                if (eq.getSalary()!=null && eq.getSalary()>=0){
                    WHERE("salary = #{salary}");
                }
//                (comm!=null&&comm>=0)||
                if (eq.getComm()!=null && eq.getComm()>=0){
                    WHERE("commission_pct= #{comm}");
                }
//                (manager!=null&&manager.getId()!=null)||
                if (eq.getManager()!=null && eq.getManager().getId()!=null){
                    WHERE("manager_id = #{manager.id}");
                }
//                (department!=null&&department.getId()!=null);
                if (eq.getDepartment()!=null && eq.getDepartment().getId()!=null){
                    WHERE("department_id = #{department.id}");
                }
                return SQL();                
            }else{
                SELECT("dummy");
                FROM("dual");
            }
            return SQL();
        }
    }
    
    static public class EmployeeQuery extends Employee{

        Date startHiredate;
        Date endHiredate;

        public EmployeeQuery() {
        }
        
        public EmployeeQuery(Employee employee){
            this.id = employee.id;
            this.fname = employee.fname;
            this.lname = employee.lname;
            this.email = employee.email;
            this.phone = employee.phone;
            this.hiredate = employee.hiredate;
            this.job = employee.job;
            this.salary = employee.salary;
            this.comm = employee.comm;
            this.manager = employee.manager;
            this.department = employee.department;
        }

        public EmployeeQuery(Date startHiredate, Date endHiredate, String fname, String lname, String email, String phone, Date hiredate, Job job, Double salary, Double comm, Employee manager, Department department) {
            super(fname, lname, email, phone, hiredate, job, salary, comm, manager, department);
            this.startHiredate = startHiredate;
            this.endHiredate = endHiredate;
        }

        public EmployeeQuery(Date startHiredate, Date endHiredate, Long id, String fname, String lname, String email, String phone, Date hiredate, Job job, Double salary, Double comm, Employee manager, Department department) {
            super(id, fname, lname, email, phone, hiredate, job, salary, comm, manager, department);
            this.startHiredate = startHiredate;
            this.endHiredate = endHiredate;
        }

        public Date getStartHiredate() {
            return startHiredate;
        }

        public void setStartHiredate(Date startHiredate) {
            this.startHiredate = startHiredate;
        }

        public Date getEndHiredate() {
            return endHiredate;
        }

        public void setEndHiredate(Date endHiredate) {
            this.endHiredate = endHiredate;
        }

        @Override
        public Boolean isValidated() {
            return super.isValidated()||
                    (startHiredate!=null)||
                    (endHiredate!=null); 
        }
        
        
        
    }
    
    @Select("select * from employees")
    @Results(value={
        @Result(property = "id", column = "employee_id"),
        @Result(property = "fname", column = "first_name"),
        @Result(property = "lname", column = "last_name"),
        @Result(property = "email", column = "email"),
        @Result(property = "phone", column = "phone_number"),
        @Result(property = "hiredate", column = "hire_date"),
        @Result(property = "job", column = "job_id",
                javaType = Job.class,
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.JobsMapper.findById",
                        fetchType = FetchType.LAZY
                )),
        @Result(property = "salary", column = "salary"),
        @Result(property = "comm", column = "commission_pct"),
        @Result(property = "manager",column = "manager_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.EmployeesMapper.findById",
                        fetchType = FetchType.LAZY
                )),
        @Result(property = "department", column = "department_id",
                one = @One(
                       select = "su90.mybatisdemo.dao.mapper.DepartmentsMapper.findById",
                       fetchType = FetchType.LAZY
                ))
    })
    List<Employee> findAll();
    
    @Select("select * from employees where employee_id = #{id}")
    @Results(value={
        @Result(property = "id", column = "employee_id"),
        @Result(property = "fname", column = "first_name"),
        @Result(property = "lname", column = "last_name"),
        @Result(property = "email", column = "email"),
        @Result(property = "phone", column = "phone_number"),
        @Result(property = "hiredate", column = "hire_date"),
        @Result(property = "job", column = "job_id",
                javaType = Job.class,
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.JobsMapper.findById",
                        fetchType = FetchType.LAZY
                )),
        @Result(property = "salary", column = "salary"),
        @Result(property = "comm", column = "commission_pct"),
        @Result(property = "manager",column = "manager_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.EmployeesMapper.findById",
                        fetchType = FetchType.LAZY
                )),
        @Result(property = "department", column = "department_id",
                one = @One(
                       select = "su90.mybatisdemo.dao.mapper.DepartmentsMapper.findById",
                        fetchType = FetchType.LAZY
                ))
    })
    Employee findById(Long id);
    
    @SelectProvider(type = SqlBuilderHelper.class, method = "buildFindByRawProperties")
    @Results(value={
        @Result(property = "id", column = "employee_id"),
        @Result(property = "fname", column = "first_name"),
        @Result(property = "lname", column = "last_name"),
        @Result(property = "email", column = "email"),
        @Result(property = "phone", column = "phone_number"),
        @Result(property = "hiredate", column = "hire_date"),
        @Result(property = "job", column = "job_id",
                javaType = Job.class,
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.JobsMapper.findById",
                        fetchType = FetchType.LAZY
                )),
        @Result(property = "salary", column = "salary"),
        @Result(property = "comm", column = "commission_pct"),
        @Result(property = "manager",column = "manager_id",
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.EmployeesMapper.findById",
                        fetchType = FetchType.LAZY
                )),
        @Result(property = "department", column = "department_id",
                one = @One(
                       select = "su90.mybatisdemo.dao.mapper.DepartmentsMapper.findById",
                       fetchType = FetchType.LAZY
                ))
    })
    List<Employee> findByRawProperties(EmployeeQuery eq);
    
    
}
