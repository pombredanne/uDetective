/**
 * Application level exception
 * @author Armenak Grigoryan
 */
package as.it.ubc.ca.udetective;


public class MMSException extends Exception
{
    private static final long serialVersionUID = 1L;
    public MMSException()
    {
    }
    
    public MMSException(String message)
    {
        super(message);
    }    
    
    public MMSException(Throwable cause)
    {
        super(cause);
    }    
    
    public MMSException(String message, Throwable cause)
    {
        super(message, cause);
    }    
}