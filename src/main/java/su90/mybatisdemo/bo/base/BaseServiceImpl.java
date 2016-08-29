/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo.base;

import java.util.ArrayList;
import java.util.List;
import su90.mybatisdemo.dao.base.BaseDomain;
import su90.mybatisdemo.dao.base.BaseMapper;

/**
 *
 * @author superman90
 * @param <T>   type
 * @param <K>   key
 * @param <Q>   querybean
 */
public abstract class BaseServiceImpl<T extends BaseDomain<K>, K, Q> implements BaseService<T, K, Q>{
    
    
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
    public List<T> getEntriesByBean(Q bean){
        return getBaseMapper().findByRawProperties(bean);
    }

    @Override
    public void saveEntry(T t) {
        List<T> potentialExisted = getBaseMapper().findByRawType(t);
        
        if (potentialExisted.size()==1){
            if (potentialExisted.get(0)==null){
                getBaseMapper().insertOne(t);
            }else{
                t.setKey(potentialExisted.get(0).getKey());
                getBaseMapper().updateOne(t);
            }
        }else{
            throw new UnsupportedOperationException("//TODO unfinished, need to define custome expception");
        }
        
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
