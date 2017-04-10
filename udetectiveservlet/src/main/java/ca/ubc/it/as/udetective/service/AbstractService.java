package ca.ubc.it.as.udetective.service;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 * @author Armenak Grigoryan
 */
public abstract class AbstractService {
    
    private final Logger logger = LogManager.getLogger(AbstractService.class);

    public DataSource getDataSource() throws ServiceException {

        InitialContext cxt;
        DataSource ds = null;
        try {
            cxt = new InitialContext();
            ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/udetective" ); 
            logger.debug("Data Source " + ds.toString() + " is successfully initialized.");
            return ds;
        } catch (NamingException ex) {
            logger.error("Problem instantiating DataSource",ex);
            throw new ServiceException("Problem instantiating DataSource", ex);
        }             
        
    }    
}