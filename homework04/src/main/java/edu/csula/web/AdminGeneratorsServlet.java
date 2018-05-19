package edu.csula.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.Generator;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.mysql.Database;
import edu.csula.storage.mysql.GeneratorsDAOImpl;

@WebServlet("/admin/generators")
public class AdminGeneratorsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GeneratorsDAO gdi = new GeneratorsDAOImpl(new Database());
		List<Generator> gens = gdi.getAll();
		request.setAttribute("generators", gens);
		request.getRequestDispatcher("/WEB-INF/admin-generator.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GeneratorsDAO gdi = new GeneratorsDAOImpl(new Database());
		String name = request.getParameter("name");
		int rate = Integer.parseInt(request.getParameter("rate"));
		int baseCost = Integer.parseInt(request.getParameter("baseCost"));
		int unlock = Integer.parseInt(request.getParameter("unlock"));
		String desc = request.getParameter("descTextArea");
		gdi.add(new Generator(gdi.getAll().size(), name, desc, rate, baseCost, unlock));
		response.sendRedirect("generators");
	}
}
