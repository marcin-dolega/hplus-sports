package pl.dolega.hplussports.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pl.dolega.hplussports.dao.ApplicationDao;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// dispatch the request to login.jsp resource
		String html = "<html><h3>Please login</h3></html>";
		response.getWriter().write(html + " ");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/html/login.jsp");
		dispatcher.include(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// get the username & password from the login form
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// call DAO for validation logic
		ApplicationDao dao = new ApplicationDao();
		boolean isValidUser = dao.validateUser(username, password);

		// check if user is invalid and set up an error message
		if (isValidUser) {

			// set up the HTTP session
			HttpSession session = request.getSession();

			// set the username as an attribute
			session.setAttribute("username", username);

			// forward to home jsp
			request.getRequestDispatcher("/html/home.jsp").forward(request, response);

		} else {
			String errorMessage = "Invalid Credentials, login again";
			request.setAttribute("error", errorMessage);
			request.getRequestDispatcher("/html/login.jsp").forward(request, response);
		}

	}
}
