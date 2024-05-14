package servlets;

import resourceServer.ResourceServer;
import resources.TestResource;
import sax.ReadXMLFileSAX;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class ResourcePageServlet extends HttpServlet {
    public static final String PAGE_URL = "/resources";
    private final ResourceServer resourceServer;

    public ResourcePageServlet(ResourceServer resourceServer) {
        this.resourceServer = resourceServer;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String path = request.getParameter("path");

        if (path != null) {
            TestResource testResource = (TestResource) ReadXMLFileSAX.readXML(path);
            resourceServer.setTestResource(testResource);
        }

        response.getWriter().println("!");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
