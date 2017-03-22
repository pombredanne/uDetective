package ca.ubc.it.as.udetective.utils;

/**
 * This class loads properties from property file
 * 
 * @author Armenak Grigoryan
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.apache.log4j.Logger.getLogger;
import org.apache.log4j.Logger;

/**
 *
 * @author Armenak Grigoryan
 */
public final class AppProperties {
    
    private static final Logger log = getLogger(AppProperties.class);
    private static Properties props = new Properties();
    
    public static String getProperty(final String key) {
        if (props.isEmpty()) {
            loadPropertiesFromClassPath();
        }
        
        return props.getProperty(key);
    }
    
    /**
     * Load property file
     * @param fileName
     * @return Properties
     */
    public static void loadPropertiesFromClassPath() {
 
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("udetective.properties");
        
        try {
            props.load(input);          
        } catch (IOException ioe) {
            log.error(ioe.toString());
        }        
    } 
}