package as.it.ubc.ca.udetective.model;

import java.io.Serializable;

public abstract class AbstractModel implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    static final String TO_STRING_DELIMITER = ":";
	        
    // Empty constructor
    public AbstractModel() {
    }
}