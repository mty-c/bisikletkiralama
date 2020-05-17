
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;




public class DBConnection {
    
    public Connection connect() 
    {
        Connection c=null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            c=DriverManager.getConnection("jdbc:mysql://localhost:3306/bisiklet","root","1234");
            
            System.out.println("Basarılı");
            
        }catch(SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
            
        }
        return c;
    }  
    
}
