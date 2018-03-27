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

	@Override
	public void init() {
		ArrayList<Event> eventList = new ArrayList<Event>();
		eventList.add(new Event(0, "rain", "Raining candy", 10));
		eventList.add(new Event(1, "tree", "Candy growing on a tree", 20));
		getServletContext().setAttribute("events", eventList);

	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		System.out.println(events);
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Game Event</title>");
		out.println(
				"<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\n"
						+ "");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Incremental Game Framework</h1>");

		out.println("<nav>");
		out.println("<a href='../admin/generators'> Game Generator </a>");
		out.println("</nav>");
		out.println("<form class='form-group'>");
		out.println("<label for='eventName'>Event Name:</label><br/>");
		out.println("<input  type='text' id='eventName'/><br/>");

		out.println("<label for='eventDesc'>Event Description :</label><br/>");
		out.println("<textarea for='eventDesc' ></textarea><br/>");

		out.println("<label for='trigger'>Trigger at:</label><br/>");
		out.println("<input type='number' id='trigger'/>");
		out.println("</form>");
		out.println("<table class='table table-striped table-dark table-hover'>");

		out.println("<thead><tr>");
		out.println("<th>Name</th><th>Description</th><th>TriggerAt</th>");
		out.println("</tr></thead>");
		out.println("<tbody>");

		for (Event event : events) {
			out.println("<tr>");

			out.println("<td>" + event.getName() + "</td>" + "<td>" + event.getDescription() + "</td>" + "<td>"
					+ event.getTriggerAt() + "</td>");
			out.println("</tr>");
		}
		out.println("</tbody>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
	}
}
