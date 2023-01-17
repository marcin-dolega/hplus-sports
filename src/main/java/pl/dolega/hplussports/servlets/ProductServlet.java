package pl.dolega.hplussports.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pl.dolega.hplussports.beans.Product;
import pl.dolega.hplussports.dao.ApplicationDao;
import pl.dolega.hplussports.dao.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addProducts")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("dbconnection");

        List<Product> products;

        // get the HTTPSession object
        HttpSession session = request.getSession();

        // create a cart as ArrayList for the user
        List<String> cart = (ArrayList<String>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        // add the selected product to the cart
        if (request.getParameter("product") != null) {
            cart.add(request.getParameter("product"));
        }

        session.setAttribute("cart", cart);

        // get search criteria from search servlet
        String search  = (String) session.getAttribute("search");

        // get the search results from dao
        ApplicationDao dao = new ApplicationDao();
        try {
            products = dao.searchProducts(search, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // set the search results in request scope
        request.setAttribute("products", products);

        // forward to searchResults.jsp
        request.getRequestDispatcher("/html/searchResults.jsp").forward(request, response);

    }
}
