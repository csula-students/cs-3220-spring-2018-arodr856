package edu.csula.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.csula.models.GameState;
import edu.csula.storage.EventsDAO;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.mysql.Database;
import edu.csula.storage.mysql.EventsDAOImpl;
import edu.csula.storage.servlet.GeneratorsDAOImpl;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		
		EventsDAO eventsDAO = new EventsDAOImpl(new Database());
		GeneratorsDAO genDAO = new GeneratorsDAOImpl(getServletContext());
		GameState gameState = new GameState(genDAO.getAll(), eventsDAO.getAll());

		String json = gson.toJson(gameState);
		request.setAttribute("state", json);
		request.getRequestDispatcher("/WEB-INF/game.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
