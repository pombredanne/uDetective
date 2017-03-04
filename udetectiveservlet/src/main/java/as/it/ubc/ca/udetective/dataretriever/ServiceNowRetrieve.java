package as.it.ubc.ca.udetective.dataretriever;

import as.it.ubc.ca.udetective.inputds.IInputDS;
import as.it.ubc.ca.udetective.model.ServiceNowTicket;
import com.google.gson.Gson;

/**
 * Retrieves data from ServiceNow
 * 
 * @author strider
 */
public class ServiceNowRetrieve implements IRetriever {
    
    private IInputDS inputDS = null;
    
    public void serviceNowRetrieve(IInputDS inputDS) {
        this.inputDS = inputDS;
    }
    
    @Override
    public void retrieve() {
        // Let's assume we receive data from ServicneNow
        
	  String jsonStr= "{  'id': 10, 'subject': 'Urject ticket', 'body': 'This is a body of Urgent Ticket','date': '2017-01-01' }";
          System.out.println(jsonStr);

	  Gson gson = new Gson();
	  ServiceNowTicket ticket = gson.fromJson(jsonStr, ServiceNowTicket.class);
	  System.out.println(ticket.getId());
	  System.out.println(ticket.getSubject());
	  System.out.println(ticket.getBody());        
	  System.out.println(ticket.getDate());                  
    }
}
