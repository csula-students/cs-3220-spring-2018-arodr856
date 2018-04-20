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

@WebServlet("/admin/generators")
public class AdminGeneratorsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
		// EventsDAO dao = new EventsDAOImpl(getServletContext());
		GeneratorsDAOImpl gdi = new GeneratorsDAOImpl(request.getServletContext());
		getServletContext().setAttribute("dao", gdi);
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
		out.println("	<nav>");
		out.println("		<a href=\"#\" class=\"nav-item\">Game Information</a> |");
		out.println("		<a href=\"generators\" class=\"nav-item\">Generators</a> |");
		out.println("		<a href=\"events\" class=\"nav-item\">Events</a>");
		out.println("	</nav>");
		out.println("</div>");
		out.println("<div class=\"container\">");
		out.println("	<div class=\"left-side\">");
		out.println("	<form class=\"event-form\" action=\"generators\" method=\"post\">");
		out.println("			<label for=\"generatorName\">Generator Name:</label>");
		out.println("			<input type=\"text\" name=\"name\" id=\"generatorName\" class=\"form-input\">");
		out.println("			<label for=\"generatorRate\">Generator Rate</label>");
		out.println("			<input type=\"number\" name=\"rate\" id=\"generatorRate\" class=\"form-input\">");
		out.println("			<label for=\"baseCost\">Base Cost</label>");
		out.println("			<input type=\"number\" name=\"baseCost\" id=\"baseCost\" class=\"form-input\">");
		out.println("			<label for=\"unlock\">Unlock At</label>");
		out.println("			<input type=\"number\" name=\"unlock\" id=\"unlock\" class=\"form-input\">");
		out.println("			<label for=\"generatorDescription\">Generator Description</label>");
		out.println("			<textarea id=\"generatorDescription\" name=\"descTextArea\"></textarea>");
		out.println("			<button class=\"form-submit\" type=\"submit\">Add</button>");
		out.println("	</form>");
		out.println("</div>");

		out.println("<div class=\"right-side\">");
		out.println("	<table class=\"event-table\">");
		out.println("	<tr>");
		out.println("		<th>Name</th>");
		out.println("		<th>Rate</th>");
		out.println("		<th>Cost</th>");
		out.println("		<th>Unlock At</th>");
		out.println("		<th>Action</th>");
		out.println("	</tr>");
		for (Generator generator : generators) {
			out.println("<tr>");
			out.println("<td>" + generator.getName() + "</td>");
			out.println("<td>" + generator.getRate() + "</td>");
			out.println("<td>" + generator.getBaseCost() + "</td>");
			out.println("<td>" + generator.getUnlockAt() + "</td>");
			out.println("<td>");
			out.println("<a href=\"EditGenerator?id=" + generator.getId() + "\"\">Edit</a>");
			out.println("<a href=\"DeleteGenerator?id=" + generator.getId() + "\">delete</a> ");

			out.println("</td>");
			out.println("</tr>");
		}

		out.println("</table>");
		out.println("</div>");
		out.println("</div>");

		out.println("</body>");
		out.println("</html>");

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
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
