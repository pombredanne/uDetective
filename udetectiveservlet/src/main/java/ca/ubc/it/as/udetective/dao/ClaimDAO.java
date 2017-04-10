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
     * Adds new claim.
     * 
     * Generates a unique UBC ID by calling ADMISS.AD_ADM_ID_SEQ_PROC, then
     * proceeds to insert relevant records in NARC_PERSONT, NARC_PROSPECT_IDT,
     * CBM_TXN_SRCE_TYPE and CBM_MERCHANT.  The next merchant ID is set on the
     * passed merchant object.
     * 
     * @param ticket The ticket to insert
     * @param date Extracted date
     * @param ipAddress extracted IP Address
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
