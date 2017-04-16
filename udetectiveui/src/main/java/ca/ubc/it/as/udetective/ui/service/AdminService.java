package ca.ubc.it.as.udetective.ui.service;

import ca.ubc.it.as.udetective.ui.UDetectiveException;
import ca.ubc.it.as.udetective.ui.dao.AdminDAO;
import ca.ubc.it.as.udetective.ui.model.Admin;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AdminService extends UDetectiveService {    
    
    private static final Logger log = LogManager.getLogger(AdminService.class);
    
    public boolean isAdmin(String cwlName) throws UDetectiveException {
        log.info("Checking for admin name " + cwlName);
        AdminDAO adminDAO = new AdminDAO();
        adminDAO.setDataSource(getDataSource());
        Admin admin = adminDAO.findByCwlLoginName(cwlName);
        return admin != null;
    }
}