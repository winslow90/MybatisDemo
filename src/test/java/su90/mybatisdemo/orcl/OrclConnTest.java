/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.orcl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import su90.mybatisdemo.utils.OrclUtils;

/**
 *
 * @author superman90
 */
public class OrclConnTest extends OrclUtils {
    
    Connection conn = null;
        
    
    public OrclConnTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        conn = getConnection();
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testinstance(){
        
    }
//    create or replace procedure queryempinfo(
//                                eid in NUMBER,
//                                pename out VARCHAR2,
//                                pemail out VARCHAR2,
//                                pphone out VARCHAR2,
//                                phdate out DATE,
//                                pjobid out NUMBER,
//                                psalary out NUMBER,
//                                pcomm out NUMBER,
//                                pmangid out NUMBER,
//                                pdeptid out NUMBER
//                                )
//    as
//    begin
//      select concat(FIRST_NAME||' ',LAST_NAME), EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, COMMISSION_PCT, MANAGER_ID, DEPARTMENT_ID 
//      into pename, pemail, pphone, phdate, pjobid, psalary, pcomm, pmangid, pdeptid
//      from EMPLOYEES where EMPLOYEE_ID=eid;
//    end;
    @Test
    public void teststoredprocedure(){
        String sql ="{call queryempinfo(?,?,?,?,?,?,?,?,?,?)}";
        
        CallableStatement call = null;
        
        try{
            
            Integer empid = 178;
            
            call = conn.prepareCall(sql);
            call.setInt(1, empid);
            
            call.registerOutParameter(2, OracleTypes.VARCHAR);
            call.registerOutParameter(3, OracleTypes.VARCHAR);
            call.registerOutParameter(4, OracleTypes.VARCHAR);
            call.registerOutParameter(5, OracleTypes.DATE);
            call.registerOutParameter(6, OracleTypes.VARCHAR);
            call.registerOutParameter(7, OracleTypes.NUMBER);
            call.registerOutParameter(8, OracleTypes.NUMBER);
            call.registerOutParameter(9, OracleTypes.NUMBER);
            call.registerOutParameter(10, OracleTypes.NUMBER);
            
            call.execute();
            
            StringBuilder sb = new StringBuilder();
            sb.append("seize data from emp id:");
            sb.append(empid);
            sb.append("{");
            
            sb.append("name:");
            String name = call.getString(2);
            sb.append(name);
            
            sb.append(",email:");
            String email = call.getString(3);
            sb.append(email);
            
            sb.append(",phone:");
            String phone = call.getString(4);
            sb.append(phone);
            
            sb.append(",hdate:");
            java.sql.Date hdate = call.getDate(5);
            sb.append(hdate);
            
            sb.append(",jobid:");
            String jobid = call.getString(6);
            sb.append(jobid);
            
            sb.append(",salary:");
            Long salary = call.getLong(7);
            sb.append(salary);
            
            sb.append(",comm:");
            Double comm = call.getDouble(8);
            sb.append(comm);
            
            sb.append(",mangid:");
            Long mangid = call.getLong(9);
            sb.append(mangid);
            
            sb.append(",deptid:");
            Long deptid = call.getLong(10);
            sb.append(deptid);
            
            sb.append("}");
            
            Logger.getLogger(OrclConnTest.class.getName()).log(Level.INFO, sb.toString());
            
//            System.out.println(sb.toString());
            
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            release(conn, call, null);
        }
    }
    
    
//    create or replace PACKAGE EMPPACKAGE AS 
//
//        type empcursor is ref cursor;
//        procedure queryEmpListviaDno(dno in number,empList out empcursor);
//
//      END EMPPACKAGE;
    
//    create or replace PACKAGE BODY EMPPACKAGE AS
//
//        procedure queryEmpListviaDno(dno in number,empList out empcursor) AS
//        BEGIN
//
//          open empList for select * from EMPLOYEES where DEPARTMENT_ID=dno;
//
//        END queryEmpListviaDno;
//
//      END EMPPACKAGE;
    
    @Test
    public void testCursor(){
        String sql = "{call EMPPACKAGE.queryEmpListviaDno(?,?)}";
        CallableStatement call = null;
        ResultSet rs=null;
        
        try{
            call=conn.prepareCall(sql);
            call.setInt(1, 100);
            call.registerOutParameter(2, OracleTypes.CURSOR);
            
            call.execute();
            
            rs = ((OracleCallableStatement)call).getCursor(2);
            
            while (rs.next()){
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                Logger.getLogger(OrclConnTest.class.getName()).log(Level.INFO, firstname+' '+lastname);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            release(conn, call, null);
        }
        
    }

    
}
