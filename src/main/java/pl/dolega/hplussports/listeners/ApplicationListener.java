package pl.dolega.hplussports.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import pl.dolega.hplussports.dao.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("in context initialize method");
        Connection connection = DBConnection.getConnectionToDatabase();
        event.getServletContext().setAttribute("dbconnection", connection);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("in context destroy method");
        Connection connection = (Connection) event.getServletContext().getAttribute("dbconnection");
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
