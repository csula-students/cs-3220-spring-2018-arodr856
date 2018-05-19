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
import edu.csula.storage.servlet.UsersDAOImpl;

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {


	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		// PrintWriter out = response.getWriter();
		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		getServletContext().setAttribute("dao", dao);
		Collection<Event> events = dao.getAll();
		request.setAttribute("events", events);
		request.getRequestDispatcher("../WEB-INF/admin-events.jsp").forward(request, response);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventsDAOImpl dao = new EventsDAOImpl(getServletContext());
		String name = request.getParameter("name");
		String description = request.getParameter("descTextArea");
		int trigger = Integer.parseInt(request.getParameter("triggerInput"));
		dao.add(new Event(dao.getAll().size() + 1, name, description, trigger));
		// response.sendRedirect("events");
		request.setAttribute("events", dao.getAll());
		request.getRequestDispatcher("../WEB-INF/admin-events.jsp").forward(request, response);
	}
}
