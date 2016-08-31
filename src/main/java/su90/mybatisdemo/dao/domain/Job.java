/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import su90.mybatisdemo.dao.base.BaseDomain;

/**
 *
 * @author superman90
 */
public class Job implements BaseDomain<String>, Serializable{
        String id;
        String title;
        Long min_sal;
        Long max_sal;

    public Job() {
    }

    public Job(String title, Long min_sal, Long max_sal) {
        this.title = title;
        this.min_sal = min_sal;
        this.max_sal = max_sal;
    }

    public Job(String id, String title, Long min_sal, Long max_sal) {
        this.id = id;
        this.title = title;
        this.min_sal = min_sal;
        this.max_sal = max_sal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getMin_sal() {
        return min_sal;
    }

    public void setMin_sal(Long min_sal) {
        this.min_sal = min_sal;
    }

    public Long getMax_sal() {
        return max_sal;
    }

    public void setMax_sal(Long max_sal) {
        this.max_sal = max_sal;
    }

    @Override
    public String toString() {
        return "Job{" + "id=" + id + ", title=" + title + ", min_sal=" + min_sal + ", max_sal=" + max_sal + '}';
    }        
        
    @JsonIgnore
    public Boolean isValidated(){
        return 
                (title!=null&&!title.isEmpty())||
                (min_sal!=null&&min_sal>=0&&(max_sal==null||max_sal>=min_sal))||
                (max_sal!=null&&max_sal>=0&&(min_sal==null||max_sal>=min_sal))
        ;
    }

    @Override
    @JsonIgnore
    public String getKey() {
        return this.id;
    }

    @Override
    public Boolean hasValidatedKey() {
        return this.id!=null;
    }

    @Override
    @JsonIgnore
    public void setKey(String key) {
        this.id = key;
    }
}
