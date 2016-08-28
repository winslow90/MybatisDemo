/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.web.endpoints;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import su90.mybatisdemo.bo.impl.RegionsService;
import su90.mybatisdemo.dao.domain.Region;

/**
 *
 * @author superman90
 */
@RestController
@RequestMapping(value = "/regions")
public class RegionsController {
    
    @Autowired
    RegionsService regionsService;
    
    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public List<Region> findAllRegions(){
        return regionsService.getEntries();
    }
    
}
