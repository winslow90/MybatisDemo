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
public class Location implements Serializable{
    
    Long id;
    String address;
    String postal_code;
    String city;
    String province;
    Country country;

    public Location() {
    }

    public Location(String address, String postal_code, String city, String province, Country country) {
        this.address = address;
        this.postal_code = postal_code;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public Location(Long id, String address, String postal_code, String city, String province, Country country) {
        this.id = id;
        this.address = address;
        this.postal_code = postal_code;
        this.city = city;
        this.province = province;
        this.country = country;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();        
            sb.append('{');
            sb.append("location_id:");
            sb.append(id);
            sb.append("street_address:");
            sb.append(address);
            sb.append("postal_code:");
            sb.append(postal_code);
            sb.append("city:");
            sb.append(city);
            sb.append("state_province:");
            sb.append(province);
            sb.append("country:");
            sb.append(country);
            sb.append('}');        
        return sb.toString();
    }
    
    public Boolean isValidated(){
        return (address!=null&&!address.isEmpty())||
                (postal_code!=null&&!postal_code.isEmpty())||
                (city!=null&&!city.isEmpty())||
                (province!=null&&province.isEmpty())||
                (country!=null&&country.getId()!=null&&!country.getId().isEmpty());
    }
    
}
