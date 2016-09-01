/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.base;

import java.util.List;

/**
 *
 * @author superman90
 * @param <T>   General Type
 * @param <K>   Key for the Type
 * @param <Q>   Query Bean for the Type which might be the same as Type
 */
public interface BaseMapper<T extends BaseDomain<K,W>,K,W,Q> {
    public List<T> findAll();
    public T findById(K id);
    public List<T> findByRawType(T bean);
    public List<T> findByRawProperties(Q bean);
    public void insertOne(T bean);
    public void updateOne(T bean);
    public void deleteById(K id);
    public Long count();
}
