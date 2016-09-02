/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.web.beans;

import java.sql.Date;
import java.util.List;
import su90.mybatisdemo.dao.domain.Department;
import su90.mybatisdemo.dao.domain.Employee;
import su90.mybatisdemo.dao.domain.Job;

/**
 *
 * @author superman90
 */
public class EmployeeIn {
    public Long id;
    public String fname;
    public String lname;
    public String email;
    public String phone;
    public Date hiredate;
    public String job_id;
    public Double salary;
    public Double comm;
    public Long manager_id;
    public Long department_id;

    public EmployeeIn() {
    }

    public EmployeeIn(String fname, String lname, String email, String phone, Date hiredate, String job_id, Double salary, Double comm, Long manager_id, Long department_id) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.hiredate = hiredate;
        this.job_id = job_id;
        this.salary = salary;
        this.comm = comm;
        this.manager_id = manager_id;
        this.department_id = department_id;
    }

    public EmployeeIn(Long id, String fname, String lname, String email, String phone, Date hiredate, String job_id, Double salary, Double comm, Long manager_id, Long department_id) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.hiredate = hiredate;
        this.job_id = job_id;
        this.salary = salary;
        this.comm = comm;
        this.manager_id = manager_id;
        this.department_id = department_id;
    }

    @Override
    public String toString() {
        return "EmployeeIn{" + "id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", phone=" + phone + ", hiredate=" + hiredate + ", job_id=" + job_id + ", salary=" + salary + ", comm=" + comm + ", manager_id=" + manager_id + ", department_id=" + department_id + '}';
    }
    
    public Employee getDomain(){
        Job job = null;
        Employee manager = null;
        Department department = null;
        
        if (job_id!=null){
            job = new Job();
            job.setId(job_id);
        }
        
        if (manager_id!=null){
            manager = new Employee();
            manager.setId(manager_id);
        }
        
        if (department_id!=null){
            department = new Department();
            department.setId(department_id);
        }
        
        return new Employee(fname, lname, email, phone, hiredate, job, 
                salary, comm, manager, department);
    }
    
}
