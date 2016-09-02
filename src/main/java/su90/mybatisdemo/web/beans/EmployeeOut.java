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
public class EmployeeOut {
    public Long id;
    public String fname;
    public String lname;
    public String email;
    public String phone;
    public Date hiredate;
    public JobBean job;
    public Double salary;
    public Double comm;
    public Href manager;
    public Href department;   

    public EmployeeOut() {
    }

    public EmployeeOut(String fname, String lname, String email, String phone, Date hiredate, JobBean job, Double salary, Double comm, Href manager, Href department) {
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

    public EmployeeOut(Long id, String fname, String lname, String email, String phone, Date hiredate, JobBean job, Double salary, Double comm, Href manager, Href department) {
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

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", fname=" + fname + ", lname=" + 
                lname + ", email=" + email + ", phone=" + phone + ", hiredate=" +
                hiredate + ", job_id=" + job.toString()+ ", salary=" + salary + 
                ", comm=" + comm + ", manager_id=" + manager.getHref()+ 
                ", department_id=" + department.getHref()+ '}';
    }
}
