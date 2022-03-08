/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author PC
 */
public class DbUtil {
    private static Connection conection = null;
    private static final String USER = "root";
    private static final String PASSWORD = "nokia308";

    public static Connection getConection() {
        if(conection != null) return conection;
        else {
         try{   
             //Properties properties = new Properties();
             //Class.forName("com.mysql.jdbc.Driver");
             conection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud",USER, PASSWORD);
             return conection;
         }catch(Exception e){
             System.out.println(e.getMessage());
             return null;
         }
        }
    }
    
    
}
