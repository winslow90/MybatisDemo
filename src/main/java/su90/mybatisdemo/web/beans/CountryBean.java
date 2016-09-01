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
public class CountryBean {
    String id;
    String name;
    Href region;    

    public CountryBean() {
    }

    public CountryBean(String name, Href region) {
        this.name = name;
        this.region = region;
    }

    public CountryBean(String id, String name, Href region) {
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

    public Href getRegion() {
        return region;
    }

    public void setRegion(Href region) {
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
            sb.append(region.getHref());
            sb.append('}');        
        return sb.toString();
    }
    
}
