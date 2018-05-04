package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.Generator;
import edu.csula.storage.servlet.GeneratorsDAOImpl;


@WebServlet("/admin/EditGenerator")
public class EditGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GeneratorsDAOImpl gdi = new GeneratorsDAOImpl(request.getServletContext());
		int id = Integer.parseInt(request.getParameter("id"));
		Generator currentGen = gdi.getById(id).get();

		request.setAttribute("gen", currentGen);
		request.getRequestDispatcher("/WEB-INF/edit-generator.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		GeneratorsDAOImpl gdi = new GeneratorsDAOImpl(getServletContext());
		String name = request.getParameter("name");
		int rate = Integer.parseInt(request.getParameter("rate"));
		int baseCost = Integer.parseInt(request.getParameter("baseCost"));
		int unlock = Integer.parseInt(request.getParameter("unlock"));
		String desc = request.getParameter("descTextArea");
		int prevId = Integer.parseInt(request.getParameter("id"));

		gdi.set(prevId, new Generator(prevId, name, desc, rate, baseCost, unlock));
		response.sendRedirect("generators");
	}

}
