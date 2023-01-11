package pl.dolega.hplussports.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/getServlet", initParams = @WebInitParam(name="URL", value = "http://www.weatherservice.com"))
public class GetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletConfig config = getServletConfig();
        System.out.println(config.getInitParameter("URL"));
        String value = request.getParameter("name");
        String htmlResponse = "<html><h3>Welcome to Servlets!</h3></html>";
        response.getWriter().println(htmlResponse + " " + value);
        response.getWriter().println(config.getInitParameter("URL"));
    }
}
