package ca.ubc.it.as.udetective.dao;

import ca.ubc.it.as.udetective.UDetectiveException;

/**
 * Package level exception class
 * @author Armenak Grigoryan
 */
public class DAOException extends UDetectiveException {
    private static final long serialVersionUID = 1L;
    
    public DAOException() {
    }
    
    public DAOException(String message) {
        super(message);
    }    
    
    public DAOException(Throwable cause) {
        super(cause);
    }    
    
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }    
}

   