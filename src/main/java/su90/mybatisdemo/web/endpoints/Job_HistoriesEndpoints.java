/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.web.endpoints;

import io.swagger.annotations.ApiOperation;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import su90.mybatisdemo.bo.Job_HistoryService;
import su90.mybatisdemo.bo.impl.Job_HistoryServiceImpl;
import su90.mybatisdemo.web.beans.Job_HistoryBean;

/**
 *
 * @author superman90
 */
@RestController
@RequestMapping(value = "/api/job_histories")
public class Job_HistoriesEndpoints {
    
    @Autowired
    Job_HistoryService job_HistoryService;
    
    @RequestMapping(value = "/get/{employ_id}/start_date/{startdatestr}", method = RequestMethod.GET,
            produces = {"application/json"})
    @ApiOperation(value = "find one job_histoy")
    public Job_HistoryBean getOne(@PathVariable Long employee_id,@PathVariable  Date startdatestr){
        return new Job_HistoryBean();
    }
    
}
