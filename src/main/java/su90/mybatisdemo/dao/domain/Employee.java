/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Date;
import su90.mybatisdemo.dao.base.BaseDomain;

/**
 *
 * @author superman90
 */
public class Employee implements BaseDomain<Long> ,Serializable{

    public Long id;
    public String fname;
    public String lname;
    public String email;
    public String phone;
    public Date hiredate;
    public Job job;
    public Double salary;
    public Double comm;
    public Employee manager;
    public Department department;   

    public Employee() {
    }

    public Employee(String fname, String lname, String email, String phone, Date hiredate, Job job, Double salary, Double comm, Employee manager, Department department) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.hiredate = hiredate;
        this.job = job;
        this.salary = salary;
        this.comm = comm;
        this.manager = manager;
        this.department = department;
    }

    public Employee(Long id, String fname, String lname, String email, String phone, Date hiredate, Job job, Double salary, Double comm, Employee manager, Department department) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
        this.hiredate = hiredate;
        this.job = job;
        this.salary = salary;
        this.comm = comm;
        this.manager = manager;
        this.department = department;
    }
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getComm() {
        return comm;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", phone=" + phone + ", hiredate=" + hiredate + ", job_id=" + job.getId() + ", salary=" + salary + ", comm=" + comm + ", manager_id=" + manager.getId() + ", department_id=" + department.getId() + '}';
    }
    
    @JsonIgnore
    public Boolean isValidated(){
        return 
                (fname!=null&&!fname.isEmpty())||
                (lname!=null&&!lname.isEmpty())||
                (email!=null&&!email.isEmpty())||
                (phone!=null&&!phone.isEmpty())||
                (hiredate!=null)||
                (job!=null&&job.getId()!=null)||
                (salary!=null&&salary>=0)||
                (comm!=null&&comm>=0)||
                (manager!=null&&manager.getId()!=null)||
                (department!=null&&department.getId()!=null);
    }

    @Override
    @JsonIgnore
    public Long getKey() {
        return this.id;
    }

    @Override
    public Boolean hasValidatedKey() {
        return this.id!=null;
    }

    @Override
    @JsonIgnore
    public void setKey(Long key) {
        this.id = key;
    }
}
