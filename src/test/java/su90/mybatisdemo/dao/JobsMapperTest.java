/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import su90.mybatisdemo.dao.domain.Job;
import su90.mybatisdemo.dao.mapper.JobsMapper;
import static org.junit.Assert.*;

/**
 *
 * @author superman90
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JobsMapperTest {
    
    @Autowired
    JobsMapper jobsMapper;

    public void setJobsMapper(JobsMapper jobsMapper) {
        this.jobsMapper = jobsMapper;
    }
    
    @Test
    public void testFindAll(){
        List<Job> result = jobsMapper.findAll();
        assertNotNull(result);
    }
    
    @Test
    public void testFindById(){
        Job result = jobsMapper.findById("SA_MAN");
        assertNotNull(result);        
        assertEquals(result.getTitle(), "Sales Manager");
        assertEquals(result.getMax_sal(), new Long(20080L));
        assertEquals(result.getMin_sal(), new Long(10000L));
    }
    
    @Test
    public void testFindByRawProperties01(){
        Job sampleJob = new Job("Public Relations Representative", 4500L, 10500L);
        List<Job> result = jobsMapper.findByRawProperties(sampleJob);
        assertTrue(result.size()==1);
        assertEquals(result.get(0).getId(), "PR_REP");
    }
    
    @Test
    public void testFindByRawProperties02(){
        Job sampleJob = new Job("Public Relations Representative", 2008L, 40000L);
        sampleJob.setTitle(null);
        
        List<Job> result = jobsMapper.findByRawProperties(sampleJob);
        assertTrue(result.size()==19);
    }
    
    @Test
    public void testFindByRawProperties03(){
        Job sampleJob = new Job("Public Relations Representative", 40000L, 2008L);
        sampleJob.setTitle(null);
        
        List<Job> result = jobsMapper.findByRawProperties(sampleJob);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0), null);
    }
}
