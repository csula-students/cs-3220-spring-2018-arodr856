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

		int id = Integer.parseInt(request.getParameter("id"));

		EventsDAOImpl dao = new EventsDAOImpl(getServletContext());
		Event event = null;
		for (Event x : dao.getAll()) {
			if (id == x.getId()) {
				event = x;
				break;
			}
		}

		request.setAttribute("event", event);
		request.getRequestDispatcher("/WEB-INF/edit-event.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		EventsDAOImpl dao = new EventsDAOImpl(getServletContext());
		String name = request.getParameter("name");
		String description = request.getParameter("descTextArea");
		int triggerAt = Integer.parseInt(request.getParameter("triggerInput"));
		Event event = new Event(id, name, description, triggerAt);
		dao.set(id, event);
		response.sendRedirect("events");
	}

}
