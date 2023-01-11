package pl.dolega.hplussports.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.dolega.hplussports.beans.User;
import pl.dolega.hplussports.dao.ApplicationDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;

@WebServlet("/registerUser")
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String activity = request.getParameter("activity");
        int age = Integer.parseInt(request.getParameter("age"));

        User user = new User(username, password, firstName, lastName, age, activity);

        ApplicationDao dao = new ApplicationDao();
        int rows = dao.registerUser(user);

        String infoMessage;
        if (rows == 0) {
            infoMessage = "Sorry, an error occurred!";
        } else {
            infoMessage = "User registered successfully";
        }

        String page = getHTMLString(request.getServletContext().getRealPath("/html/register.html"), infoMessage);
        response.getWriter().write(page);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = getHTMLString(request.getServletContext().getRealPath("/html/register.html"), "");
        response.getWriter().write(page);
    }

    public String getHTMLString(String filePath, String message) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        StringBuffer buffer = new StringBuffer();
        while ((line= reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        String page = buffer.toString();

        page = MessageFormat.format(page, message);

        return page;
    }
}
