package as.it.ubc.ca.udetective.dataretriever;

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

import com.google.gson.Gson;

import as.it.ubc.ca.udetective.inputds.IInputDS;
import as.it.ubc.ca.udetective.model.ServiceNowTicket;
import as.it.ubc.ca.udetective.utils.AppProperties;

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
                                
        HttpClient client = new HttpClient();
        client.getParams().setAuthenticationPreemptive(false);
        Credentials creds = new UsernamePasswordCredentials(AppProperties.getProperty("username"), AppProperties.getProperty("password"));
        client.getState().setCredentials(AuthScope.ANY, creds);

        // We should use an encoded query in this case because URL parameters do not support the full semantics of a filter.        
        String serviceUrl     = AppProperties.getProperty("service_url");
        String restPath       = AppProperties.getProperty("rest_path");
        String fetchInterval  = AppProperties.getProperty("fetch_interval");   
        String resultLimit    = AppProperties.getProperty("result_limit");
        String hardCodedForTesting = "48";
        String url1           = "?sysparm_query=active%3Dtrue%5Estate!%3D6%5Esys_created_onRELATIVEGE%40hour%40ago%40" + hardCodedForTesting;
        String url2           = "%5Eassignment_group%3D81db147e2b5c79444dde23f119da153b&sysparm_display_value=true&sysparm_fields=sys_id%2Cnumber%2Cshort_description%2Cdescription&sysparm_limit=" + resultLimit;
        
        String finalUrl = serviceUrl + restPath + url1 + url2;
        
        // active=true^state!=6^sys_created_onRELATIVEGE@hour@ago@48^assignment_group=81db147e2b5c79444dde23f119da153b
        
        // HttpMethod method = new GetMethod("https://ubcdev.service-now.com/api/now/table/incident?sysparm_query=active%3Dtrue%5Estate!%3D6%5Esys_created_onRELATIVEGE%40hour%40ago%4048%5Eassignment_group%3D81db147e2b5c79444dde23f119da153b&sysparm_display_value=true&sysparm_fields=sys_id%2Cnumber%2Cshort_description%2Cdescription&sysparm_limit=1");
        HttpMethod method = new GetMethod(finalUrl);
        method.addRequestHeader("Accept", "application/json");
        int status = client.executeMethod(method);
        log.info("Status:" + status);
        BufferedReader rd = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));     
        String message = org.apache.commons.io.IOUtils.toString(rd);
        
        // remove leading and ending "{"
        // message = message.substring(1, message.length()-1);
        int index = message.indexOf("\"result\":");
        message = message.substring(index+9, message.length()-1);
        log.debug(message);
        
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
