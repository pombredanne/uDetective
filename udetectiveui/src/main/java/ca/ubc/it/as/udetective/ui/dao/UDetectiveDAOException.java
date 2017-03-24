/**
 * Application level exception
 * @author UBC
 */
package ca.ubc.it.as.udetective.ui.dao;

import ca.ubc.it.as.udetective.ui.UDetectiveException;

public class UDetectiveDAOException extends UDetectiveException
{
    private static final long serialVersionUID = 1L;
    
    public UDetectiveDAOException() {
    }
    
    public UDetectiveDAOException(String message) {
        super(message);
    }    
    
    public UDetectiveDAOException(Throwable cause) {
        super(cause);
    }    
    
    public UDetectiveDAOException(String message, Throwable cause) {
        super(message, cause);
    }    
}