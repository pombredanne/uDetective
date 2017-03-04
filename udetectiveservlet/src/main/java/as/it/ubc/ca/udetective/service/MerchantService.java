package as.it.ubc.ca.udetective.service;

import as.it.ubc.ca.udetective.MMSException;
import as.it.ubc.ca.udetective.dao.AdminDAO;
import as.it.ubc.ca.udetective.dao.MMSDAOException;
import as.it.ubc.ca.udetective.dao.MerchantDAO;
import as.it.ubc.ca.udetective.model.AbstractModel;
import as.it.ubc.ca.udetective.model.ServiceNowTicket;
import java.util.List;

/**
 * Service layer for interracting with CBM database
 * 
 * @author Armenak Grigoryan
 */
public class MerchantService extends MMSService {
    
    /**
     * Retrieve all merchants
     * @return List of merchants
     * @throws as.it.ubc.ca.udetective.service.MMSServiceException
     * @throws as.it.ubc.ca.udetective.dao.MMSDAOException 
     */
//    public List<ServiceNowTicket> getAllMerchants() throws MMSServiceException, MMSDAOException {
//        MerchantDAO applicationDAO = new MerchantDAO();
//        applicationDAO.setDataSource(getDataSource());
//        List<ServiceNowTicket> applicationList = applicationDAO.getAllMerchants();
//        
//        return applicationList;
//    }    
//    
//    public List<ServiceNowTicket> findMerchants(String merchantCode) throws MMSServiceException, MMSDAOException {
//        MerchantDAO merchantDAO = new MerchantDAO();
//        merchantDAO.setDataSource(getDataSource());
//        List<ServiceNowTicket> applicationList = merchantDAO.getMerchantsByMerchantCode(merchantCode);
//        
//        return applicationList;
//    }        
//    
//    public ServiceNowTicket findMerchant(String merchantId) throws MMSServiceException, MMSDAOException {
//        MerchantDAO merchantDAO = new MerchantDAO();
//        merchantDAO.setDataSource(getDataSource());
//        ServiceNowTicket merchant = merchantDAO.getMerchantByMerchantId(merchantId);
//        
//        return merchant;
//    }            
//    
////    public ServiceNowTicket addMerchant(ServiceNowTicket merchant) throws MMSException {
////        MerchantDAO merchantDAO = new MerchantDAO();
////        merchantDAO.setDataSource(getDataSource());
////        merchantDAO.addMerchant(merchant);     
////        return merchant;
////    }
//    
//    /**
//     * Update merchant data in CBM_MERCHANT table
//     * @param merchant ServiceNowTicket
//     * @return int Number of updated rows
//     * @throws MMSException 
//     */
//    public void updateMerchant(ServiceNowTicket merchant) throws MMSException {
//        MerchantDAO applicationDAO = new MerchantDAO();
//        applicationDAO.setDataSource(getDataSource());
//        applicationDAO.updateMerchant(merchant);
//    }    
    
    /**
     * Finds admin by CWL name
     * @param cwlLoginName String
     * @return AbstractModel 
     * @throws MMSException 
     */
    public AbstractModel findAdminByCwlName(String cwlLoginName) throws MMSException {
        AdminDAO adminDAO = new AdminDAO();
        adminDAO.setDataSource(getDataSource());
        AbstractModel admin = adminDAO.findByCwlLoginName(cwlLoginName);           
        
        return admin;
    }    

    /**
     * Returns current time stamp
     * @return java.sql.Timestamp
     */
    public static java.sql.Timestamp getCurrentTimeStamp() {
	return new java.sql.Timestamp(new java.util.Date().getTime());
    }    
}