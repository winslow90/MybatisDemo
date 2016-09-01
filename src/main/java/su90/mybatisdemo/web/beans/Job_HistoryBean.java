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
public class Job_HistoryBean {
    public Href employee;
    public Date start_date;
    public Date end_date;
    public Href job;
    public Href department;
    public Job_HistoryBean.Key key;

    
    public static class Key{
        public Long employee_id;
        public Date start_date;


        public Key() {
        }

        public Key(Long employee_id, Date start_date) {
            this.employee_id = employee_id;
            this.start_date = start_date;
        }
        
        public Long getEmployee_id() {
            return employee_id;
        }

        public void setEmployee_id(Long employee_id) {
            this.employee_id = employee_id;
        }

        public Date getStart_date() {
            return start_date;
        }

        public void setStart_date(Date start_date) {
            this.start_date = start_date;
        }
        
    }
    
    public Job_HistoryBean() {
    }

    public Job_HistoryBean(Date start_date, Date end_date, Href job, Href department) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.job = job;
        this.department = department;
    }

    public Job_HistoryBean(Href employee, Date start_date, Date end_date, Href job, Href department) {
        this.employee = employee;
        this.start_date = start_date;
        this.end_date = end_date;
        this.job = job;
        this.department = department;
    }

    public Href getEmployee() {
        return employee;
    }

    public void setEmployee(Href employee) {
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

    public Href getJob() {
        return job;
    }

    public void setJob(Href job) {
        this.job = job;
    }

    public Href getDepartment() {
        return department;
    }

    public void setDepartment(Href department) {
        this.department = department;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
    
    @Override
    public String toString() {
        return "Job_History{" + "employee_id=" + employee.getHref()+ 
                ", start_date=" + start_date + ", end_date=" + end_date + 
                ", job_id=" + job.getHref()+ 
                ", department_id=" + department.getHref()+ '}';
    }
    
}
