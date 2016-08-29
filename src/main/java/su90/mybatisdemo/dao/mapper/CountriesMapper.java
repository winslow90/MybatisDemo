/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import static org.apache.ibatis.jdbc.SqlBuilder.*;
import org.apache.ibatis.mapping.FetchType;
import su90.mybatisdemo.dao.base.BaseMapper;
import su90.mybatisdemo.dao.domain.Country;
import su90.mybatisdemo.dao.domain.Region;

/**
 *
 * @author superman90
 */
@Mapper
public interface CountriesMapper extends BaseMapper<Country, String, Country>{
    
    static public class SqlBuilderHelper{
        public String buildFindByRawProperties(Country country){
            BEGIN();
            if (country.isValidated()){
                SELECT("*");
                FROM("countries co");
                if (country.getName()!=null&&!country.getName().isEmpty()){
                    WHERE("upper(co.country_name) like upper(#{name})");
                }
                if (country.getRegion()!=null&&country.getRegion().getId()!=null){
                    WHERE("co.region_id = #{region.id}");
                }
            }else{
                SELECT("dummy");
                FROM("dual");
            }
            return SQL();
        }
    }
    
    @Select("select * from countries")
    @Results(value ={
        @Result(property = "id",column = "country_id"),
        @Result(property = "name",column = "country_name"),
        @Result(property = "region",column = "region_id",
                javaType = Region.class,
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.RegionsMapper.findById",
                        fetchType = FetchType.LAZY
                ))
    })
    @Override
    List<Country> findAll();
    
    @Select("select * from countries where country_id = #{id}")
    @Results(value ={
        @Result(property = "id",column = "country_id"),
        @Result(property = "name",column = "country_name"),
        @Result(property = "region",column = "region_id",
                javaType = Region.class,
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.RegionsMapper.findById",
                        fetchType = FetchType.LAZY
                ))
    })
    @Override
    Country findById(String id);
    
    @SelectProvider(type = SqlBuilderHelper.class,method ="buildFindByRawProperties")
    @Results(value ={
        @Result(property = "id",column = "country_id"),
        @Result(property = "name",column = "country_name"),
        @Result(property = "region",column = "region_id",
                javaType = Region.class,
                one = @One(
                        select = "su90.mybatisdemo.dao.mapper.RegionsMapper.findById",
                        fetchType = FetchType.LAZY
                ))
    })
    @Override
    List<Country> findByRawProperties(Country country);
    
//    @Insert("")
//    void insertOneCountry(Country country);

    @Select("select count(*) from countries")
    @Override
    public Long count();
    
    
    @Override
    public List<Country> findByRawType(Country bean);

    @Override
    public void insertOne(Country bean);
    
    @Override
    public void deleteById(String id);

    @Override
    public void updateOne(Country bean);
    
}