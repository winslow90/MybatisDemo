/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.web.endpoints;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import su90.mybatisdemo.bo.JobsService;
import su90.mybatisdemo.bo.impl.JobsServiceImpl;
import su90.mybatisdemo.web.beans.JobBean;

/**
 *
 * @author superman90
 */
@RestController
@RequestMapping(value = "/api/jobs")
public class JobsEndpoints {
    
    @Autowired
    JobsService jobsService;
    
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET,
            produces = {"application/json"})
    @ApiOperation(value = "find one job")
    public JobBean getOne(@PathVariable String id){
        return jobsService.getWebBeanById(id);
    }
    
}
