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
public class LocationBean {
    Long id;
    String address;
    String postal_code;
    String city;
    String province;
    Href country;

    public LocationBean() {
    }

    public LocationBean(String address, String postal_code, String city, String province, Href country) {
        this.address = address;
        this.postal_code = postal_code;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public LocationBean(Long id, String address, String postal_code, String city, String province, Href country) {
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

    public Href getCountry() {
        return country;
    }

    public void setCountry(Href country) {
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
            sb.append(country.getHref());
            sb.append('}');        
        return sb.toString();
    }
    
}
