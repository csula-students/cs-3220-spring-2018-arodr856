package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.Generator;
import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.servlet.UsersDAOImpl;

@WebServlet("/admin/generators")
public class AdminGeneratorsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/admin-generator.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GeneratorsDAOImpl gdi = new GeneratorsDAOImpl(getServletContext());
		String name = request.getParameter("name");
		int rate = Integer.parseInt(request.getParameter("rate"));
		int baseCost = Integer.parseInt(request.getParameter("baseCost"));
		int unlock = Integer.parseInt(request.getParameter("unlock"));
		String desc = request.getParameter("descTextArea");
		gdi.add(new Generator(gdi.getAll().size(), name, desc, rate, baseCost, unlock));
		response.sendRedirect("generators");
	}
}
