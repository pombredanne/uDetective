package ca.ubc.it.as.udetective.service;

import java.sql.Timestamp;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ca.ubc.it.as.udetective.dao.ClaimDAO;
import ca.ubc.it.as.udetective.dao.DAOException;

/**
 * 
 * @author Armenak Grigoryan
 */
public class ClaimService extends AbstractService {
    private static final Logger log = LogManager.getLogger(ClaimService.class);
    
    public void addTicket(String number, String description, Timestamp date, String ipAddress) 
    throws ServiceException {
        ClaimDAO claimDao = new ClaimDAO();
        claimDao.setDataSource(getDataSource());
        
        try {
            claimDao.addTicket(number, description, date, ipAddress);
        } catch (DAOException de) {
            log.error(de.toString());
            throw new ServiceException(de.toString());
        }
    }    

}