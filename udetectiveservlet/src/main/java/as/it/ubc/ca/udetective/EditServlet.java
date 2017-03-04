package as.it.ubc.ca.udetective;

import as.it.ubc.ca.udetective.model.ServiceNowTicket;
import as.it.ubc.ca.udetective.service.MerchantService;
import as.it.ubc.ca.udetective.utils.Utilities;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.sql.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class EditServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String PAGE_404 = "/WEB-INF/view/404.jsp";

    private static final String PARAM_CWL_LOGIN_NAME = "cwlLoginName";
    private static final String PARAM_MERCHANT_ID = "merchantId";
    private static final String PARAM_MERCHANT_NAME = "merchantName";

    private static final String INVALID_DATE = "Date must be in the format \"YYYY-MM-DD\"";

    private static final Logger log = LogManager.getLogger(EditServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        Object cwlLoginName = request.getSession().getAttribute(PARAM_CWL_LOGIN_NAME);
        log.debug("CWL login name is " + cwlLoginName);

        AdminHelper helper = new AdminHelper(request, response, getServletContext());
        if (!helper.isAdmin(cwlLoginName.toString())) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
            rd.forward(request, response);
            return;
        }

        String merchantId = request.getParameter(PARAM_MERCHANT_ID);
        log.debug("EditServlet.doGet merchantId: " + merchantId);

        MerchantService merchantService = new MerchantService();
//        try {
//            ServiceNowTicket merchant = merchantService.findMerchant(merchantId);
//            if (merchant != null) {
//                request.setAttribute("merchant", merchant);
//                RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/view/EditMerchant.jsp");
//                view.forward(request, response);
//            }
//        } catch (MMSException ae) {
//            log.error(ae.toString());
//            RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
//            rd.forward(request, response);
//        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        Object cwlLoginName = request.getSession().getAttribute(PARAM_CWL_LOGIN_NAME);
        log.debug("CWL login name is " + cwlLoginName);

        AdminHelper helper = new AdminHelper(request, response, getServletContext());
        if (!helper.isAdmin(cwlLoginName.toString())) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
            rd.forward(request, response);
            return;
        }

        Map<String, String> errors = new HashMap<>();
        int merchantId                  = Integer.valueOf(request.getParameter(PARAM_MERCHANT_ID));
        String merchantName             = request.getParameter(PARAM_MERCHANT_NAME);
        String merchantType             = request.getParameter("merchantType");
        String description              = request.getParameter("description");
        String homeUrl                  = request.getParameter("homeUrl");
        String contactInfo              = request.getParameter("contactInfo");
        String serviceProvided          = request.getParameter("serviceProvided");
        String realTimeProcessing       = request.getParameter("realTimeProcessing");
        String active                   = request.getParameter("active");
        String accountingActive         = request.getParameter("accountingActive");
        String srcTypeCd                = request.getParameter("srcTypeCd");
        String reportingCategory        = request.getParameter("reportingCategory");
        String authServerId             = request.getParameter("authServerId");
        String businessContactName      = request.getParameter("businessContactName");
        String businessContactEmail     = request.getParameter("businessContactEmail");
        String technicalContactName     = request.getParameter("technicalContactName");
        String technicalContactEmail    = request.getParameter("technicalContactEmail");
        String department               = request.getParameter("department");
        String goLiveDate               = request.getParameter("goLiveDate");
        String confirmation             = request.getParameter("confirmation");
        String operationalContactEmail  = request.getParameter("operationalContactEmail");
        String note                     = request.getParameter("note");
        String expectedPeriodOfActivity = request.getParameter("expectedPeriodOfActivity");
        String createdBy                = request.getParameter("createdBy");        
        String serviceProvider          = request.getParameter("serviceProvider");                

        // find it to set defaults (e.g. glCode) not updated from the form
        MerchantService merchantService = new MerchantService();
        ServiceNowTicket merchant = null;
