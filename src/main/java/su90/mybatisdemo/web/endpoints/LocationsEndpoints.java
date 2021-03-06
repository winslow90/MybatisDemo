/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.web.endpoints;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import su90.mybatisdemo.bo.LocationsService;
import su90.mybatisdemo.bo.impl.LocationsServiceImpl;
import su90.mybatisdemo.web.beans.LocationBean;

/**
 *
 * @author superman90
 */
@RestController
@RequestMapping(value = {"/api/locations"})
public class LocationsEndpoints {
    
    @Autowired
    LocationsService locationsService;
    
    @RequestMapping(value = "/getall", method = RequestMethod.GET, 
            produces = {"application/json"})
    @ApiOperation(value = "Find all locations",
            httpMethod = "GET"
            )
    
    public List<LocationBean> getAll(){
        return locationsService.getWebBeans();
    }
    
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET,
            produces = {"application/json"})
    @ApiOperation(value = "findspecify region")
    public LocationBean getOne(@PathVariable Long id){
        return locationsService.getWebBeanById(id);
    }
    
}
