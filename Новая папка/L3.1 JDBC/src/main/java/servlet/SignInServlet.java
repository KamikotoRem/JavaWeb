package servlet;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet{

    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {

        this.accountService = accountService;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login == null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Login is empty");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile currentProfile = accountService.getUserByLogin(login);

        if (!currentProfile.getLogin().equals(login) || !currentProfile.getPassword().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (currentProfile.getLogin().equals(login) && currentProfile.getPassword().equals(password)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Authorized: " + login);
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }



    }



}
