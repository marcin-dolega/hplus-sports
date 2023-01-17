package pl.dolega.hplussports.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.dolega.hplussports.beans.Product;
import pl.dolega.hplussports.dao.ApplicationDao;
import pl.dolega.hplussports.dao.DBConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // collect search string from the form
        String searchString = request.getParameter("search");

        Connection connection = (Connection) getServletContext().getAttribute("dbconnection");

//        request.getSession().setAttribute("searchString", searchString);

        // call DAO layer and get all products for search criteria
        ApplicationDao dao = new ApplicationDao();
        List<Product> products = null;

        try {
            products = dao.searchProducts(searchString, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // write products data back to the client browser
        request.setAttribute("products", products);
        request.getRequestDispatcher("/html/searchResults.jsp").forward(request, response);

    }

    public String getHTMLString(String filePath, List<Product> products) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line="";
        StringBuffer buffer = new StringBuffer();
        while((line=reader.readLine())!=null){
            buffer.append(line);
        }

        reader.close();
        String page = buffer.toString();

        page = MessageFormat.format(page, products.get(0).getProductImgPath(),
                products.get(1).getProductImgPath(),products.get(2).getProductImgPath(),
                products.get(0).getProductName(),products.get(1).getProductName(),
                products.get(2).getProductName(),0);

        return page;

    }
}
