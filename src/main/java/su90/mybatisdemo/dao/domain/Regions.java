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
public class Regions implements Serializable{
    Long region_id;
    String region_name;
    
    public Regions(){
    }

    public Regions(Long region_id, String region_name) {
        this.region_id = region_id;
        this.region_name = region_name;
    }
    

    public Long getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Long region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();        
            sb.append('{');
            sb.append("region_id:");
            sb.append(region_id);
            sb.append("region_name");
            sb.append(region_name);
            sb.append('}');        
        return sb.toString();
    }
}
