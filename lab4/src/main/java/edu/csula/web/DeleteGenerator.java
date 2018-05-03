package edu.csula.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.GeneratorsDAOImpl;

/**
 * Servlet implementation class DeleteGenerator
 */
@WebServlet("/admin/DeleteGenerator")
public class DeleteGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		GeneratorsDAOImpl gdi = new GeneratorsDAOImpl(getServletContext());
		gdi.remove(id);
		response.sendRedirect("generators");

	}

}
