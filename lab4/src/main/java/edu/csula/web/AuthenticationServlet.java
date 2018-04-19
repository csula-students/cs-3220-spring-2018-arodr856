package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/auth")
public class AuthenticationServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set the content type to HTML
		response.setContentType("text/html");

		// Get a Print Writer
		PrintWriter out = response.getWriter();

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
		out.println("<h1>Hello login servlet!</h1>");
		out.println("<div class=\"container\">");

		out.println("	<form action='' method='POST' >");
		
		out.println("		<label for='userName'>");
		out.println("		<input type='text' name='userName' id='userName'>");
		out.println("		<label for='password'>");
		out.println("		<input type='text' name='password' id='password'>");
		
		
		out.println("	</form>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle login
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO: handle logout
	}
}
