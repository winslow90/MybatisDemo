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
public class Country implements BaseDomain<String>, Serializable{

    String id;
    String name;
    Region region;    

    public Country() {
    }

    public Country(String name, Region region) {
        this.name = name;
        this.region = region;
    }

    public Country(String id, String name, Region region) {
        this.id = id;
        this.name = name;
        this.region = region;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();        
            sb.append('{');
            sb.append("country_id:");
            sb.append(id);
            sb.append("country_name:");
            sb.append(name);
            sb.append("region_id:");
            sb.append(region.getId());
            sb.append('}');        
        return sb.toString();
    }
    
    
    public Boolean isValidated(){
        return (name!=null&&!name.isEmpty())||(region!=null&&region.getId()!=null);
    }

    @Override
    public String getKey() {
        return this.id;
    }

    @Override
    public Boolean hasValidatedKey() {
        return this.id!=null;
    }

    @Override
    public void setKey(String key) {
        this.id=key;
    }
}
