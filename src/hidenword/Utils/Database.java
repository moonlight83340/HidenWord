/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hidenword.Utils;

import static hidenword.Constants.Database.CONNECT_URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gaëtan
 */
public class Database {
    private static Database instance = null;
    private static Connection connection = null;

    private Database() {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(CONNECT_URL, "jdbc", "");
            conn.setAutoCommit(false);
        }catch(SQLException ex){
            ex.printStackTrace();
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            System.exit(0);
        }
        connection = conn;
    }
        
    /** Creates the instance is synchronized to avoid multithreads problems */
    private synchronized static void createInstance () {
        if (instance == null) { 
            instance = new Database ();
        }
    }
    
    /** Get the properties instance. Uses singleton pattern */
    public static Database getInstance(){
        // Uses singleton pattern to guarantee the creation of only one instance
        if(instance == null) {
            createInstance();
        }
        return instance;
    }

    /** Override the clone method to ensure the "unique instance" requeriment of this class
     * @throws java.lang.CloneNotSupportedException */
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
    
    public Connection getConnection() {
        return connection;
    }
}