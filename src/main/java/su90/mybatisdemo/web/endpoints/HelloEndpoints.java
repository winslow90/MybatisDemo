/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.web.endpoints;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author superman90
 */
@RestController
@RequestMapping(value = "/hello")
public class HelloEndpoints {
    
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String sayHellow(@PathVariable String name){
        return "Hello"+name+"~";
    }
    
}
