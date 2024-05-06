package servlets;

import accountServer.AccountServerI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminPageServlet extends HttpServlet {
    public static final String PAGE_URL = "/admin";

    static final Logger logger = LogManager.getLogger(HomePageServlet.class.getName());
    private final AccountServerI accountServer;

    public AdminPageServlet(AccountServerI accountServer) {
        this.accountServer = accountServer;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        int limit = accountServer.getUsersLimit();

        logger.info("Limit: {}", limit);

        response.getWriter().print(limit);
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
