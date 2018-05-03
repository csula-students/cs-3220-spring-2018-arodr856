package edu.csula.web;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.Event;
import edu.csula.storage.servlet.EventsDAOImpl;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/admin/DeleteEvent")
public class DeleteEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		EventsDAOImpl dao = new EventsDAOImpl(getServletContext());
		Collection<Event> temp = dao.getAll();
		Iterator<Event> iterator = temp.iterator();
		while (iterator.hasNext()) {
			Event event = iterator.next();
			if (event.getId() == id) {
				iterator.remove();
				break;
			}
		}
		response.sendRedirect("events");
	}

}
