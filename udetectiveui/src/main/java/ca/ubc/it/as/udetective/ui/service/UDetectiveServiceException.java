/**
 * Application level exception
 * @author UBC
 */
package ca.ubc.it.as.udetective.ui.service;

import ca.ubc.it.as.udetective.ui.UDetectiveException;

public class UDetectiveServiceException extends UDetectiveException
{
    private static final long serialVersionUID = 1L;
    
    public UDetectiveServiceException() {
    }
    
    public UDetectiveServiceException(String message) {
        super(message);
    }    
    
    public UDetectiveServiceException(Throwable cause) {
        super(cause);
    }    
    
    public UDetectiveServiceException(String message, Throwable cause) {
        super(message, cause);
    }    
}