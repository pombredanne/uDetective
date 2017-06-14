package ca.ubc.it.as.udetective.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * DAO class implementing access to Claims table
 * 
 * @author Armenak Grigoryan
 */
public class ClaimDAO extends AbstractDAO {
    
    private final Logger logger = LogManager.getLogger(ClaimDAO.class);

    /**
     * Insert new claim into a database.
     * 
     * @param number Extracted ticket number
     * @param description Extracted description
     * @param date Extracted Date
     * @param ipAddress Extracted IP Address
     * @throws DAOException 
     */
    public void addTicket(String number, String description, Timestamp date, String ipAddress) throws DAOException {
        try (Connection conn = getDataSource().getConnection();) {

            logger.debug("Adding new ServiceNow ticket " + number);

            String query = "INSERT INTO CLAIMS (ticket_number, claim_date, claim_text, extracted_ip) VALUES (?,?,?,?)";
            logger.debug(query);

            try (PreparedStatement stmt = conn.prepareStatement(query);) {

                stmt.setString(1, number);
                stmt.setTimestamp(2, date);
                stmt.setString(3, description);
                stmt.setString(4, ipAddress);

                stmt.executeUpdate();
                stmt.close();
            }
            
        } catch (SQLException sqle) {
            logger.error(sqle.toString());
            throw new DAOException(sqle);
        }        
    }
}