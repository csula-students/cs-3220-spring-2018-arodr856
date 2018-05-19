package edu.csula.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.Event;
import edu.csula.storage.EventsDAO;
import edu.csula.storage.mysql.Database;
import edu.csula.storage.mysql.EventsDAOImpl;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/admin/EditEvent")
public class EditEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		EventsDAO dao = new EventsDAOImpl(new Database());
		Event event = dao.getById(id).get();

		request.setAttribute("event", event);
		request.getRequestDispatcher("/WEB-INF/edit-event.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("id: " + id);
		EventsDAO dao = new EventsDAOImpl(new Database());
		String name = request.getParameter("name");
		String description = request.getParameter("descTextArea");
		int triggerAt = Integer.parseInt(request.getParameter("triggerInput"));
		Event event = new Event(id, name, description, triggerAt);
		dao.set(id, event);
		response.sendRedirect("events");
	}

}
