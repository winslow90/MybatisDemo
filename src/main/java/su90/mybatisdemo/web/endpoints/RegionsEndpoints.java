/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.web.endpoints;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import su90.mybatisdemo.bo.RegionsService;
import su90.mybatisdemo.bo.impl.RegionsServiceImpl;
import su90.mybatisdemo.dao.domain.Region;
import su90.mybatisdemo.web.beans.RegionBean;

/**
 *
 * get      read
 * put      create      idempotent
 * post     update
 * delete   delete
 * option   list support operations
 * @author superman90
 */
@RestController
@RequestMapping(value = "api/regions")
public class RegionsEndpoints {
    
    @Autowired
    RegionsService regionsService;
    
    @RequestMapping(value = "/getall", method = RequestMethod.GET, 
            produces = {"application/json"})
    @ApiOperation(value = "Find all regions",
            httpMethod = "GET"
            )    
    public List<RegionBean> getAll(){
        return regionsService.getWebBeans();
    }
    
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET,
            produces = {"application/json"})
    @ApiOperation(value = "find one region")
    public RegionBean getOne(@PathVariable Long id){
        return regionsService.getWebBeanById(id);
    }
    
}
