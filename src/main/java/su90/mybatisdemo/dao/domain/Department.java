/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.domain;

import java.io.Serializable;

/**
 *
 * @author superman90
 */
public class Department implements Serializable{
    
    Long id;
    String name;
    Employee manager;
    Location location;

    public Department() {
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
        return "Department{" + "id=" + id + ", name=" + name + ", manager=" + manager + ", location=" + location + '}';
    }
    
    public Boolean isValidated(){
        throw new UnsupportedOperationException("TODO: unfinished yet");
    }
}
