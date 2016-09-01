/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import su90.mybatisdemo.bo.base.BaseServiceImpl;
import su90.mybatisdemo.dao.base.BaseMapper;
import su90.mybatisdemo.dao.domain.Job_History;
import su90.mybatisdemo.dao.mapper.Job_HistoryMapper;
import su90.mybatisdemo.web.beans.Job_HistoryBean;

/**
 *
 * @author superman90
 */
@Service
@Transactional
public class Job_HistoryService extends BaseServiceImpl<Job_History, Job_History.Key, Job_HistoryBean, Job_History>{

    @Autowired
    Job_HistoryMapper job_HistoryMapper;
    
    @Override
    public BaseMapper<Job_History, Job_History.Key, Job_HistoryBean, Job_History> getBaseMapper() {
        return job_HistoryMapper;
    }
    
}
