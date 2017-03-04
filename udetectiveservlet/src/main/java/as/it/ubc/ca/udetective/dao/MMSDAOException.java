/**
 * Application level exception
 * @author UBC
 */
package as.it.ubc.ca.udetective.dao;

import as.it.ubc.ca.udetective.MMSException;

public class MMSDAOException extends MMSException
{
    private static final long serialVersionUID = 1L;
    
    public MMSDAOException() {
    }
    
    public MMSDAOException(String message) {
        super(message);
    }    
    
    public MMSDAOException(Throwable cause) {
        super(cause);
    }    
    
    public MMSDAOException(String message, Throwable cause) {
        super(message, cause);
    }    
}