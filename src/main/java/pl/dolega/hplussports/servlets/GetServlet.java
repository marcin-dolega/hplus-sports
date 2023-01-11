package pl.dolega.hplussports.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/getServlet")
public class GetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String value = request.getParameter("name");
        String htmlResponse = "<html><h3>Welcome to Servlets!</h3></html>";
        response.getWriter().println(htmlResponse + " " + value);
    }
}
