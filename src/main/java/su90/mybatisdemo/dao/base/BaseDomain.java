/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.base;

/**
 *
 * @author superman90
 */
public interface BaseDomain<K,W> {
    public Boolean hasValidatedKey();
    public void setKey(K key);
    public K getKey();
    public W getWebBean();
}
