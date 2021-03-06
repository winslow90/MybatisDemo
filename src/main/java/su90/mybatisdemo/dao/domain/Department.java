/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import su90.mybatisdemo.dao.base.BaseDomain;
import su90.mybatisdemo.utils.UriUtils;
import su90.mybatisdemo.web.beans.DepartmentBean;
import su90.mybatisdemo.web.beans.Href;
import su90.mybatisdemo.web.beans.LocationBean;
import su90.mybatisdemo.web.endpoints.EmployeesEndpoints;
import su90.mybatisdemo.web.endpoints.LocationsEndpoints;

/**
 *
 * @author superman90
 */
public class Department implements BaseDomain<Long,DepartmentBean> ,Serializable{
    
    Long id;
    String name;
    Employee manager;
    Location location;

    public Department() {
    }

    public Department(String name, Employee manager, Location location) {
        this.name = name;
        this.manager = manager;
        this.location = location;
    }

    public Department(Long id, String name, Employee manager, Location location) {
        this.id = id;
        this.name = name;
        this.manager = manager;
        this.location = location;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", manager_id=" + manager.getId() + ", location_id=" + location.getId() + '}';
    }
    
    @JsonIgnore
    public Boolean isValidated(){
        return 
                (name!=null&&!name.isEmpty())||
                (manager!=null&&manager.getId()!=null)||
                (location!=null&&location.getId()!=null);
    }

    @Override
    @JsonIgnore
    public Long getKey() {
        return this.id;
    }

    @Override
    public Boolean hasValidatedKey() {
        return this.id!=null;
    }

    @Override
    @JsonIgnore
    public void setKey(Long key) {
        this.id=key;
    }

    @Override
    public DepartmentBean getWebBean() {
        Href managerHref = null;
        LocationBean locationBean = null;
        
        if (getManager()!=null){
            managerHref = UriUtils.generateHref(MvcUriComponentsBuilder.on(
                    EmployeesEndpoints.class).getOne(getManager().getId()));
        }
        if (getLocation()!=null){
//            locationHref = UriUtils.generateHref(MvcUriComponentsBuilder.on(
//                    LocationsEndpoints.class).getOne(getLocation().getId()));
            locationBean = getLocation().getWebBean();
        }


        return new DepartmentBean(id, name, managerHref, locationBean);
    }
}
