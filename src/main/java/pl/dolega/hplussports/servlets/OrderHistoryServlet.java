package pl.dolega.hplussports.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.dolega.hplussports.beans.Order;
import pl.dolega.hplussports.dao.ApplicationDao;
import pl.dolega.hplussports.dao.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/orderHistory")
public class OrderHistoryServlet extends HttpServlet {

    private static final Long serialVersionUIS = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get username from session
        String username = (String) request.getSession().getAttribute("username");

        // call dao and get order history
        ApplicationDao dao = new ApplicationDao();
        List<Order> orders = dao.getOrders(username);

        // set order data in request
        request.setAttribute("orders", orders);

        // forward to home jsp
        request.getRequestDispatcher("/html/home.jsp").forward(request, response);

    }

}
