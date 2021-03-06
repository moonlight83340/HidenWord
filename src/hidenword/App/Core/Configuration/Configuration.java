/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hidenword.App.Core.Configuration;

/**
 * Configuration class, use to get value from config file
 * @author Gaëtan Perrot, Barbaria
 */
final public class Configuration {
    final private Driver driver;
    
    /**
     * Constructor take the driver in parameter
     * @param driver
     */
    public Configuration(Driver driver){
        this.driver = driver;
    }
    
    /**
     * Check if the configuration has the key
     * @param key Configuration item to check
     * @return boolean
     */
    public boolean has(String key){
        return driver.has(key);
    }

    /**
     * Get the value of the item
     * @param key The config item key
     * @return The raw value
     */
    public String get(String key){
        return driver.get(key);
    }
}
