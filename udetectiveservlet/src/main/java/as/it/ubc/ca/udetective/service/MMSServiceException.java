/**
 * Application level exception
 * @author UBC
 */
package as.it.ubc.ca.udetective.service;

import as.it.ubc.ca.udetective.MMSException;

public class MMSServiceException extends MMSException
{
    private static final long serialVersionUID = 1L;
    
    public MMSServiceException() {
    }
    
    public MMSServiceException(String message) {
        super(message);
    }    
    
    public MMSServiceException(Throwable cause) {
        super(cause);
    }    
    
    public MMSServiceException(String message, Throwable cause) {
        super(message, cause);
    }    
}