package as.it.ubc.ca.udetective.dao;

import as.it.ubc.ca.udetective.model.ServiceNowTicket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MerchantDAO extends MMSDAO {
    
    private final Logger logger = LogManager.getLogger(MerchantDAO.class);
    
    private static final String MERCHANT_SELECT_FROM = "SELECT M.MERCHANT_ID, M.MERCHANT_NAME, M.DESCRIPTION, M.CONTACT_INFO, M.SERVICES_PROVIDED, "
            + "M.HOME_URL, M.SERVICE_PROVIDER_ID, M.REAL_TIME_PROCESSING, CASE WHEN M.ACTIVE = 'Y' OR M.SETTLEMENT_ACTIVE = 'Y' THEN 'Y' ELSE 'N' END AS ACTIVE, "
            + "M.CONTACT_MESSAGE, M.SETTLEMENT_SERVICE_PROVIDER, M.ACCOUNTING_ACTIVE, M.SRC_TYPE_CD, M.REPORTING_CATEGORY, "
            + "M.AUTH_SERVER_ID, M.BUSINESS_CONTACT_NAME, M.BUSINESS_CONTACT_EMAIL, M.TECHNICAL_CONTACT_NAME, M.TECHNICAL_CONTACT_EMAIL, M.DEPARTMENT, "
            + "M.GO_LIVE_DATE, M.CONFIRMATION, M.PAYMENT_REASON, M.MERCHANT_TYPE, M.CREATED_ON, M.UPDATED_ON, M.UPDATED_BY, M.OPERATIONAL_CONTACT_EMAIL, M.NOTE, "
            + "M. EXPECTED_PERIOD_OF_ACTIVITY, T.GL_ACCOUNT_CD, M.CREATED_BY, M.SERVICE_PROVIDER FROM CBM.CBM_MERCHANT M, CBM.CBM_TXN_SRCE_TYPE T";
    private static final String FIND_ALL_MERCHANTS = MERCHANT_SELECT_FROM + " WHERE t.SRC_TYPE_CD = m.SRC_TYPE_CD "; 
    private static final String FIND_MERCHANTS_BY_MERCHANT_CODE = MERCHANT_SELECT_FROM + " WHERE t.SRC_TYPE_CD = m.SRC_TYPE_CD AND lower(M.SRC_TYPE_CD) LIKE lower(?)";
    private static final String FIND_MERCHANT_BY_MERCHANT_ID = MERCHANT_SELECT_FROM + " WHERE t.SRC_TYPE_CD = m.SRC_TYPE_CD AND MERCHANT_ID = ?";
    private static final String INSERT_NEW_UPPLICATION =  "INSERT INTO application (student_name, student_number, app_type, first_choice, coop) VALUES('', ?,?, '', 0)";
    private static final String RETRIEVE_PASSWORD_BY_AUTHSERVERID = "SELECT S.PASSWORD FROM CBM.CBM_MERCHANT M, SIS.NARC_PROSPECT_IDT S WHERE S.USERNAME = M.AUTH_SERVER_ID AND M.AUTH_SERVER_ID = ?";
    
//    public List<ServiceNowTicket> getAllMerchants() throws MMSDAOException {
//        List<ServiceNowTicket> merchantList = new ArrayList<>();
//        ServiceNowTicket merchant = null;
//
//        logger.info("Fetching all merchants");
//        
//        try (Connection conn = getDataSource().getConnection();
//            Statement stmt = conn.createStatement();) {          
//            
//            try (ResultSet rs = stmt.executeQuery(FIND_ALL_MERCHANTS);) {
//                while (rs.next()) {
//                    merchant = new ServiceNowTicket();
//                    merchant.setMerchantId(rs.getInt("MERCHANT_ID"));                    
//                    merchant.setMerchantName(rs.getString("MERCHANT_NAME"));
//                    merchant.setDescription(rs.getString("DESCRIPTION"));
//                    merchant.setContactInfo(rs.getString("CONTACT_INFO"));                    
//                    merchant.setServiceProvided(rs.getString("SERVICES_PROVIDED"));                                        
//                    merchant.setHomeUrl(rs.getString("HOME_URL"));                       
//                    merchant.setServiceProviderId(rs.getInt("SERVICE_PROVIDER_ID"));                
//                    merchant.setRealTimeProcessing(rs.getString("REAL_TIME_PROCESSING"));                     
//                    merchant.setActive(rs.getString("ACTIVE"));                     
//                    merchant.setSettlementServiceProvider(rs.getInt("SETTLEMENT_SERVICE_PROVIDER"));                                        
//                    merchant.setAccountingActive(rs.getString("ACCOUNTING_ACTIVE"));                     
//                    merchant.setSrcTypeCd(rs.getString("SRC_TYPE_CD"));                                         
//                    merchant.setReportingCategory(rs.getString("REPORTING_CATEGORY"));                                         
//                    merchant.setAuthServerId(rs.getString("AUTH_SERVER_ID"));                                         
//                    merchant.setBusinessContactName(rs.getString("BUSINESS_CONTACT_NAME"));                                         
//                    merchant.setBusinessContactEmail(rs.getString("BUSINESS_CONTACT_EMAIL"));                                         
//                    merchant.setTechnicalContactName(rs.getString("TECHNICAL_CONTACT_NAME"));                                         
//                    merchant.setTechnicalContactEmail(rs.getString("TECHNICAL_CONTACT_EMAIL"));                                                             
//                    merchant.setDepartment(rs.getString("DEPARTMENT"));
//                    merchant.setGoLiveDate(rs.getDate("GO_LIVE_DATE"));
//                    merchant.setConfirmation(rs.getString("CONFIRMATION"));                                                             
//                    merchant.setMerchantType(rs.getString("MERCHANT_TYPE"));
//                    merchant.setCreatedOn(rs.getDate("CREATED_ON"));
//                    merchant.setUpdatedOn(rs.getDate("UPDATED_ON"));
//                    merchant.setUpdatedBy(rs.getString("UPDATED_BY"));      
//                    merchant.setGlCode(rs.getString("GL_ACCOUNT_CD"));
//                    merchant.setOperationalContactEmail(rs.getString("OPERATIONAL_CONTACT_EMAIL"));
//                    merchant.setNote(rs.getString("NOTE"));
//                    merchant.setExpectedPeriodOfActivity(rs.getString("EXPECTED_PERIOD_OF_ACTIVITY"));
//                    merchant.setCreatedBy(rs.getString("CREATED_BY"));   
//                    merchant.setPassword(retrievePassword(rs.getString("AUTH_SERVER_ID")));
//                    merchant.setServiceProvider(rs.getString("SERVICE_PROVIDER"));
//                    merchantList.add(merchant);
//                }
//            }
//	} catch (SQLException sqle) {
//            logger.error(sqle.toString());
//            throw new MMSDAOException(sqle);            
//	}        
//        
//        logger.debug("Returning " + merchantList.size() + " applications");
//        return merchantList;
//    }
    
//    public List<ServiceNowTicket> getMerchantsByMerchantCode(String merchantCode) throws MMSDAOException {
//        List<ServiceNowTicket> merchantList = new ArrayList<>();
//        ServiceNowTicket merchant = null;
//
//        logger.info("Fetching merchants by merchant code");
//        logger.debug("Preparing and executing query =" + FIND_MERCHANTS_BY_MERCHANT_CODE + " with parameter " + merchantCode);
//        try (Connection conn = getDataSource().getConnection();
//            PreparedStatement stmt = conn.prepareStatement(FIND_MERCHANTS_BY_MERCHANT_CODE);) {          
//            stmt.setString(1, merchantCode);
//            try (ResultSet rs = stmt.executeQuery();) {
//                while (rs.next()) {
//                    merchant = new ServiceNowTicket();
//                    merchant.setMerchantId(rs.getInt("MERCHANT_ID"));                    
//                    merchant.setMerchantName(rs.getString("MERCHANT_NAME"));
//                    merchant.setDescription(rs.getString("DESCRIPTION"));
//                    merchant.setContactInfo(rs.getString("CONTACT_INFO"));                    
//                    merchant.setServiceProvided(rs.getString("SERVICES_PROVIDED"));                                        
//                    merchant.setHomeUrl(rs.getString("HOME_URL"));                       
//                    merchant.setServiceProviderId(rs.getInt("SERVICE_PROVIDER_ID"));                
//                    merchant.setRealTimeProcessing(rs.getString("REAL_TIME_PROCESSING"));                     
//                    merchant.setActive(rs.getString("ACTIVE"));                     
//                    merchant.setSettlementServiceProvider(rs.getInt("SETTLEMENT_SERVICE_PROVIDER"));                                        
//                    merchant.setAccountingActive(rs.getString("ACCOUNTING_ACTIVE"));                     
//                    merchant.setSrcTypeCd(rs.getString("SRC_TYPE_CD"));                                         
//                    merchant.setReportingCategory(rs.getString("REPORTING_CATEGORY"));                                         
//                    merchant.setAuthServerId(rs.getString("AUTH_SERVER_ID"));                                         
//                    merchant.setBusinessContactName(rs.getString("BUSINESS_CONTACT_NAME"));                                         
//                    merchant.setBusinessContactEmail(rs.getString("BUSINESS_CONTACT_EMAIL"));                                         
//                    merchant.setTechnicalContactName(rs.getString("TECHNICAL_CONTACT_NAME"));                                         
//                    merchant.setTechnicalContactEmail(rs.getString("TECHNICAL_CONTACT_EMAIL"));                                                             
//                    merchant.setDepartment(rs.getString("DEPARTMENT"));
//                    merchant.setGoLiveDate(rs.getDate("GO_LIVE_DATE"));
//                    merchant.setConfirmation(rs.getString("CONFIRMATION"));                                                             
//                    merchant.setMerchantType(rs.getString("MERCHANT_TYPE"));
//                    merchant.setCreatedOn(rs.getDate("CREATED_ON"));
//                    merchant.setUpdatedOn(rs.getDate("UPDATED_ON"));
//                    merchant.setUpdatedBy(rs.getString("UPDATED_BY"));                                        
//                    merchant.setGlCode(rs.getString("GL_ACCOUNT_CD"));        
//                    merchant.setOperationalContactEmail(rs.getString("OPERATIONAL_CONTACT_EMAIL"));
//                    merchant.setNote(rs.getString("NOTE"));
//                    merchant.setExpectedPeriodOfActivity(rs.getString("EXPECTED_PERIOD_OF_ACTIVITY"));
//                    merchant.setCreatedBy(rs.getString("CREATED_BY"));     
//                    merchant.setPassword(retrievePassword(rs.getString("AUTH_SERVER_ID")));   
//                    merchant.setServiceProvider(rs.getString("SERVICE_PROVIDER"));
//                    merchantList.add(merchant);
//                }
//            }
//	} catch (SQLException sqle) {
//            logger.error(sqle.toString());
//            throw new MMSDAOException(sqle);            
//	}        
//        
//        logger.debug("Returning " + merchantList.size() + " applications");
//        return merchantList;
//    }    
    
//    public ServiceNowTicket getMerchantByMerchantId(String merchantId) throws MMSDAOException {
//        ServiceNowTicket merchant = null;
//
//        logger.info("Fetching merchant by merchant code");
//        
//        logger.debug("Executing query =" + FIND_MERCHANT_BY_MERCHANT_ID + " with parameter " + merchantId);
//        try (Connection conn = getDataSource().getConnection();
//            PreparedStatement stmt = conn.prepareStatement(FIND_MERCHANT_BY_MERCHANT_ID);) {          
//            stmt.setString(1, merchantId);
//            try (ResultSet rs = stmt.executeQuery();) {
//                while (rs.next()) {
//                    merchant = new ServiceNowTicket();
//                    merchant.setMerchantId(rs.getInt("MERCHANT_ID"));                    
//                    merchant.setMerchantName(rs.getString("MERCHANT_NAME"));
//                    merchant.setDescription(rs.getString("DESCRIPTION"));
//                    merchant.setContactInfo(rs.getString("CONTACT_INFO"));                    
//                    merchant.setServiceProvided(rs.getString("SERVICES_PROVIDED"));                                        
//                    merchant.setHomeUrl(rs.getString("HOME_URL"));                       
//                    merchant.setServiceProviderId(rs.getInt("SERVICE_PROVIDER_ID"));                
//                    merchant.setRealTimeProcessing(rs.getString("REAL_TIME_PROCESSING"));                     
//                    merchant.setActive(rs.getString("ACTIVE"));                     
//                    merchant.setSettlementServiceProvider(rs.getInt("SETTLEMENT_SERVICE_PROVIDER"));                                        
//                    merchant.setAccountingActive(rs.getString("ACCOUNTING_ACTIVE"));                     
//                    merchant.setSrcTypeCd(rs.getString("SRC_TYPE_CD"));                                         
//                    merchant.setReportingCategory(rs.getString("REPORTING_CATEGORY"));                                         
//                    merchant.setAuthServerId(rs.getString("AUTH_SERVER_ID"));                                         
//                    merchant.setBusinessContactName(rs.getString("BUSINESS_CONTACT_NAME"));                                         
//                    merchant.setBusinessContactEmail(rs.getString("BUSINESS_CONTACT_EMAIL"));                                         
//                    merchant.setTechnicalContactName(rs.getString("TECHNICAL_CONTACT_NAME"));                                         
//                    merchant.setTechnicalContactEmail(rs.getString("TECHNICAL_CONTACT_EMAIL"));                                                             
//                    merchant.setDepartment(rs.getString("DEPARTMENT"));
//                    merchant.setGoLiveDate(rs.getDate("GO_LIVE_DATE"));
//                    merchant.setConfirmation(rs.getString("CONFIRMATION"));                                                             
//                    merchant.setMerchantType(rs.getString("MERCHANT_TYPE"));
//                    merchant.setCreatedOn(rs.getDate("CREATED_ON"));
//                    merchant.setUpdatedOn(rs.getDate("UPDATED_ON"));
//                    merchant.setUpdatedBy(rs.getString("UPDATED_BY"));  
//                    merchant.setGlCode(rs.getString("GL_ACCOUNT_CD"));    
//                    merchant.setOperationalContactEmail(rs.getString("OPERATIONAL_CONTACT_EMAIL"));
//                    merchant.setNote(rs.getString("NOTE"));
//                    merchant.setExpectedPeriodOfActivity(rs.getString("EXPECTED_PERIOD_OF_ACTIVITY"));
//                    merchant.setCreatedBy(rs.getString("CREATED_BY"));      
//                    merchant.setPassword(retrievePassword(rs.getString("AUTH_SERVER_ID")));       
//                    merchant.setServiceProvider(rs.getString("SERVICE_PROVIDER"));                    
//                    break;
//                }
//            }
//	} catch (SQLException sqle) {
//            logger.error(sqle.toString());
//            throw new MMSDAOException(sqle);            
//	}        
//        
//        return merchant;
//    }        
    
//    public ServiceNowTicket addMerchant(ServiceNowTicket merchant) throws MMSDAOException {
//                
//        try (Connection conn = getDataSource().getConnection();
//            PreparedStatement pstmt = conn.prepareStatement(UPDATE_APPLICATION_BY_STUDENT_NUMBER);) {        
//            
//            pstmt.setString(1, merchant.getDescription());
//
//            pstmt.executeUpdate();                   
//            pstmt.close();
//            
//            return merchant;
//	} catch (SQLException sqle) {
//            logger.error(sqle.toString());
//            throw new MMSDAOException(sqle);
//	} 
//    }
    
    /**
     * Update merchant
     * 
     * It would be better to use PreparedStatement, but the data quality is so poor that it is easier to construct Statement "manually"
     * @param merchant
     * @return
     * @throws MMSDAOException 
     */
//    public void updateMerchant(ServiceNowTicket merchant) throws MMSDAOException {
//                
//        try (Connection conn = getDataSource().getConnection();) {
//            
//            logger.debug("Updating the application for merchant " + merchant.getMerchantId());
//            
//            List<String> params = new ArrayList<>();
//            ServiceNowTicket cur = getMerchantByMerchantId(Integer.toString(merchant.getMerchantId()));
//            String query = "UPDATE CBM.CBM_MERCHANT SET MERCHANT_NAME = ?, DESCRIPTION = ?, CONTACT_INFO = ?, SERVICES_PROVIDED = ?, "
//                    + "HOME_URL = ?, SERVICE_PROVIDER_ID = ?, REAL_TIME_PROCESSING = ?, "
//                    + "SETTLEMENT_SERVICE_PROVIDER = ?, ACCOUNTING_ACTIVE = ?, SRC_TYPE_CD = ?, "
//                    + "REPORTING_CATEGORY = ?, AUTH_SERVER_ID = ?, BUSINESS_CONTACT_NAME = ?, BUSINESS_CONTACT_EMAIL = ?, "
//                    + "TECHNICAL_CONTACT_NAME = ?, TECHNICAL_CONTACT_EMAIL = ?, CONFIRMATION = ?, "
//                    + "MERCHANT_TYPE = ?, OPERATIONAL_CONTACT_EMAIL = ?, NOTE = ?, EXPECTED_PERIOD_OF_ACTIVITY = ?,"
//                    + "UPDATED_ON = ?, UPDATED_BY = ?, CREATED_BY = ?, SERVICE_PROVIDER = ?, DEPARTMENT = ?";
//            
//            boolean updateActive = !cur.getActive().equals(merchant.getActive());
//            boolean updateGoLiveDate = (merchant.getGoLiveDate() != null);
//            if (updateActive) {
//                query += ", ACTIVE = ?, SETTLEMENT_ACTIVE = ?";
//            }
//            if (updateGoLiveDate) {
//                query += ", GO_LIVE_DATE = ?";
//            }
//            query += " WHERE MERCHANT_ID = ?";
//            
//            logger.debug(query);
//            
//            try (PreparedStatement stmt = conn.prepareStatement(query);) {
//                
//                stmt.setString(1, merchant.getMerchantName());
//                stmt.setString(2, merchant.getDescription());
//                stmt.setString(3, merchant.getContactInfo());
//                stmt.setString(4, merchant.getServiceProvided());
//                stmt.setString(5, merchant.getHomeUrl());
//                stmt.setInt(6, merchant.getServiceProviderId());
//                stmt.setString(7, merchant.getRealTimeProcessing());
//                stmt.setInt(8, merchant.getSettlementServiceProvider());
//                stmt.setString(9, merchant.getAccountingActive());
//                stmt.setString(10, merchant.getSrcTypeCd());
//                stmt.setString(11, merchant.getReportingCategory());
//                stmt.setString(12, merchant.getAuthServerId());
//                stmt.setString(13, merchant.getBusinessContactName());
//                stmt.setString(14, merchant.getBusinessContactEmail());
//                stmt.setString(15, merchant.getTechnicalContactName());
//                stmt.setString(16, merchant.getTechnicalContactEmail());
//                stmt.setString(17, merchant.getConformation());
//                stmt.setString(18, merchant.getMerchantType());
//                stmt.setString(19, merchant.getOperationalContactEmail());
//                stmt.setString(20, merchant.getNote());
//                stmt.setString(21, merchant.getExpectedPeriodOfActivity());
//                stmt.setDate(22, merchant.getUpdatedOn());
//                stmt.setString(23, merchant.getUpdatedBy());
//                stmt.setString(24, merchant.getCreatedBy());
//                stmt.setString(25, merchant.getServiceProvider());
//                stmt.setString(26, merchant.getDepartment());
//                
//                int pos = 27;
//                if (updateActive) {
//                    String[] wsvtTypes = { "WS", "VT", "WSVT" };
//                    if (merchant.getActive().equals("N")) {
//                        stmt.setString(pos++, "N");
//                        stmt.setString(pos++, "N");
//                    } else if (ArrayUtils.contains(wsvtTypes, merchant.getMerchantType())) {
//                        stmt.setString(pos++, "N");
//                        stmt.setString(pos++, "Y");
//                    } else {
//                        stmt.setString(pos++, "Y");
//                        stmt.setString(pos++, "Y");
//                    }
//                }
//                if (updateGoLiveDate) {
//                    stmt.setDate(pos++, merchant.getGoLiveDate());
//                }
//                stmt.setInt(pos++, merchant.getMerchantId());
//                
//                stmt.executeQuery();
//                stmt.close();
//            }
//        } catch (SQLException sqle) {
//            logger.error(sqle.toString());
//            throw new MMSDAOException(sqle);
//        } 
//    }
    
    public ServiceNowTicket addNewMerchant(ServiceNowTicket merchant) throws MMSDAOException {
                
        try (Connection conn = getDataSource().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(INSERT_NEW_UPPLICATION);) {        
            
            pstmt.executeUpdate();                   
            pstmt.close();
            
            return merchant;
	} catch (SQLException sqle) {
            logger.error(sqle.toString());
            throw new MMSDAOException(sqle);
	} 
    }        
    
    public String retrievePassword(String authServerId) throws MMSDAOException {
        String password = "";
        
        logger.info("Fetching password by authServerID " + authServerId);

        try (Connection conn = getDataSource().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(RETRIEVE_PASSWORD_BY_AUTHSERVERID);) {          
            pstmt.setString(1, authServerId);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    password = rs.getString("PASSWORD");
                }
            }
	} catch (SQLException sqle) {
            logger.error(sqle.toString());
            throw new MMSDAOException(sqle);            
	}        
        
        return password;
    }
}
