package as.it.ubc.ca.udetective;

import as.it.ubc.ca.udetective.model.ServiceNowTicket;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String PAGE_404                 = "/WEB-INF/view/404.jsp";
    private static final String PARAM_CWL_LOGIN_NAME     = "cwlLoginName";

    private static final Logger log = LogManager.getLogger(ExportServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
	doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        log.info(request.getSession().getAttribute("merchantList"));

        Object cwlLoginName = request.getSession().getAttribute(PARAM_CWL_LOGIN_NAME);
        log.debug("CWL login name is " + cwlLoginName);

        AdminHelper helper = new AdminHelper(request, response, getServletContext());
        if (!helper.isAdmin(cwlLoginName.toString())) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(PAGE_404);
            rd.forward(request, response);
            return;
        }

        log.info("Generating report");

        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();

        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("Merchant ID");
        row.createCell(1).setCellValue("Merchant Name");
        row.createCell(2).setCellValue("Description");
        row.createCell(3).setCellValue("Contact Info");
        row.createCell(4).setCellValue("Services Provided");
        row.createCell(5).setCellValue("Service Provider ID");
        row.createCell(6).setCellValue("Merchant Web Site");
        row.createCell(7).setCellValue("Active");
        row.createCell(8).setCellValue("Settlement Service Provider");
        row.createCell(9).setCellValue("Accounting Active");
        row.createCell(10).setCellValue("Merchant Code");
        row.createCell(11).setCellValue("Reporting Category");
        row.createCell(12).setCellValue("Auth Server ID");
        row.createCell(13).setCellValue("Business Contact Name");
        row.createCell(14).setCellValue("Business Contact Email");
        row.createCell(15).setCellValue("Technical Contact Name");
        row.createCell(16).setCellValue("Technical Contact Email");
        row.createCell(17).setCellValue("Department");
        row.createCell(18).setCellValue("Go Live Date");
        row.createCell(19).setCellValue("Confirmation");
        row.createCell(20).setCellValue("Merchant Type");
        row.createCell(21).setCellValue("Created On");
        row.createCell(22).setCellValue("Created By");        
        row.createCell(23).setCellValue("Updated On");
        row.createCell(24).setCellValue("Updated By");        

//        List<ServiceNowTicket> merchantList = (ArrayList<ServiceNowTicket>)request.getSession().getAttribute("merchantList");
//        int i=1;
//        for (ServiceNowTicket merchant: merchantList) {
//            HSSFRow row1 = sheet.createRow(i);
//            row1.createCell(0).setCellValue(merchant.getMerchantId());
//            row1.createCell(1).setCellValue(merchant.getMerchantName());
//            row1.createCell(2).setCellValue(merchant.getDescription());
//            row1.createCell(3).setCellValue(merchant.getContactInfo());
//            row1.createCell(4).setCellValue(merchant.getServiceProvided());
//            row1.createCell(5).setCellValue(merchant.getServiceProviderId());
//            row1.createCell(6).setCellValue(merchant.getHomeUrl());
//            row1.createCell(7).setCellValue(merchant.getActive());
//            row1.createCell(8).setCellValue(merchant.getSettlementServiceProvider());
//            row1.createCell(9).setCellValue(merchant.getAccountingActive());
//            row1.createCell(10).setCellValue(merchant.getSrcTypeCd());
//            row1.createCell(11).setCellValue(merchant.getReportingCategory());
//            row1.createCell(12).setCellValue(merchant.getAuthServerId());
//            row1.createCell(13).setCellValue(merchant.getBusinessContactName());
//            row1.createCell(14).setCellValue(merchant.getBusinessContactEmail());
//            row1.createCell(15).setCellValue(merchant.getTechnicalContactName());
//            row1.createCell(16).setCellValue(merchant.getTechnicalContactEmail());
//            row1.createCell(17).setCellValue(merchant.getDepartment());
//
//            Date goLiveDate = merchant.getGoLiveDate();
//            String goLiveDateStr = "";
//            if (goLiveDate != null) {
//                goLiveDateStr = goLiveDate.toString();
//            }
//            row1.createCell(18).setCellValue(goLiveDateStr);
//
//            row1.createCell(19).setCellValue(merchant.getConformation());
//            row1.createCell(20).setCellValue(merchant.getMerchantType());
//
//            Date createdOn = merchant.getCreatedOn();
//            String createdOnStr = "";
//            if (createdOn != null) {
//                createdOnStr = createdOn.toString();
//            }
//            row1.createCell(21).setCellValue(createdOnStr);
//
//            row1.createCell(22).setCellValue(merchant.getCreatedBy());
//            //i=i+1;
//            //sheet.autoSizeColumn(1);            
//            
//            Date updatedOn = merchant.getUpdatedOn();
//            String updatedOnStr = "";
//            if (createdOn != null) {
//                updatedOnStr = updatedOn.toString();
//            }
//            row1.createCell(23).setCellValue(updatedOnStr);
//
//            row1.createCell(24).setCellValue(merchant.getUpdatedBy());
//            i=i+1;
//            sheet.autoSizeColumn(1);
        }

//        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
//        wb.write(outByteStream);
//        byte [] outArray = outByteStream.toByteArray();
//        response.setContentType("application/ms-excel");
//        response.setContentLength(outArray.length);
//        response.setHeader("Expires:", "0"); // eliminates browser caching
//        response.setHeader("Content-Disposition", "attachment; filename=Merchants.xls");
//        OutputStream outStream = response.getOutputStream();
//        outStream.write(outArray);
//        outStream.flush();
//    }
}