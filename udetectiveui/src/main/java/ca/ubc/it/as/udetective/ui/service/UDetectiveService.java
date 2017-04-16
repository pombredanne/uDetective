package ca.ubc.it.as.udetective.ui.service;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author UBC IT
 */
public abstract class UDetectiveService {
    
    private static final Logger LOG = LogManager.getLogger(UDetectiveService.class);

    public DataSource getDataSource() throws UDetectiveServiceException {

        DataSource ds = null;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource)envContext.lookup("jdbc/udetective");
            
            LOG.info("Data Source " + ds.toString() + " is successfully initialized.");
            return ds;
        } catch (NamingException ex) {
            LOG.error("Problem instantiating DataSource",ex);
            throw new UDetectiveServiceException("Problem instantiating DataSource", ex);
        }      
    }    
}