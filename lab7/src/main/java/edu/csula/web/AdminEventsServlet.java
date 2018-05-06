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
import edu.csula.models.Generator;
import edu.csula.storage.EventsDAO;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.servlet.GeneratorsDAOImpl;
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
		request.getRequestDispatcher("/WEB-INF/admin-events.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventsDAOImpl dao = new EventsDAOImpl(getServletContext());
		String name = request.getParameter("name");
		String description = request.getParameter("descTextArea");
		int trigger = Integer.parseInt(request.getParameter("triggerInput"));
		dao.add(new Event(dao.getAll().size() + 1, name, description, trigger));
		response.sendRedirect("events");
	}
}
