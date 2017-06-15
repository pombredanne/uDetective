package ca.ubc.it.as.udetective.inputds;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * ServiceNow Input Data Source
 * 
 * @author Armenak Grigoryan
 */
public class LDAPDataSource implements IDataSource {
    
    private final Logger logger = LogManager.getLogger(LDAPDataSource.class);

    private Object client = null;
    
    @Override
    public Object connect() {
        return client;
    }
    
    public void close() {
        if (client != null) {
            //client.close();
        }
    }
}
