/**
 * Application level exception
 * @author Armenak Grigoryan
 */
package ca.ubc.it.as.udetective;


public class UDetectiveException extends Exception
{
    private static final long serialVersionUID = 1L;
    public UDetectiveException()
    {
    }
    
    public UDetectiveException(String message)
    {
        super(message);
    }    
    
    public UDetectiveException(Throwable cause)
    {
        super(cause);
    }    
    
    public UDetectiveException(String message, Throwable cause)
    {
        super(message, cause);
    }    
}