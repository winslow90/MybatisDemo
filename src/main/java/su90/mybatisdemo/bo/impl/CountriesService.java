/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su90.mybatisdemo.bo.base.BaseServiceImpl;
import su90.mybatisdemo.dao.base.BaseMapper;
import su90.mybatisdemo.dao.domain.Country;
import su90.mybatisdemo.dao.mapper.CountriesMapper;
import su90.mybatisdemo.web.beans.CountryBean;

/**
 *
 * @author superman90
 */
@Service
@Transactional
public class CountriesService extends BaseServiceImpl<Country, String, CountryBean, Country>{

    @Autowired
    CountriesMapper countriesMapper;
    
    @Override
    public BaseMapper<Country, String, CountryBean, Country> getBaseMapper() {
        return countriesMapper;
    }
    
}
