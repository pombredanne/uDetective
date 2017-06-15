package ca.ubc.it.as.udetective.dataretriever;

import ca.ubc.it.as.udetective.inputds.ServiceNowDataSource;

/**
 * This class implements Pipeline idea.
 * 
 * @author Armenak Grigoryan
 */
public class Pipeline {

    public void execute() throws RetrieverException {
        
        // Fetch data from ServiceNow
        IRetriever snRetriever = new ServiceNowRetriever();
        snRetriever.retrieve(new ServiceNowDataSource(), null);
        
        // Fetch data from ElasticSearch
        
        // Fetch data from ELDAP
   } 
}
