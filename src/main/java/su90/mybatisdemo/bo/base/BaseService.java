/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.bo.base;

import java.util.List;
import su90.mybatisdemo.dao.base.BaseDomain;

/**
 *
 * @author superman90
 */
public interface BaseService<T extends BaseDomain<K>,K,Q> {
    public List<T> getEntries();
    public List<T> getEntriesByIds(K[] ids);
    public List<T> getEntriesByBean(Q bean);
    public void saveEntry(T t);
    public void updateEntry(T t);
    public T getEntryById(K id);
    public void deleteEntriesByIds(K[] ids);
    public void deleteEntryById(K id);
    public Long count();
    
}
