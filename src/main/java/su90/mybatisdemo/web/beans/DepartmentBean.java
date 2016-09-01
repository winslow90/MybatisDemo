/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.web.beans;

/**
 *
 * @author superman90
 */
public class DepartmentBean {
    Long id;
    String name;
    Href manager;
    Href location;

    public DepartmentBean() {
    }

    public DepartmentBean(String name, Href manager, Href location) {
        this.name = name;
        this.manager = manager;
        this.location = location;
    }

    public DepartmentBean(Long id, String name, Href manager, Href location) {
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

    public Href getManager() {
        return manager;
    }

    public void setManager(Href manager) {
        this.manager = manager;
    }

    public Href getLocation() {
        return location;
    }

    public void setLocation(Href location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", manager_id=" + manager.getHref()+ ", location_id=" + location.getHref()+ '}';
    }
}
