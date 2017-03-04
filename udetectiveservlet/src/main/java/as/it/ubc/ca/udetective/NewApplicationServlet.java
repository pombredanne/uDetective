package as.it.ubc.ca.udetective;

/**
 * @author UBC IT
 */

import as.it.ubc.ca.udetective.model.ServiceNowTicket;
//import as.it.ubc.ca.mms.model.ApplicationWindow;
import as.it.ubc.ca.udetective.service.MerchantService;
//import as.it.ubc.ca.mms.service.ApplicationWindowService;
//import as.it.ubc.ca.mms.service.SpecializationService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class NewApplicationServlet extends HttpServlet {

//    private static final long serialVersionUID = 1L;
//    
//    private static final String PARAM_CWL_LOGIN_NAME     = "cwlLoginName";
//    
//    private static final String PARAM_STUDENT_NUMBER     = "studentNumber";
//    private static final String PARAM_FIRST_CHOICE_LIST  = "firstChoiceList";
//    private static final String PARAM_SECOND_CHOICE_LIST = "secondChoiceList";
//    private static final String PARAM_THIRD_CHOICE_LIST  = "thirdChoiceList";
//    
//    private static final String PAGE_404                 = "/WEB-INF/view/404.jsp";    
//    
//    private static final String THIS_FIELD_IS_REQUIRED   = "This field is required";
//        
//    private static final Logger log = LogManager.getLogger(NewApplicationServlet.class);
//
//    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response) 
//    throws ServletException, IOException {
//        
//        Object cwlLoginName = request.getSession().getAttribute(PARAM_CWL_LOGIN_NAME);
//        log.debug("CWL login name is " + cwlLoginName);
//        
//        AdminHelper helper = new AdminHelper(request, response, getServletContext());
//        if (!helper.isAdmin(cwlLoginName.toString())) {
//            RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
//            rd.forward(request, response); 
//            return;
//        }
//        
//        log.debug("Collecting data and redirecting to application page");
//        Map choices = null; 
//        try {
//            choices = new SpecializationService().populateChoices();
//        } catch (ApplicationException ae) {
//            RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
//            rd.forward(request, response);     
//            return;
//        }                         
//        request.setAttribute(PARAM_FIRST_CHOICE_LIST, choices.get(PARAM_FIRST_CHOICE_LIST));
//        request.setAttribute(PARAM_SECOND_CHOICE_LIST, choices.get(PARAM_SECOND_CHOICE_LIST));
//        request.setAttribute(PARAM_THIRD_CHOICE_LIST, choices.get(PARAM_THIRD_CHOICE_LIST));        
//        
//        
//        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/view/OnBehalf.jsp");
//        rd.forward(request, response);  
//    }
//
//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) 
//    throws ServletException, IOException {
//        
//        Object cwlLoginName = request.getSession().getAttribute(PARAM_CWL_LOGIN_NAME);
//        log.debug("CWL login name is " + cwlLoginName);
//        
//        AdminHelper helper = new AdminHelper(request, response, getServletContext());
//        if (!helper.isAdmin(cwlLoginName.toString())) {
//            RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
//            rd.forward(request, response);              
//            return;
//        }        
//        
//        // Check if the application is open
//        ApplicationWindowService applicationWindowService = new ApplicationWindowService();
//        ApplicationWindow applicationWindow;
//        try {
//            applicationWindow = applicationWindowService.getApplicationStatus();
//        } catch (ApplicationException ae) {
//            log.error(ae.toString());
//            RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
//            rd.forward(request, response);     
//            return;
//        }
//        
//        // Set attribues for application start and end
//        String applicationYear = ApplicationWindowService.transformTimestampToString(
//                applicationWindow.getFrom(),
//                "yyyy");
//        
//        request.setAttribute("applicationYear", applicationYear);                
//        
//        Map<String, String> errors = new HashMap<>();
//        
//        String studentNumber = request.getParameter(PARAM_STUDENT_NUMBER);
//        
//        String studentName = request.getParameter("studentName");
//        String firstChoice = request.getParameter("firstChoice");
//        String secondChoice = request.getParameter("secondChoice");
//        String thirdChoice = request.getParameter("thirdChoice");
//        String coop = request.getParameter("coop");
//        String appType = request.getParameter("appType");
//        
//        if (appType == null || appType.equals("")) {
//            errors.put("appType", THIS_FIELD_IS_REQUIRED);
//        }                
//        
//        if (studentNumber == null || studentNumber.equals("")) {
//            errors.put("studentNumber", THIS_FIELD_IS_REQUIRED);
//        }        
//        
//        if (studentName == null || studentName.equals("")) {
//            errors.put("studentName", THIS_FIELD_IS_REQUIRED);
//        }                
//        
//        if (firstChoice == null || firstChoice.equals("")) {
//            errors.put("first_choice", THIS_FIELD_IS_REQUIRED);
//        }
//        
//        if (secondChoice != null && (secondChoice.equals(firstChoice) || secondChoice.equals(thirdChoice))) {
//            errors.put("second_choice", "Second choice must be unique");
//        }
//        
//        if (thirdChoice != null && (thirdChoice.equals(firstChoice) || thirdChoice.equals(secondChoice))) {
//            errors.put("third_choice", "Third choice must be unique");
//        }        
//
//        int interestedInCoop = 0;
//        if (coop != null) {
//            interestedInCoop = 1;
//        }
//        
//        log.debug("--- Data submitted by student");
//        log.debug("Student number    " + studentNumber);
//        log.debug("Student name      " + studentName);
//        log.debug("Inerested in coop " + coop);
//        log.debug("First choice      " + firstChoice);
//        log.debug("Second choice     " + secondChoice);
//        log.debug("Third choice      " + thirdChoice);
//        
//        request.setAttribute("studentNumber", studentNumber);                
//
//        if (errors.isEmpty()) {
//            MerchantService applicationService = new MerchantService();
//            
//            // if application has already been submitted
//            try {
//                ServiceNowTicket submittedApplication = applicationService.findByStudentNumber(Integer.parseInt(studentNumber));
//                
//                if (submittedApplication != null) {
//                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/view/OnBehalf_Already.jsp");
//                    rd.forward(request, response);     
//                    return;                                
//                }
//            } catch (ApplicationException ae) {
//                RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
//                rd.forward(request, response);     
//                return;                
//            }
//            
//            ServiceNowTicket application = new ServiceNowTicket(Integer.parseInt(studentNumber), studentName, firstChoice);
//            application.setFirstChoice(firstChoice);
//            application.setSecondChoice(secondChoice);
//            application.setThirdChoice(thirdChoice);
//            application.setStudentName(studentName);
//            application.setStudentNumber(Integer.parseInt(studentNumber));
//            application.setCoop(interestedInCoop);
//            application.setAppType(appType);            
//
//            try {
//                application = applicationService.addOnBehalfApplication(application);
//            } catch (ApplicationException ae) {
//                RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
//                rd.forward(request, response);     
//                return;
//            }
//
//            request.setAttribute("application", application);
//            log.debug("Application for student " + application.getStudentNumber() + " was successfully submitted");
//
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/view/OnBehalf_Result.jsp");
//            rd.forward(request, response);         
//            return;
//        } else {
//            Map choices = null;
//            try {
//                choices = new SpecializationService().populateChoices();
//            } catch (ApplicationException ae) {
//                RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
//                rd.forward(request, response);     
//                return;
//            }             
//            request.setAttribute(PARAM_FIRST_CHOICE_LIST, choices.get(PARAM_FIRST_CHOICE_LIST));
//            request.setAttribute(PARAM_SECOND_CHOICE_LIST, choices.get(PARAM_SECOND_CHOICE_LIST));
//            request.setAttribute(PARAM_THIRD_CHOICE_LIST, choices.get(PARAM_THIRD_CHOICE_LIST));
//            request.setAttribute(PARAM_STUDENT_NUMBER, studentNumber);          
//            request.setAttribute("studentName", studentName); 
//            request.setAttribute("errors", errors);
//            log.warn("There is/are error(s) in the application: " + errors.toString());
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/view/OnBehalf.jsp");
//            rd.forward(request, response);          
//            return;
//        }
//    }
}