/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su90.mybatisdemo.bo.base.BaseServiceImpl;
import su90.mybatisdemo.dao.base.BaseMapper;
import su90.mybatisdemo.dao.domain.Region;
import su90.mybatisdemo.dao.mapper.RegionsMapper;

/**
 *
 * @author superman90
 */
@Service
public class RegionsService extends BaseServiceImpl<Region, Long, Region>{
    
    @Autowired
    RegionsMapper regionsMapper;

    @Override
    public BaseMapper<Region, Long, Region> getBaseMapper() {
        return regionsMapper;
    }
    
}
