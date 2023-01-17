package pl.dolega.hplussports.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.dolega.hplussports.dao.DBConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

//@WebServlet("/home")
public class HomeServlet extends HttpServlet {

	public Connection connection = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("in doGet method");
		// forward the control to the index.html
		request.getRequestDispatcher("/html/index.html").forward(request, response);
	}

	@Override
	public void init() throws ServletException {

		System.out.println("in init method");
		// initialization activity
		connection = DBConnection.getConnectionToDatabase();
	}

	@Override
	public void destroy() {

		System.out.println("in destroy method");
		// clean up activity - close DB connection object
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
