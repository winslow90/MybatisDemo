/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import su90.mybatisdemo.dao.domain.Job;
import su90.mybatisdemo.dao.mapper.JobsMapper;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author superman90
 */
@SpringBootTest
public class JobsMapperTest extends AbstractTestNGSpringContextTests{
    
    @Autowired
    JobsMapper jobsMapper;

    public void setJobsMapper(JobsMapper jobsMapper) {
        this.jobsMapper = jobsMapper;
    }
    
    @Test(groups = {"find"})
    public void testFindAll(){
        List<Job> result = jobsMapper.findAll();
        assertNotNull(result);
    }
    
    @Test(groups = {"find"})
    public void testFindById(){
        Job result = jobsMapper.findById("SA_MAN");
        assertNotNull(result);        
        assertEquals(result.getTitle(), "Sales Manager");
        assertEquals(result.getMax_sal(), new Long(20080L));
        assertEquals(result.getMin_sal(), new Long(10000L));
    }
    
    @Test(groups = {"find"})
    public void testFindByRawProperties01(){
        Job sampleJob = new Job("Public Relations Representative", 4500L, 10500L);
        List<Job> result = jobsMapper.findByRawProperties(sampleJob);
        assertTrue(result.size()==1);
        assertEquals(result.get(0).getId(), "PR_REP");
    }
    
    @Test(groups = {"find"})
    public void testFindByRawProperties02(){
        Job sampleJob = new Job("Public Relations Representative", 2008L, 40000L);
        sampleJob.setTitle(null);
        
        List<Job> result = jobsMapper.findByRawProperties(sampleJob);
        assertTrue(result.size()==19);
    }
    
    @Test(groups = {"find"})
    public void testFindByRawProperties03(){
        Job sampleJob = new Job("Public Relations Representative", 40000L, 2008L);
        sampleJob.setTitle(null);
        
        List<Job> result = jobsMapper.findByRawProperties(sampleJob);
        assertEquals(result.size(), 1);
        assertEquals(result.get(0), null);
    }
    
    @Test(groups = {"find"})
    public void testFindByRawType(){
        Job sampleJob = new Job("Public Relations Representative", 4500L, 10500L);
        List<Job> result = jobsMapper.findByRawType(sampleJob);
        assertTrue(result.size()==1);
        assertEquals(result.get(0).getId(), "PR_REP");
    }
    
    @Test(groups = {"find"})
    public void testCount(){
        Long result = jobsMapper.count();
        assertTrue(result>0);
    }
    
    @Test(groups = {"insert"},enabled = false)
    public void testInsert(){
        Job tobeinserted = new Job("DUMMY", "Dummy Job Titile", 0L, 0L);
        jobsMapper.insertOne(tobeinserted);
        assertNotNull(jobsMapper.findById("DUMMY"));
    }
    
    @Test(groups = {"update"}, dependsOnGroups = {"insert"},enabled = false)
    public void testUpdate(){
        Job tobeupdated = jobsMapper.findById("DUMMY");
        tobeupdated.setTitle("Goddamn Dummy Job Titile");
        jobsMapper.updateOne(tobeupdated);
        assertEquals(jobsMapper.findById("DUMMY").getTitle(), "Goddamn Dummy Job Titile");
    }
    
    @Test(groups = {"delete"}, dependsOnGroups = {"update","insert"},enabled = false)
    public void testDelete(){
        jobsMapper.deleteById("DUMMY");
        assertNull(jobsMapper.findById("DUMMY"));
    }
}
