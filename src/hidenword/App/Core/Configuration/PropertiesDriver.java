/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hidenword.App.Core.Configuration;

import hidenword.App.Paths;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Properties Driver, implementation of driver file for configuration using
 * @author Gaëtan
 */
public class PropertiesDriver implements Driver{
    final private Properties properties;
    
    /**
     * Create a new PropertiesDriver     
     * @param fileName
     */
    public PropertiesDriver(String fileName){
        this.properties = new Properties();
        FileInputStream file = null;
        try {
            file = new FileInputStream(fileName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            properties.load(file);
            file.close();
        }catch(IOException ex){
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);      
        }
    }
    
    @Override
    public boolean has(String key) {
        return this.properties.containsKey(key);
    }

    @Override
    public String get(String key) {
        String result = null;
        StringWriter writer = new StringWriter();
        this.properties.list(new PrintWriter(writer));
        System.out.println(writer.getBuffer().toString());
        if(key !=null && !key.trim().isEmpty()){
            result = this.properties.getProperty(key);
        }
        return result;
    } 
}