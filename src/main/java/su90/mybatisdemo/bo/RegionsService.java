/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo;

import su90.mybatisdemo.bo.base.BaseService;
import su90.mybatisdemo.bo.base.BaseServiceImpl;
import su90.mybatisdemo.dao.base.BaseDomain;
import su90.mybatisdemo.dao.domain.Region;
import su90.mybatisdemo.web.beans.RegionBean;

/**
 *
 * @author superman90
 */
public interface RegionsService extends BaseService<Region, Long,RegionBean, Region>{
    
}
