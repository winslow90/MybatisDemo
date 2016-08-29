/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.dao.ex;

/**
 *
 * @author superman90
 */
public class KeyAbsentException extends RuntimeException{

    public KeyAbsentException(String string) {
        super(string);
    }
    
}
