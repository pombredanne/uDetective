package ca.ubc.it.as.udetective.handler;

import ca.ubc.it.as.udetective.model.AbstractModel;

/**
 * Abstract Handler for CoD design pattern
 * 
 * @author Armenak Grigoryan
 */
public abstract class AbstractHandler {
    
    private AbstractHandler handler;

    public AbstractHandler getHandler() {
        return handler;
    }
    
    public void setHandler(AbstractHandler handler) {
        this.handler = handler;
    }
    
    public void setNextHandler(AbstractHandler successor){;
        this.setHandler(successor);
    }
   
    public void handleRequest(AbstractModel model) {
        if(getHandler() != null) {
            getHandler().handleRequest(model);
        } else {
            System.out.println("Unable to find the correct parser for the file: " + getHandler());
        }
    }
}
