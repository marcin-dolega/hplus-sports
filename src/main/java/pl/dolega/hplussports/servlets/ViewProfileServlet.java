package pl.dolega.hplussports.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.dolega.hplussports.beans.User;
import pl.dolega.hplussports.dao.ApplicationDao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/getProfileDetails")
public class ViewProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get the username from the session
        System.out.println("Username in profile servlet: " + request.getSession().getAttribute("username"));
        String username = (String) request.getSession().getAttribute("username");

        // call dao and get profile details
        ApplicationDao dao = new ApplicationDao();
        User user = dao.getProfileDetails(username);

        Map<String, Double> weightSummary = new HashMap<>();
        weightSummary.put("January", 67.9);
        weightSummary.put("February", 65.9);
        weightSummary.put("March", 64.8);


        // store all information in request object
        request.setAttribute("user", user);
        request.setAttribute("weightSummary", weightSummary);

        // forward control to profile.jsp
        request.getRequestDispatcher("/html/profile.jsp").forward(request, response);

    }

}
