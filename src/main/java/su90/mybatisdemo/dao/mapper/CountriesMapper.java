/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import su90.mybatisdemo.dao.domain.Country;
import su90.mybatisdemo.dao.domain.Region;

/**
 *
 * @author superman90
 */
@Mapper
public interface CountriesMapper {
    
    static public class SqlBuilderHelper{
    }
    
    @Select("select * from countries")
    @Results(value ={
        @Result(property = "id",column = "country_id"),
        @Result(property = "name",column = "country_name"),
        @Result(property = "region",column = "region_id",
                javaType = Region.class,
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.RegionsMapper._doSelectRegion",
                        fetchType = FetchType.LAZY
                ))
    })
    List<Country> findAll();
    
    
}