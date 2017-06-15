package ca.ubc.it.as.udetective.dataretriever;

import ca.ubc.it.as.udetective.UDetectiveException;

/**
 * Package level exception class
 * @author Armenak Grigoryan
 */
public class RetrieverException extends UDetectiveException {
    private static final long serialVersionUID = 1L;
    
    public RetrieverException() {
    }
    
    public RetrieverException(String message) {
        super(message);
    }    
    
    public RetrieverException(Throwable cause) {
        super(cause);
    }    
    
    public RetrieverException(String message, Throwable cause) {
        super(message, cause);
    }    
}

   