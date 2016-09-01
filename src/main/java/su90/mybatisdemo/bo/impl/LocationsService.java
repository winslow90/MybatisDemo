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
import su90.mybatisdemo.dao.domain.Location;
import su90.mybatisdemo.dao.mapper.LocationsMapper;
import su90.mybatisdemo.web.beans.LocationBean;

/**
 *
 * @author superman90
 */
@Service
@Transactional
public class LocationsService extends BaseServiceImpl<Location, Long, LocationBean, Location>{

    @Autowired
    LocationsMapper locationsMapper;
    
    @Override
    public BaseMapper<Location, Long, LocationBean, Location> getBaseMapper() {
        return locationsMapper;
    }
    
}
