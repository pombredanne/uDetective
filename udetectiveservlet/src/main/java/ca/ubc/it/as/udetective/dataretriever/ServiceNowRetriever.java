package ca.ubc.it.as.udetective.dataretriever;

import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import org.apache.log4j.Logger;
import static org.apache.log4j.Logger.getLogger;

import com.google.gson.Gson;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;

import ca.ubc.it.as.udetective.model.ServiceNowTicket;
import ca.ubc.it.as.udetective.utils.AppProperties;
import ca.ubc.it.as.udetective.utils.Utilities;
import ca.ubc.it.as.udetective.inputds.ElasticSearchDataSource;
import ca.ubc.it.as.udetective.inputds.IDataSource;
import ca.ubc.it.as.udetective.service.ClaimService;

/**
 * Retrieves data from ServiceNow
 * 
 * @author Armenak Grigoryan
 */
public class ServiceNowRetriever implements IRetriever {
    
    private static final Logger log = getLogger(ServiceNowRetriever.class);    
    
    @Override
    public Object retrieve(IDataSource snDataSource, Object object) throws RetrieverException {
                                
        HttpClient client = (HttpClient)snDataSource.connect();
        
        // We should use an encoded query in this case because URL parameters do not support the full semantics of a filter.        
        String serviceUrl     = AppProperties.getProperty("service_url");
        String restPath       = AppProperties.getProperty("rest_path");
        String resultLimit    = AppProperties.getProperty("result_limit");
        String period_of_time = AppProperties.getProperty("period_of_time");
        String url1           = "?sysparm_query=active%3Dtrue%5Estate!%3D6%5Esys_created_onRELATIVEGE%40hour%40ago%40" + period_of_time;
        String url2           = "%5Eassignment_group%3D81db147e2b5c79444dde23f119da153b&sysparm_display_value=true&sysparm_fields=sys_id%2Cnumber%2Cshort_description%2Cdescription&sysparm_limit=" + resultLimit;
        
        String finalUrl = serviceUrl + restPath + url1 + url2;
        
        HttpMethod method = new GetMethod(finalUrl);
        method.addRequestHeader("Accept", "application/json");
        String message = "";
        try {
            int status = client.executeMethod(method);
            log.info("Status:" + status);
            BufferedReader rd = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream()));     
            message = org.apache.commons.io.IOUtils.toString(rd);
        } catch (IOException ioe) {
            throw new RetrieverException(ioe.toString());
        }
        
        // remove leading and ending "{"
        int index = message.indexOf("\"result\":");
        message = message.substring(index+9, message.length()-1);
        
	Gson gson = new Gson(); 
        ServiceNowTicket[] wrapper = null;
        try {
            wrapper = gson.fromJson(message, ServiceNowTicket[].class);   
        } catch (Exception e) {
            log.error(e.toString());
        }
        
        if (wrapper == null) {
            log.error("Problem retrieving information from ServiceNow");
            return null;
        }
        log.info("number of fetched tickets: " + wrapper.length);
        List<ServiceNowTicket> ticketList = Arrays.asList(wrapper);  
        
        for (Iterator<ServiceNowTicket> it = ticketList.iterator(); it.hasNext();) {
            ServiceNowTicket ticket = it.next();
            log.info("Fetching IP address and date");
            String source    = StringUtils.substringBetween(ticket.getDescription(), "<Source>", "</Source>");
            String timeStamp = StringUtils.substringBetween(source, "<TimeStamp>", "</TimeStamp>");
            String ipAddress = StringUtils.substringBetween(source, "<IP_Address>", "</IP_Address>");
            log.info("--------------------------");
            log.info("Sys_Id:    " + ticket.getSysId());
            log.info("Number:    " + ticket.getNumber());
            log.info("Timestamp: " + timeStamp);
            log.info("IPAddress: " + ipAddress);      
            log.info("--------------------------");
            log.info("");
            ClaimService service = new ClaimService();
            try {
                // fixing timestamp
                timeStamp = fixTimeStampFormat(timeStamp);
                
                // Sends data to ElasticSearch to find CWL
                IDataSource esDataSource = new ElasticSearchDataSource();
                esDataSource.connect();
                
                // Sends data to CWL database to find email address, first and last name
                
                
                
                
                // save data in the database
                service.addTicket(ticket.getNumber(), ticket.getDescription(), java.sql.Timestamp.valueOf(timeStamp), ipAddress);
            } catch (Exception de) {
                log.error(de.toString());
            }
        }
        return null;
    }
    private String fixTimeStampFormat(String timeStamp) {

        if (Utilities.isEmptyString(timeStamp)) {
            log.error("Timestamp is NULL");
            return null;
        }

        return timeStamp.replace("T", " ").replace("Z","");            
    }    
}