//        try {
//            merchant = merchantService.findMerchant(Integer.toString(merchantId));
//        } catch (MMSException ae) {
//            log.error(ae.toString());
//        }
        if (merchant == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
            rd.forward(request, response);
            return;
        }
        request.setAttribute("merchant", merchant);

//        merchant.setMerchantName(merchantName);
//        merchant.setMerchantType(merchantType);
//        merchant.setDescription(description);
//        merchant.setHomeUrl(homeUrl);
//        merchant.setContactInfo(contactInfo);
//        merchant.setServiceProvided(serviceProvided);
//        merchant.setRealTimeProcessing(realTimeProcessing);
//        merchant.setActive(active);
//        merchant.setAccountingActive(accountingActive);
//        merchant.setSrcTypeCd(srcTypeCd);
//        merchant.setReportingCategory(reportingCategory);
//        merchant.setAuthServerId(authServerId);
//        merchant.setBusinessContactName(businessContactName);
//        merchant.setBusinessContactEmail(businessContactEmail);
//        merchant.setTechnicalContactName(technicalContactName);
//        merchant.setTechnicalContactEmail(technicalContactEmail);
//        merchant.setDepartment(department);
//        merchant.setConfirmation(confirmation);
//        merchant.setOperationalContactEmail(operationalContactEmail);
//        merchant.setNote(note);
//        merchant.setExpectedPeriodOfActivity(expectedPeriodOfActivity);
//        merchant.setCreatedBy(createdBy);
//        merchant.setServiceProvider(serviceProvider);

        if (Utilities.isEmptyString(merchantName)) {
            errors.put(PARAM_MERCHANT_NAME, "This field is required");
        }
        if (Utilities.isEmptyString(srcTypeCd)) {
            errors.put("srcTypeCd", "This field is required");
        }
        if (Utilities.isEmptyString(description)) {
            errors.put("description", "This field is required");
        }
        if (Utilities.isEmptyString(serviceProvided)) {
            errors.put("serviceProvided", "This field is required");
        }
        if (Utilities.isEmptyString(businessContactName)) {
            errors.put("businessContactName", "This field is required");
        }
        if (Utilities.isEmptyString(businessContactEmail)) {
            errors.put("businessContactEmail", "This field is required");
        }
        if (Utilities.isEmptyString(merchantType)) {
            errors.put("merchantType", "This field is required");
        }
        if (Utilities.isEmptyString(operationalContactEmail)) {
            errors.put("operationalContactEmail", "This field is required");
        }
        if (!Utilities.isEmptyString(note) && note.length() > 2000) {
            errors.put("note", "This text is too long. Only 2,000 charactrs are allowed");
        }        
        

//        if (!Utilities.isEmptyString(goLiveDate)) {
//            if (!Utilities.isValidFormat("yyyy-MM-dd", goLiveDate)) {
//                errors.put("goLiveDate", INVALID_DATE);
//            } else {
//                merchant.setGoLiveDate(Date.valueOf(goLiveDate));
//            }
//        }
//
//        if (errors.isEmpty()) {
//
//            String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
//            merchant.setUpdatedOn(Date.valueOf(date));
//            merchant.setUpdatedBy(cwlLoginName.toString());
//
//            try {
//                merchantService.updateMerchant(merchant);
//            } catch (MMSException ae) {
//                RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
//                rd.forward(request, response);
//                return;
//            }
//
//            log.debug("Changes for merchant " + merchant.getMerchantName() + " has been successfully submitted");
//            request.setAttribute("saved", "true");
//
//        } else {
//            request.setAttribute(PARAM_MERCHANT_ID, merchantId);
//            request.setAttribute(PARAM_MERCHANT_NAME, merchantName);
//            request.setAttribute("errors", errors);
//            log.warn("There is/are error(s) in the form: " + errors.toString());
//        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/view/EditMerchant.jsp");
        rd.forward(request, response);
    }
}