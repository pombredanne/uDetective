package as.it.ubc.ca.udetective.dataretriever;

import as.it.ubc.ca.udetective.inputds.IInputDS;
import as.it.ubc.ca.udetective.model.ServiceNowTicket;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import static org.apache.log4j.Logger.getLogger;

/**
 * Retrieves data from ServiceNow
 * 
 * @author strider
 */
public class ServiceNowRetrieve implements IRetriever {
    
    private static final Logger log = getLogger(ServiceNowRetrieve.class);    
    
    private IInputDS inputDS = null;
    
    public void serviceNowRetrieve(IInputDS inputDS) {
        this.inputDS = inputDS;
    }
    
    @Override
    public void retrieve() throws IOException {
                
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream input = classLoader.getResourceAsStream("udetective.properties");
        Properties properties = new Properties();
        properties.load(input);        
                
        HttpClient client = new HttpClient();
        client.getParams().setAuthenticationPreemptive(false);
        Credentials creds = new UsernamePasswordCredentials(properties.getProperty("username"), properties.getProperty("password"));
        client.getState().setCredentials(AuthScope.ANY, creds);

        HttpMethod method = new GetMethod("https://ubcdev.service-now.com/api/now/table/incident?sysparm_query=active%3Dtrue%5Estate!%3D6%5Esys_created_onRELATIVEGE%40hour%40ago%4048%5Eassignment_group%3D81db147e2b5c79444dde23f119da153b&sysparm_display_value=true&sysparm_fields=sys_id%2Cnumber%2Cshort_description%2Cdescription&sysparm_limit=1");
        method.addRequestHeader("Accept", "application/json");
        int status = client.executeMethod(method);
        log.info("Status:" + status);
        BufferedReader rd = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));     
        String message = org.apache.commons.io.IOUtils.toString(rd);
        
        // remove leading and ending "{"
        // message = message.substring(1, message.length()-1);
        int index = message.indexOf("\"result\":");
        message = message.substring(index+9, message.length()-1);
        log.info(message);
        
	Gson gson = new Gson(); 
        ServiceNowTicket[] wrapper = null;
        try {
            wrapper = gson.fromJson(message, ServiceNowTicket[].class);   
        } catch (Exception e) {
            log.error(e.toString());
        }
        
        log.info("number of JSON objects=" + wrapper.length);
        for (int i=0; i<wrapper.length; i++) {
            log.info(wrapper[i].toString());
        }
    }
}
