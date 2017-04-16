package ca.ubc.it.as.udetective.handler;

import ca.ubc.it.as.udetective.model.AbstractModel;

/**
 *
 * @author Armenak Grigoryan
 */
public class SISHandler extends AbstractHandler {
    public SISHandler(AbstractHandler handler){
	this.setHandler(handler);
    }
    
    @Override
    public void handleRequest(AbstractModel model) {
        System.out.println("A text parser is handling the file: "+model);
    }
}
