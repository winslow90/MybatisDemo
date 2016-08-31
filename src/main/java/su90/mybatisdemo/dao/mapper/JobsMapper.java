/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import su90.mybatisdemo.dao.domain.Job;
import static org.apache.ibatis.jdbc.SqlBuilder.*;
import su90.mybatisdemo.dao.base.BaseMapper;
import su90.mybatisdemo.dao.ex.InvalidBeanException;

/**
 *
 * @author superman90
 */
@Mapper
public interface JobsMapper extends BaseMapper<Job, String, Job>{
    
    static public class SqlBuilderHelper{
        
        public String buildFindByRawType(Job j){
            BEGIN();
            if (j.isValidated()){
                SELECT("*");
                FROM("jobs");
//                (title!=null&&!title.isEmpty())||
                if (j.getTitle()!=null & !j.getTitle().isEmpty()){
                    WHERE("job_title = #{title}");
                }
//                (min_sal!=null&&min_sal>=0&&(max_sal==null||max_sal>=min_sal))||
                if (j.getMin_sal()!=null&&j.getMin_sal()>=0&&(j.getMax_sal()==null||j.getMax_sal()>=j.getMin_sal())){
                    WHERE("min_salary >= #{min_sal}");
                }
//                (max_sal!=null&&max_sal>=0&&(min_sal==null||max_sal>=min_sal))   
                if (j.getMax_sal()!=null&&j.getMax_sal()>=0&&(j.getMin_sal()==null||j.getMax_sal()>=j.getMin_sal())){
                    WHERE("max_salary <= #{max_sal}");
                }
                
            }else{
                SELECT("dummy");
                FROM("dual");
            }
            return SQL();
        }
        
        public String buildFindByRawProperties(Job job){
            BEGIN();
            if (job.isValidated()){
                SELECT("*");
                FROM("jobs");
//                (title!=null&&!title.isEmpty())||
                if (job.getTitle()!=null&&!job.getTitle().isEmpty()){
                    WHERE("upper(job_title) like upper(#{title})");
                }
//                (min_sal!=null&&min_sal>=0&&(max_sal==null||max_sal>=min_sal))||
                if (job.getMin_sal()!=null&&job.getMin_sal()>=0&&(job.getMax_sal()==null||job.getMax_sal()>=job.getMin_sal())){
                    WHERE("min_salary >= #{min_sal}");
                }
//                (max_sal!=null&&max_sal>=0&&(min_sal==null||max_sal>=min_sal))   
                if (job.getMax_sal()!=null&&job.getMax_sal()>=0&&(job.getMin_sal()==null||job.getMax_sal()>=job.getMin_sal())){
                    WHERE("max_salary <= #{max_sal}");
                }
            }else{
                SELECT("dummy");
                FROM("dual");
            }
            return SQL();
        }
        
        public String buildInsertOne(Job job){
            BEGIN();            
            if (job.isValidated()&&job.hasValidatedKey()){
                INSERT_INTO("jobs");
                
                VALUES("job_id", "#{id}");                
                
//                (title!=null&&!title.isEmpty())||
                if (job.getTitle()!=null&&!job.getTitle().isEmpty()){
                    VALUES("job_title", "#{title}");
                }
//                (min_sal!=null&&min_sal>=0&&(max_sal==null||max_sal>=min_sal))||
                if (job.getMin_sal()!=null&&job.getMin_sal()>=0&&(job.getMax_sal()==null||job.getMax_sal()>=job.getMin_sal())){
                    VALUES("min_salary", "#{min_sal}");
                }
//                (max_sal!=null&&max_sal>=0&&(min_sal==null||max_sal>=min_sal))   
                if (job.getMax_sal()!=null&&job.getMax_sal()>=0&&(job.getMin_sal()==null||job.getMax_sal()>=job.getMin_sal())){
                    VALUES("max_salary", "#{max_sal}");
                }
            }else{
                throw new InvalidBeanException("The Job bean to be inserted is invalid");
            }
            
            return SQL();
        }
        
        public String buildUpdateOne(Job job){
            BEGIN();
            if (job.hasValidatedKey()&&job.isValidated()){
                UPDATE("jobs");
                //                (title!=null&&!title.isEmpty())||
                if (job.getTitle()!=null&&!job.getTitle().isEmpty()){
                    SET("job_title = #{title}");
                }
//                (min_sal!=null&&min_sal>=0&&(max_sal==null||max_sal>=min_sal))||
                if (job.getMin_sal()!=null&&job.getMin_sal()>=0&&(job.getMax_sal()==null||job.getMax_sal()>=job.getMin_sal())){
                    SET("min_salary = #{min_sal}");
                }
//                (max_sal!=null&&max_sal>=0&&(min_sal==null||max_sal>=min_sal))   
                if (job.getMax_sal()!=null&&job.getMax_sal()>=0&&(job.getMin_sal()==null||job.getMax_sal()>=job.getMin_sal())){
                    SET("max_salary=#{max_sal}");
                }
                
                WHERE("job_id = #{id}");
            }else{
                throw new InvalidBeanException("The Job bean to be updated has to have a key");
            }
            
            return SQL();
        }
        
    }
    
    @Select("select * from jobs")
    @Results(value={
        @Result(property = "id",column = "job_id"),
        @Result(property = "title", column = "job_title"),
        @Result(property = "min_sal", column = "min_salary"),
        @Result(property = "max_sal", column = "max_salary")
    })
    @Override
    List<Job> findAll();
    
    @Select("select * from jobs where job_id = #{id}")
    @Results(value={
        @Result(property = "id",column = "job_id"),
        @Result(property = "title", column = "job_title"),
        @Result(property = "min_sal", column = "min_salary"),
        @Result(property = "max_sal", column = "max_salary")
    })
    @Override
    Job findById(String id);
    
    @SelectProvider(type = SqlBuilderHelper.class,method = "buildFindByRawProperties")
    @Results(value={
        @Result(property = "id",column = "job_id"),
        @Result(property = "title", column = "job_title"),
        @Result(property = "min_sal", column = "min_salary"),
        @Result(property = "max_sal", column = "max_salary")
    })    
    @Override
    List<Job> findByRawProperties(Job job);

    @SelectProvider(type = SqlBuilderHelper.class,method = "buildFindByRawType")
    @Results(value={
        @Result(property = "id",column = "job_id"),
        @Result(property = "title", column = "job_title"),
        @Result(property = "min_sal", column = "min_salary"),
        @Result(property = "max_sal", column = "max_salary")
    })  
    @Override
    public List<Job> findByRawType(Job bean);    
    
    @Select("select count(*) from jobs")
    @Override
    public Long count();

    /**
     * the bean to be inserted has to be both keyowing and valid
     * @param bean 
     */
    @InsertProvider(type = SqlBuilderHelper.class, method = "buildInsertOne")
    @Override
    public void insertOne(Job bean);
    
    @UpdateProvider(type = SqlBuilderHelper.class, method = "buildUpdateOne")
    @Override
    public void updateOne(Job bean);
    
    @Delete("delete from jobs where job_id = #{id}")
    @Override
    public void deleteById(@Param("id") String id);
}
