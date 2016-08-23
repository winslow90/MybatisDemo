/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import su90.mybatisdemo.dao.domain.Regions;

/**
 *
 * @author superman90
 */
@Mapper
public interface RegionsMapper {
    @Select("select * from regions where region_id = #{id}")
    Regions findById(@Param("id") Long id);
}
