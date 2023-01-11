package pl.dolega.hplussports.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pl.dolega.hplussports.beans.Product;
import pl.dolega.hplussports.dao.ApplicationDao;

import java.io.*;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

@WebServlet(name = "SearchServlet", value = "/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String searchString = request.getParameter("search");
        ApplicationDao dao = new ApplicationDao();

        try {
            List<Product> products = dao.searchProducts(searchString);
                String page = getHTMLString(request.getServletContext().getRealPath("/html/searchResults.html"), products);
                response.getWriter().write(page);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public String getHTMLString(String filePath, List<Product> products) throws IOException {

        BufferedReader reader;

        if (products.isEmpty()) {
            reader = new BufferedReader(new FileReader(getServletContext().getRealPath("/html/index.html")));
        } else {
            reader = new BufferedReader(new FileReader(filePath));
        }

        String line;

        StringBuffer buffer = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();

        String page = buffer.toString();

        if (!products.isEmpty()) {
            page = MessageFormat.format(page,
                    products.get(0).getProductImagePath(),
                    products.get(1).getProductImagePath(),
                    products.get(2).getProductImagePath(),
                    products.get(0).getProductName(),
                    products.get(1).getProductName(),
                    products.get(2).getProductName(),
                    0);
        }

        return page;
    }
}
