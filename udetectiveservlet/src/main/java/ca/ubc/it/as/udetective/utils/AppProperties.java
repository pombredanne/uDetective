package ca.ubc.it.as.udetective.utils;

/**
 * Load properties from property file
 * 
 * @author Armenak Grigoryan
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.apache.log4j.Logger.getLogger;
import org.apache.log4j.Logger;

/**
 * Load properties from property file
 * 
 * @author Armenak Grigoryan
 */
public final class AppProperties {
    
    private static final Logger     log              = getLogger(AppProperties.class);
    private static final Properties props            = new Properties();
    private static final String     propertyFileName = "udetective.properties";
    
    public static String getProperty(final String key) {
        if (props.isEmpty()) {
            loadPropertiesFromClassPath();
        }
        
        return props.getProperty(key);
    }
    
    /**
     * Load property file into a memory
     */
    public static void loadPropertiesFromClassPath() {
 
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream(propertyFileName);
        
        try {
            props.load(input);          
        } catch (IOException ioe) {
            log.error(ioe.toString());
        }        
    } 
}