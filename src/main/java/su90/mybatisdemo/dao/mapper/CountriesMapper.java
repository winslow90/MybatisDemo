/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import static org.apache.ibatis.jdbc.SqlBuilder.*;
import org.apache.ibatis.mapping.FetchType;
import su90.mybatisdemo.dao.base.BaseMapper;
import su90.mybatisdemo.dao.domain.Country;
import su90.mybatisdemo.dao.domain.Region;
import su90.mybatisdemo.dao.ex.InvalidBeanException;
import su90.mybatisdemo.dao.ex.KeyAbsentException;

/**
 *
 * @author superman90
 */
@Mapper
public interface CountriesMapper extends BaseMapper<Country, String, Country>{
    
    static class SqlBuilderHelper{
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
        
        public String buildFindByRawType(Country country){
            BEGIN();
            if (country.hasValidatedKey()||country.isValidated()){
                SELECT("*");
                FROM("countries");
                if (country.getId()!=null){
                    WHERE("country_id = #{id}");
                }
                if (country.getName()!=null&&!country.getName().isEmpty()){
                    WHERE("country_name = #{name}");
                }
                if (country.getRegion()!=null&&country.getRegion().getId()!=null){
                    WHERE("region_id = #{region.id}");
                }
            }else{
                SELECT("dummy");
                FROM("dual");
            }
            return SQL();
        }
        
        public String buildInsertOne(Country country){
            BEGIN();
            if (country.hasValidatedKey()&&country.isValidated()){
                INSERT_INTO("countries");
                VALUES("country_id", "#{id}");
                if (country.getName()!=null&&!country.getName().isEmpty()){
                    VALUES("country_name", "#{name}");
                }
                if (country.getRegion()!=null&&country.getRegion().getId()!=null){
                    VALUES("region_id", "#{region.id}");
                }                
            }else{
                if (country.hasValidatedKey()){
                    throw new InvalidBeanException("The Country bean to be inserted is invalid");
                }else{
                    throw new KeyAbsentException("The Country bean has to have an bean");
                }
            }
            return SQL();
        }
        
        public String buildUpdateOne(Country country){
            BEGIN();
            if (country.hasValidatedKey()&&country.isValidated()){
                UPDATE("countries");
                if (country.getName()!=null&&!country.getName().isEmpty()){
                    SET("country_name=#{name}");
                }
                if (country.getRegion()!=null&&country.getRegion().getId()!=null){
                    SET("region_id=#{region.id}");
                }
                WHERE("country_id = #{id}");
            }else{
                if (country.hasValidatedKey()){
                    throw new InvalidBeanException("The Country bean to be inserted is invalid");
                }else{
                    throw new KeyAbsentException("The Country bean has to have an bean");
                }
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
    
    @SelectProvider(type = SqlBuilderHelper.class, method = "buildFindByRawType")
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
    public List<Country> findByRawType(Country country);
    
    @InsertProvider(type = SqlBuilderHelper.class, method = "buildInsertOne")
    @Override
    public void insertOne(Country country);
    
    @UpdateProvider(type = SqlBuilderHelper.class, method = "buildUpdateOne")
    @Override
    public void updateOne(Country country);
    
    @Delete("delete from countries where country_id = #{id}")
    @Override
    public void deleteById(@Param("id") String id);
    
}