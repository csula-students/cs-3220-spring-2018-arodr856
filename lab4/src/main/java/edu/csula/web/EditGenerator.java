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

/**
 * Servlet implementation class Edit
 */
@WebServlet("/admin/EditGenerator")
public class EditGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditGenerator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		GeneratorsDAOImpl gdi = new GeneratorsDAOImpl(request.getServletContext());
		int id = Integer.parseInt(request.getParameter("id"));
		Generator currentGen = gdi.getById(id).get();
		List<Generator> generators = gdi.getAll();
		System.out.println(generators);
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Game Event</title>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../app.css \">");
		out.println("</head>");
		out.println("<body>");

		out.println("<h1>Incremental Game Framework</h1>");
		out.println("<div class=\"nav-bar\">");
		// out.println(" <nav>");
		// out.println(" <a href=\"#\" class=\"nav-item\">Game Information</a> |");
		// out.println(" <a href=\"generators\" class=\"nav-item\">Generators</a> |");
		// out.println(" <a href=\"events\" class=\"nav-item\">Events</a>");
		// out.println(" </nav>");
		out.println("</div>");
		out.println("<div class=\"container\">");
		out.println("	<div class=\"left-side\">");
		out.println("	<form class=\"event-form\" action=\"EditGenerator?id=" + id + "\" method=\"post\">");
		out.println("			<label for=\"generatorName\">Generator Name:</label>");
		out.println("			<input type=\"text\" name=\"name\" id=\"generatorName\" class=\"form-input\" value=\""
				+ currentGen.getName() + "\">");
		out.println("			<label for=\"generatorRate\">Generator Rate</label>");
		out.println("			<input type=\"number\" name=\"rate\" id=\"generatorRate\" class=\"form-input\" value=\""
				+ currentGen.getRate() + "\">");
		out.println("			<label for=\"baseCost\">Base Cost</label>");
		out.println("			<input type=\"number\" name=\"baseCost\" id=\"baseCost\" class=\"form-input\" value=\""
				+ currentGen.getBaseCost() + "\">");
		out.println("			<label for=\"unlock\">Unlock At</label>");
		out.println("			<input type=\"number\" name=\"unlock\" id=\"unlock\" class=\"form-input\" value=\""
				+ currentGen.getUnlockAt() + "\">");
		out.println("			<label for=\"generatorDescription\">Generator Description</label>");
		out.println("			<textarea id=\"generatorDescription\" name=\"descTextArea\">"
				+ currentGen.getDescription() + "</textarea>");
		out.println("			<button class=\"form-submit\" type=\"submit\">Edit</button>");
		out.println("	</form>");
		out.println("</div>");

		out.println("</div>");

		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
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
