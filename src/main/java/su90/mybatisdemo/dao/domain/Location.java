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
import su90.mybatisdemo.web.beans.Href;
import su90.mybatisdemo.web.beans.LocationBean;
import su90.mybatisdemo.web.endpoints.CountriesEndpoints;

/**
 *
 * @author superman90
 */
public class Location implements BaseDomain<Long, LocationBean>,Serializable{
    
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
            sb.append("country_id:");
            sb.append(country.getId());
            sb.append('}');        
        return sb.toString();
    }
    
    @JsonIgnore
    public Boolean isValidated(){
        return (address!=null&&!address.isEmpty())||
                (postal_code!=null&&!postal_code.isEmpty())||
                (city!=null&&!city.isEmpty())||
                (province!=null&&province.isEmpty())||
                (country!=null&&country.getId()!=null&&!country.getId().isEmpty());
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
    public LocationBean getWebBean() {
//        Href countryHref = null;
//        if (getCountry()!=null){
//            countryHref = UriUtils.generateHref(MvcUriComponentsBuilder.on(
//                    CountriesEndpoints.class).getOne(getCountry().getId()));
//        }
//        return new LocationBean(id, address, postal_code, city, province, countryHref);
        if (getCountry()!=null){
            return new LocationBean(id, address, postal_code, city, province, country.getWebBean());
        }else{
            return new LocationBean(id, address, postal_code, city, province, null);
        }
    }
    
}
