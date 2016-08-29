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
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import su90.mybatisdemo.dao.domain.Country;
import su90.mybatisdemo.dao.domain.Location;
import static org.apache.ibatis.jdbc.SqlBuilder.*;
import su90.mybatisdemo.dao.base.BaseMapper;

/**
 *
 * @author superman90
 */
@Mapper
public interface LocationsMapper extends BaseMapper<Location, Long, Location>{
    static public class SqlBuilderHelper{
        public String buildFindByRawProperties(Location location){
            BEGIN();
            if (location.isValidated()){
                SELECT("*");
                FROM("locations");
//                address!=null&&!address.isEmpty())||
                if (location.getAddress()!=null&&!location.getAddress().isEmpty()){
                    WHERE("upper(street_address) like upper(#{address})");
                }
//                (postal_code!=null&&!postal_code.isEmpty())||
                if (location.getPostal_code()!=null&&!location.getPostal_code().isEmpty()){
                    WHERE("upper(postal_code) like upper(#{postal_code})");
                }
//                (city!=null&&!city.isEmpty())||
                if (location.getCity()!=null&&!location.getCity().isEmpty()){
                    WHERE("upper(city) like upper(#{city})");
                }
//                (province!=null&&province.isEmpty())||
                if (location.getProvince()!=null&&!location.getProvince().isEmpty()){
                    WHERE("upper(state_province) like upper(#{province})");
                }
//                (country!=null&&country.getId()!=null&&!country.getId().isEmpty());
                if (location.getCountry()!=null&&!location.getCountry().getId().isEmpty()){
                    WHERE("country_id = #{country.id}");
                }
            }else{
                SELECT("dummy");
                FROM("dual");
            }
            return SQL();
        }
    }
    
    @Select("select * from locations")
    @Results( value={
            @Result(property = "id",column = "location_id"),
            @Result(property = "address", column = "street_address"),
            @Result(property = "postal_code",column = "postal_code"),
            @Result(property = "city",column = "city"),
            @Result(property = "province",column = "state_province"),
            @Result(property = "country",column = "country_id",
                    javaType = Country.class,
                    one = @One(
                            select = "su90.mybatisdemo.dao.mapper.CountriesMapper.findById",
                            fetchType = FetchType.LAZY
                    )
                    )
    })
    @Override
    List<Location> findAll();
    
    @Select("select * from locations where location_id = #{id}")
    @Results( value={
            @Result(property = "id",column = "location_id"),
            @Result(property = "address", column = "street_address"),
            @Result(property = "postal_code",column = "postal_code"),
            @Result(property = "city",column = "city"),
            @Result(property = "province",column = "state_province"),
            @Result(property = "country",column = "country_id",
                    javaType = Country.class,
                    one = @One(
                            select = "su90.mybatisdemo.dao.mapper.CountriesMapper.findById",
                            fetchType = FetchType.LAZY
                    )
                    )
    })
    @Override
    Location findById(Long id);
    
    @SelectProvider(type = SqlBuilderHelper.class, method = "buildFindByRawProperties")
    @Results( value={
            @Result(property = "id",column = "location_id"),
            @Result(property = "address", column = "street_address"),
            @Result(property = "postal_code",column = "postal_code"),
            @Result(property = "city",column = "city"),
            @Result(property = "province",column = "state_province"),
            @Result(property = "country",column = "country_id",
                    javaType = Country.class,
                    one = @One(
                            select = "su90.mybatisdemo.dao.mapper.CountriesMapper.findById",
                            fetchType = FetchType.LAZY
                    )
                    )
    })
    @Override
    List<Location> findByRawProperties(Location location);

    @Override
    public List<Location> findByRawType(Location bean);
    
    @Override
    public Long count();

    @Override
    public void deleteById(Long id);

    @Override
    public void updateOne(Location bean);

    @Override
    public void insertOne(Location bean);

    
}
