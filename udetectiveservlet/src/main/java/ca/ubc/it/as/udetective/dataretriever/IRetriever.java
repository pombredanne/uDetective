package ca.ubc.it.as.udetective.dataretriever;

import java.io.IOException;

import ca.ubc.it.as.udetective.inputds.IDataSource;

/**
 * Public interface which all data retrievers must implement
 * 
 * @author Armenak Grigoryan
 */
public interface IRetriever {
    
    public Object retrieve(IDataSource dataSource, Object object) throws RetrieverException;
            
}
