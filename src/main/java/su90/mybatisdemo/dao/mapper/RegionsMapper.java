/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import static org.apache.ibatis.jdbc.SqlBuilder.*;
import su90.mybatisdemo.dao.base.BaseMapper;
import su90.mybatisdemo.dao.domain.Region;
import su90.mybatisdemo.web.beans.RegionBean;

/**
 *
 * @author superman90
 */
@Mapper
public interface RegionsMapper extends BaseMapper<Region, Long,RegionBean, Region>{
    
    static public class SqlBuilderHelper{        
        public String buildUpdateString(Region region){
            BEGIN();
            if (region.getName()!=null && !region.getName().isEmpty()){
                UPDATE("regions re");
                SET("re.region_name=#{name}");
                WHERE("re.region_id=#{id}");                                    
            }else{
                SELECT("dummy");
                FROM("dual");
            }
            return SQL();
        }
        public String findByRawType(Region region){
            BEGIN();
            if (region.isValidated()||region.hasValidatedKey()){
                SELECT("*");
                FROM("regions");
                if (region.getId()!=null){
                    WHERE("region_id = #{id}");
                }
                if (region.getName()!=null){
                    WHERE("region_name = #{name}");
                }
                
            }else{
                SELECT("dummy");
                FROM("dual");
            }
            return SQL();
        }
    }
    
//    @Select("select * from regions where region_id=#{region_id}")
//    @Results(value={
//            @Result(property = "id",column = "region_id"),
//            @Result(property = "name",column = "region_name")
//    })
//    @Options(useCache = true)        
//    Region _doSelectRegion(Long region_id);
    
    @Select("select * from regions")
    @Results(value={
            @Result(property = "id",column = "region_id"),
            @Result(property = "name",column = "region_name")
    })
    @Override
    List<Region> findAll();
    
    @Select("select region_id,region_name from regions where region_id = #{id}")
    @Options(useCache = true)
    @Results(value={
            @Result(property = "id",column = "region_id"),
            @Result(property = "name",column = "region_name")
    })
    @Override
    Region findById(@Param("id") Long id);
    
//    ParameterMapping{property='sername', mode=IN, javaType=class java.lang.String, jdbcType=CHAR, numericScale=null, resultMapId='null', jdbcTypeName='null', expression='null'}
    @Select("select region_id,region_name from regions where upper(region_name) like upper(#{searchstr})")
    @Results(value={
            @Result(property = "id",column = "region_id"),
            @Result(property = "name",column = "region_name")
    })
    List<Region> findByName(String searchstr);
    
    @Select("select region_id,region_name from regions where upper(region_name) like upper(#{name})")
    @Results(value={
            @Result(property = "id",column = "region_id"),
            @Result(property = "name",column = "region_name")
    })
    @Override
    List<Region> findByRawProperties(Region region);
    
    @Insert("insert into regions(region_id,region_name) values(#{id},#{name})") //p62 samples for @selectkey
    @SelectKey(statement = "select REGIONS_SEQ.NEXTVAL from dual",keyProperty ="id",resultType = Long.class,before = true)
    @Override
    void insertOne(Region region);
    
    @UpdateProvider(type = SqlBuilderHelper.class,method ="buildUpdateString")
    @Override
    void updateOne(Region region);
    
    @Delete("delete from regions where region_id =#{id}")
    @Override
    void deleteById(Long id);
    
    @Delete("delete from regions where region_id =#{id}")
    void deleteByRegionId(Region region);
    
    @Select("select count(*) from regions")
    @Override
    Long count();

    @SelectProvider(type = SqlBuilderHelper.class, method ="findByRawType")
    @Results(value={
            @Result(property = "id",column = "region_id"),
            @Result(property = "name",column = "region_name")
    })
    @Override
    public List<Region> findByRawType(Region bean);
    
}
