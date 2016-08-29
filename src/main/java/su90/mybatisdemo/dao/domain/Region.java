/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.domain;

import java.io.Serializable;
import su90.mybatisdemo.dao.base.BaseDomain;

/**
 *
 * @author superman90
 */
public class Region implements BaseDomain<Long> ,Serializable{
    Long id;
    String name;

    public Region(){
    }

    public Region(String name) {
        this.name = name;
    }

    public Region(Long region_id, String region_name) {
        this.id = region_id;
        this.name = region_name;
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
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();        
            sb.append('{');
            sb.append("region_id:");
            sb.append(id);
            sb.append("region_name");
            sb.append(name);
            sb.append('}');        
        return sb.toString();
    }
    
    public Boolean isValidated(){
        return (name!=null && !name.isEmpty());
    }

    @Override
    public Long getKey() {
        return this.id;
    }

    @Override
    public Boolean hasValidatedKey() {
        return this.id!=null;
    }

    @Override
    public void setKey(Long key) {
        this.id = key;
    }
}
