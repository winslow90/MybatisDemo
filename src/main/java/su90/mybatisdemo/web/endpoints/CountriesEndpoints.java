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
import su90.mybatisdemo.bo.impl.CountriesService;
import su90.mybatisdemo.web.beans.CountryBean;

/**
 *
 * @author superman90
 */
@RestController
@RequestMapping(value = "/api/countries")
public class CountriesEndpoints {
    
    @Autowired
    CountriesService countriesService;
    
    @RequestMapping(value = "/getall", method = RequestMethod.GET, 
            produces = {"application/json"})
    @ApiOperation(value = "Find all countries",
            httpMethod = "GET"
            )   
    public List<CountryBean> getAll(){
        return countriesService.getWebBeans();
    }
    
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET,
            produces = {"application/json"})
    @ApiOperation(value = "findspecify country")
    public CountryBean getOne(@PathVariable String id){
        return countriesService.getWebBeanById(id);
    }
    
}
