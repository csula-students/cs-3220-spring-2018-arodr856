package edu.csula.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.UsersDAOImpl;

@WebServlet("/admin/auth")
public class AuthenticationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/admin-authentication.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersDAOImpl udi = new UsersDAOImpl(request.getSession());
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		if (udi.authenticate(userName, password)) {
			response.sendRedirect("events");
		} else {
			response.sendRedirect("auth");
		}
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
}
