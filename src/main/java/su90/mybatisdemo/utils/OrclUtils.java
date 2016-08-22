/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author superman90
 */
public class OrclUtils {
    private static final String driver = "oracle.jdbc.OracleDriver";
    private static final String url = "jdbc:oracle:thin:@192.168.1.101:1521:db01";
    private static final String user = "mybatisdemo";
    private static final String password = "mybatisdemopw";
    
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrclUtils.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Connection getConnection(){
        
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(OrclUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void release(Connection conn, Statement st, ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrclUtils.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                rs=null;
            }
        }
        if (st != null){
            try {
                st.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrclUtils.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                st=null;
            }
        }     
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(OrclUtils.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                conn=null;
            }
        }     
    }
    
    
    
}
