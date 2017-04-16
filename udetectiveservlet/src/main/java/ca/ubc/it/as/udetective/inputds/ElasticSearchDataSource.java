package ca.ubc.it.as.udetective.inputds;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;


/**
 * ServiceNow Input Data Source
 * 
 * @author Armenak Grigoryan
 */
public class ElasticSearchDataSource implements IDataSource {
    
    private final Logger logger = LogManager.getLogger(ElasticSearchDataSource.class);

    private TransportClient client = null;
    
    @Override
    public TransportClient connect() {
        Settings settings = Settings.builder()
            .put("cluster.name", "elasticsearch")
            .put("client.transport.sniff", true).build();       
        
        try {
            client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("event-apptst01.as.it.ubc.ca"), 9300))
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("event-apptst02.as.it.ubc.ca"), 9300));        
        } catch (UnknownHostException uhe) {
            logger.error(uhe.toString());
        }
        
        return client;
    }
    
    public void close() {
        if (client != null) {
            client.close();
        }
    }
}
