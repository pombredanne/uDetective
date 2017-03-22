package ca.ubc.it.as.udetective.dataretriever;

import java.io.IOException;

/**
 * Public interface which all data retrievers must implement
 * 
 * @author Armenak Grigoryan
 */
public interface IRetriever {
    
    public void retrieve() throws IOException ;
            
}
