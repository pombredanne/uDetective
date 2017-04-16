package ca.ubc.it.as.udetective.inputds;

import ca.ubc.it.as.udetective.utils.AppProperties;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;

/**
 * ServiceNow Input Data Source
 * 
 * @author Armenak Grigoryan
 */
public class ServiceNowDataSource implements IDataSource {
    @Override
    public Object connect() {
        HttpClient client = new HttpClient();
        client.getParams().setAuthenticationPreemptive(false);
        Credentials creds = new UsernamePasswordCredentials(AppProperties.getProperty("username"), AppProperties.getProperty("password"));
        client.getState().setCredentials(AuthScope.ANY, creds);
        
        return client;
    }
}
