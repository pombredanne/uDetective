package as.it.ubc.ca.udetective.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author UBC IT
 */
public abstract class MMSService {
    
    private final Logger logger = LogManager.getLogger(MMSService.class);

    private static final org.apache.log4j.Logger LOG = LogManager.getLogger(MMSService.class);

    public DataSource getDataSource() throws MMSServiceException {

        DataSource ds = null;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource)envContext.lookup("jdbc/mms");
            
            logger.info("Data Source " + ds.toString() + " is successfully initialized.");
            return ds;
        } catch (NamingException ex) {
            LOG.error("Problem instantiating DataSource",ex);
            throw new MMSServiceException("Problem instantiating DataSource", ex);
        }      
    }    
}