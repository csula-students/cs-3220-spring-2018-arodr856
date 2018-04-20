package edu.csula.storage.servlet;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import edu.csula.models.User;
import edu.csula.storage.UsersDAO;

/**
 * To abstract the storage access from the business layer using HttpSession
 */
public class UsersDAOImpl implements UsersDAO {
	private final HttpSession context;
	protected static final String CONTEXT_NAME = "users";

	public UsersDAOImpl(HttpSession context) {
		this.context = context;
	}

	@Override
	public boolean authenticate(String username, String password) {
		// TODO: check if username/password combination is valid and store the
		// username/password into the session
		if (username.equals("admin") && password.equals("cs3220password")) {
			this.context.setAttribute(CONTEXT_NAME, new User(0, username, password));
			return true;
		}
		return false;
	}

	@Override
	public Optional<User> getAuthenticatedUser() {
		// TODO: return the authenticated user if there is any
		if (this.context.getAttribute(CONTEXT_NAME) != null) {
			return Optional.of((User) this.context.getAttribute(CONTEXT_NAME));
		}
		return Optional.empty();
	}

	@Override
	public void logout() {
		// TOOD: log user out using `invalidate`
		this.context.invalidate();
	}

}
