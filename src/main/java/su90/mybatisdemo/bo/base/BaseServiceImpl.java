/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo.base;

import java.util.ArrayList;
import java.util.List;
import su90.mybatisdemo.dao.base.BaseMapper;

/**
 *
 * @author superman90
 */
public abstract class BaseServiceImpl<T, K, Q> implements BaseService<T, K, Q>{
    
    
    public abstract BaseMapper<T, K, Q> getBaseMapper();

    @Override
    public List<T> getEntries() {
        return getBaseMapper().findAll();
    }

    @Override
    public List<T> getEntriesByIds(K[] ids) {
        List<T> result = new ArrayList<>();
        for (K id : ids) {
            result.add(getBaseMapper().findById(id));
        }
        return result;
    }

    @Override
    public void saveEntry(T t) {
        getBaseMapper().insertOne(t);
    }

    @Override
    public void updateEntry(T t) {
        getBaseMapper().updateOne(t);
    }

    @Override
    public T getEntryById(K id) {
        return getBaseMapper().findById(id);
    }

    @Override
    public void deleteEntriesByIds(K[] ids) {
        for (K id : ids){
            deleteEntryById(id);
        }
    }

    @Override
    public void deleteEntryById(K id) {
        getBaseMapper().deleteById(id);
    }

    @Override
    public Long count() {
        return getBaseMapper().count();
    }
    
}
