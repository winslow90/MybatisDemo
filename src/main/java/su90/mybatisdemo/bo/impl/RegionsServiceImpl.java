/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su90.mybatisdemo.bo.RegionsService;
import su90.mybatisdemo.bo.base.BaseServiceImpl;
import su90.mybatisdemo.dao.base.BaseMapper;
import su90.mybatisdemo.dao.domain.Region;
import su90.mybatisdemo.dao.mapper.RegionsMapper;
import su90.mybatisdemo.web.beans.RegionBean;

/**
 *
 * @author superman90
 */
@Service
@Transactional
public class RegionsServiceImpl 
        extends BaseServiceImpl<Region, Long,RegionBean, Region>
        implements RegionsService
{
    
    @Autowired
    RegionsMapper regionsMapper;

    @Override
    public BaseMapper<Region, Long,RegionBean, Region> getBaseMapper() {
        return regionsMapper;
    }
    
}
