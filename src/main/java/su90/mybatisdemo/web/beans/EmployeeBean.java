/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.web.beans;

import java.sql.Date;

/**
 *
 * @author superman90
 */
public class EmployeeBean {
    public Long id;
    public String fname;
    public String lname;
    public String email;
    public String phone;
    public Date hiredate;
    public Href job;
    public Double salary;
    public Double comm;
    public Href manager;
    public Href department;   

    public EmployeeBean() {
    }

    public EmployeeBean(String fname, String lname, String email, String phone, Date hiredate, Href job, Double salary, Double comm, Href manager, Href department) {
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

    public EmployeeBean(Long id, String fname, String lname, String email, String phone, Date hiredate, Href job, Double salary, Double comm, Href manager, Href department) {
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

    public Href getJob() {
        return job;
    }

    public void setJob(Href job) {
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

    public Href getManager() {
        return manager;
    }

    public void setManager(Href manager) {
        this.manager = manager;
    }

    public Href getDepartment() {
        return department;
    }

    public void setDepartment(Href department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", fname=" + fname + ", lname=" + 
                lname + ", email=" + email + ", phone=" + phone + ", hiredate=" +
                hiredate + ", job_id=" + job.getHref()+ ", salary=" + salary + 
                ", comm=" + comm + ", manager_id=" + manager.getHref()+ 
                ", department_id=" + department.getHref()+ '}';
    }
}
