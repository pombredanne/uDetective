package ca.ubc.it.as.udetective.ui;

/**
 * @author UBC IT
 */

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SearchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String PAGE_404             = "/WEB-INF/view/404.jsp";
    private static final String PARAM_CWL_LOGIN_NAME = "cwlLoginName";

    private static final Logger log = LogManager.getLogger(SearchServlet.class);

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

        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/view/some_view.jsp");
        rd.forward(request, response);
    }
}