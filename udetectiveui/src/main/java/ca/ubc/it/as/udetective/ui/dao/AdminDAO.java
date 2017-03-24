package ca.ubc.it.as.udetective.ui.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ca.ubc.it.as.udetective.ui.model.Admin;

public class AdminDAO extends UDetectiveDAO {
    
    private final Logger logger = LogManager.getLogger(AdminDAO.class);
    
    private static final String FIND_BY_CWL_NAME = "SELECT id, cwl_name, first_name, last_name, middle_name, email FROM ADMIN WHERE cwl_name=?";
        
    public Admin findByCwlLoginName(String cwlLoginName) throws UDetectiveDAOException {
        
        logger.info("Searching by CWL login name " + cwlLoginName);
        logger.debug(FIND_BY_CWL_NAME);

        Admin admin = null;
        
        try (Connection conn = getDataSource().getConnection();
            PreparedStatement ps = conn.prepareStatement(FIND_BY_CWL_NAME);) {
            
            ps.setString(1, cwlLoginName);
            try (ResultSet rs = ps.executeQuery();) {
                if (rs.next()) {
                    admin = new Admin(rs.getInt("id"), 
                                      rs.getString("cwl_name"),
                                      rs.getString("first_name"),
                                      rs.getString("last_name"),
                                      rs.getString("middle_name"),
                                      rs.getString("email"));
                }
            }
            return admin;
	} catch (SQLException sqle) {
            logger.error(sqle.toString());
            throw new UDetectiveDAOException(sqle);             
	}
    }
}
