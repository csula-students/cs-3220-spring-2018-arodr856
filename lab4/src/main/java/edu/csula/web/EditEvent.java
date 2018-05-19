package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.Event;
import edu.csula.storage.servlet.EventsDAOImpl;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/admin/EditEvent")
public class EditEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		// Get a Print Writer
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		// EventsDAOImpl dao = (EventsDAOImpl) getServletContext().getAttribute("dao");
		EventsDAOImpl dao = new EventsDAOImpl(getServletContext());
		Event event = null;
		for (Event x : dao.getAll()) {
			if (id == x.getId()) {
				event = x;
				break;
			}
		}

		// Generate the template HTML
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">");
		out.println(
				"        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
		out.println("        <title>Document</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		out.println("<form action=\"EditEvent?id=" + id + "\" method=\"post\">");
		out.println("		<label for=\"eventName\">Event Name:</label>");
		out.println("		<input type=\"text\" name=\"name\" id=\"eventName\" class=\"form-input\" value="
				+ event.getName() + ">");

		out.println("		<label for=\"eventDescription\">Event Description</label>");
		out.println("		<textarea id=\"eventDescription\" name=\"descTextArea\">" + event.getDescription()
				+ "</textarea>");

		out.println("		<label for=\"trigger\">Trigger at:</label>");
		out.println("		<input type=\"number\" name=\"triggerInput\" id=\"trigger\" class=\"form-input\" value="
				+ event.getTriggerAt() + ">");

		out.println("		<button class=\"form-submit\" type=\"submit\">Edit</button>");
		out.println("</form>");

		out.println("<form>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		// EventsDAOImpl dao = (EventsDAOImpl) getServletContext().getAttribute("dao");
		EventsDAOImpl dao = new EventsDAOImpl(getServletContext());
		String name = request.getParameter("name");
		String description = request.getParameter("descTextArea");
		int triggerAt = Integer.parseInt(request.getParameter("triggerInput"));
		Event event = new Event(id, name, description, triggerAt);
		dao.set(id, event);
		response.sendRedirect("events");
	}

}
