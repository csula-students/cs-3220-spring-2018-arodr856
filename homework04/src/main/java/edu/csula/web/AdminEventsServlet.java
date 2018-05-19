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
import edu.csula.storage.servlet.UsersDAOImpl;

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UsersDAOImpl udi = new UsersDAOImpl(request.getSession());
		if (!udi.getAuthenticatedUser().isPresent()) {
			response.sendRedirect("auth");
		}

		EventsDAO dao = new EventsDAOImpl(new Database());

		request.setAttribute("events", dao.getAll());
		request.getRequestDispatcher("../WEB-INF/admin-events.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventsDAO dao = new EventsDAOImpl(new Database());
		String name = request.getParameter("name");
		String description = request.getParameter("descTextArea");
		int trigger = Integer.parseInt(request.getParameter("triggerInput"));
		dao.add(new Event(dao.getAll().size() + 1, name, description, trigger));
		response.sendRedirect("events");
	}
}
