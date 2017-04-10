package ca.ubc.it.as.udetective.service;

import ca.ubc.it.as.udetective.UDetectiveException;

/**
 *
 * @author Armenak Grigoryan
 */
public class ServiceException extends UDetectiveException {
    private static final long serialVersionUID = 1L;
    
    public ServiceException() {
    }
    
    public ServiceException(String message) {
        super(message);
    }    
    
    public ServiceException(Throwable cause) {
        super(cause);
    }    
    
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }    
}

   