/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import su90.mybatisdemo.dao.domain.Regions;

/**
 *
 * @author superman90
 */
@Mapper
public interface RegionsMapper {        
    
    @Select("select * from regions")
    @Results(value={
            @Result(property = "id",column = "region_id"),
            @Result(property = "name",column = "region_name")
    })
    List<Regions> findAll();
    
    @Select("select region_id,region_name from regions where region_id = #{id}")
    @Results(value={
            @Result(property = "id",column = "region_id"),
            @Result(property = "name",column = "region_name")
    })
    Regions findById(@Param("id") Long id);
    
//    ParameterMapping{property='sername', mode=IN, javaType=class java.lang.String, jdbcType=CHAR, numericScale=null, resultMapId='null', jdbcTypeName='null', expression='null'}
    @Select("select region_id,region_name from regions where upper(region_name) like upper(#{searchstr})")
    @Results(value={
            @Result(property = "id",column = "region_id"),
            @Result(property = "name",column = "region_name")
    })
    List<Regions> findByName(String searchstr);
    
    @Insert("insert into regions(region_id,region_name) values(#{id},#{name})") //p62 samples for @selectkey
    @SelectKey(statement = "select REGIONS_SEQ.NEXTVAL from dual",keyProperty ="id",resultType = Long.class,before = true)
    void insertOneRegion(Regions region);
    
}
