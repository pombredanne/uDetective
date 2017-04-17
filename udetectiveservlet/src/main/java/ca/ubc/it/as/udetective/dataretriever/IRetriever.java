package ca.ubc.it.as.udetective.dataretriever;

import ca.ubc.it.as.udetective.model.AbstractModel;
import java.io.IOException;

/**
 * Public interface which all data retrievers must implement
 * 
 * @author Armenak Grigoryan
 */
public interface IRetriever {
    
    public AbstractModel retrieve(AbstractModel model);
    public void retrieve() throws IOException;
    
            
}
