/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import su90.mybatisdemo.dao.domain.Job;
import static org.apache.ibatis.jdbc.SqlBuilder.*;
import su90.mybatisdemo.dao.base.BaseMapper;

/**
 *
 * @author superman90
 */
@Mapper
public interface JobsMapper extends BaseMapper<Job, String, Job>{
    
    static public class SqlBuilderHelper{
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
    
}
