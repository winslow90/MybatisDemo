/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Date;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import su90.mybatisdemo.dao.base.BaseDomain;
import su90.mybatisdemo.utils.UriUtils;
import su90.mybatisdemo.web.beans.Href;
import su90.mybatisdemo.web.beans.Job_HistoryBean;
import su90.mybatisdemo.web.endpoints.DepartmentsEndpoints;
import su90.mybatisdemo.web.endpoints.EmployeesEndpoints;
import su90.mybatisdemo.web.endpoints.JobsEndpoints;

/**
 *
 * @author superman90
 */
public class Job_History implements BaseDomain<Job_History.Key,Job_HistoryBean>, Serializable{
    
    public Employee employee;
    public Date start_date;
    public Date end_date;
    public Job job;
    public Department department;
    
    private Job_History.Key thekey=null;


    public static class Key{
        
        public Employee employee;
        public Date start_date;

        public Key() {
        }

        public Key(Employee employee, Date start_date) {
            this.employee = employee;
            this.start_date = start_date;
        }
        
        public Employee getEmployee() {
            return employee;
        }

        public void setEmployee(Employee employee) {
            this.employee = employee;
        }

        public Date getStart_date() {
            return start_date;
        }

        public void setStart_date(Date start_date) {
            this.start_date = start_date;
        }
        
    }

    public Job_History() {
    }

    public Job_History(Date start_date, Date end_date, Job job, Department department) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.job = job;
        this.department = department;
    }

    public Job_History(Employee employee, Date start_date, Date end_date, Job job, Department department) {
        this.employee = employee;
        this.start_date = start_date;
        this.end_date = end_date;
        this.job = job;
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Job_History{" + "employee_id=" + employee.getId() + 
                ", start_date=" + start_date + ", end_date=" + end_date + 
                ", job_id=" + job.getId() + 
                ", department_id=" + department.getId() + '}';
    }
    
    @JsonIgnore
    public Boolean isValidated(){
        return 
                (employee!=null&& employee.getId()!=null)||
                (start_date!=null)||
                (end_date!=null)||
                (job!=null&&job.getId()!=null)||
                (department!=null&&department.getId()!=null);
    }
    
    @Override
    @JsonIgnore
    public Key getKey() {
        return thekey==null?
                thekey = new Job_History.Key(this.employee,this.start_date):
                thekey;
    }
    
    @Override
    public Boolean hasValidatedKey() {
        return this.employee!=null&&this.employee.getId()!=null&&this.start_date!=null;
    }
    
    @Override
    @JsonIgnore
    public void setKey(Key key) {
        this.employee=key.getEmployee();
        this.start_date=key.getStart_date();
    }
    
    @Override
    public Job_HistoryBean getWebBean() {
        Href employeeHref=null;
        Href jobHref=null;
        Href departmentHref=null;
        
        if (getEmployee()!=null){
            employeeHref = UriUtils.generateHref(MvcUriComponentsBuilder.on(
                    EmployeesEndpoints.class).getOne(getEmployee().getId()));
        }
        
        if (getJob()!=null){
            jobHref = UriUtils.generateHref(MvcUriComponentsBuilder.on(
                    JobsEndpoints.class).getOne(getJob().getId()));
        }
        
        if (getDepartment()!=null){
            departmentHref = UriUtils.generateHref(MvcUriComponentsBuilder.on(
                    DepartmentsEndpoints.class).getOne(getDepartment().getId()));
        }
        
        Job_HistoryBean result = new Job_HistoryBean(employeeHref, start_date, end_date, jobHref, departmentHref);
        result.setKey(new Job_HistoryBean.Key(getEmployee().getId(), start_date));
        return result;
    }
    
}
