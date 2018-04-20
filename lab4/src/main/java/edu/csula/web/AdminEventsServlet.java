package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.Event;
import edu.csula.storage.EventsDAO;
import edu.csula.storage.servlet.EventsDAOImpl;

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Override
	// public void init() {
	// ArrayList<Event> eventList = new ArrayList<Event>();
	// getServletContext().setAttribute("events", eventList);
	// }

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		// getServletContext().setAttribute("dao", dao);
		Collection<Event> events = dao.getAll();
		System.out.println(events);
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
		out.println("	<form class=\"event-form\" action=\"events\" method=\"post\">");
		out.println("			<label for=\"eventName\">Event Name:</label>");
		out.println("		<input type=\"text\" name=\"name\" id=\"eventName\" class=\"form-input\">");
		out.println("<label for=\"eventDescription\">Event Description</label>");
		out.println("<textarea id=\"eventDescription\" name=\"descTextArea\"></textarea>");
		out.println("<label for=\"trigger\">Trigger at:</label>");
		out.println("<input type=\"number\" name=\"triggerInput\" id=\"trigger\" class=\"form-input\">");
		out.println("<button class=\"form-submit\" type=\"submit\">Add</button>");
		out.println("</form>");
		out.println("</div>");
		out.println("<div class=\"right-side\">");
		out.println("<table class=\"event-table\">");
		out.println("<tr>");
		out.println("<th>Name</th>");
		out.println("<th>Description</th>");
		out.println("<th>TriggerAt</th>");
		out.println("<th>Action</th>");
		out.println("</tr>");
		for (Event event : events) {
			out.println("<tr>");
			out.println("<td>" + event.getName() + "</td>");
			out.println("<td>" + event.getDescription() + "</td>");
			out.println("<td>" + event.getTriggerAt() + "</td>");
			out.println("<td>");
			out.println("<a href=\"EditEvent?id=" + event.getId() + "\"\">Edit</a>");
			out.println("<a href=\"DeleteEvent?id=" + event.getId() + "\">delete</a> ");

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
		// EventsDAOImpl dao = (EventsDAOImpl) getServletContext().getAttribute("dao");
		EventsDAOImpl dao = new EventsDAOImpl(getServletContext());
		String name = request.getParameter("name");
		String description = request.getParameter("descTextArea");
		int trigger = Integer.parseInt(request.getParameter("triggerInput"));
		dao.add(new Event(dao.getAll().size() + 1, name, description, trigger));
		response.sendRedirect("events");
	}
}
