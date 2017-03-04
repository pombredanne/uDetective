package as.it.ubc.ca.udetective.service;

import as.it.ubc.ca.udetective.MMSException;
import as.it.ubc.ca.udetective.dao.AdminDAO;
import as.it.ubc.ca.udetective.model.AbstractModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AdminService extends MMSService {    
    
    private static final Logger log = LogManager.getLogger(AdminService.class);
    
    public boolean isAdmin(String cwlName) throws MMSException {
        log.info("Checking for admin name " + cwlName);
        AdminDAO adminDAO = new AdminDAO();
        adminDAO.setDataSource(getDataSource());
        AbstractModel admin = adminDAO.findByCwlLoginName(cwlName);
        if (admin!= null) {
            return true;
        }
        return false;
    }
}