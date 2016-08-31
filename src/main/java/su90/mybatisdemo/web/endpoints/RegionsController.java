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
import su90.mybatisdemo.bo.impl.RegionsService;
import su90.mybatisdemo.dao.domain.Region;

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
@RequestMapping(value = "/regions")
public class RegionsController {
    
    @Autowired
    RegionsService regionsService;
    
    @RequestMapping(value = "/getall", method = RequestMethod.GET, 
            produces = {"application/json"})
    @ApiOperation(value = "Find all regions",
            httpMethod = "GET"
            )
    
    public List<Region> getAllRegions(){
        return regionsService.getEntries();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = {"application/json"})
    @ApiOperation(value = "findspecify region")
    public Region getOneRegion(@PathVariable Long id){
        return regionsService.getEntryById(id);
    }
    
}
