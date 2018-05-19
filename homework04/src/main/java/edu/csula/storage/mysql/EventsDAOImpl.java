package edu.csula.storage.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.sql.PreparedStatement;

import edu.csula.models.Event;
import edu.csula.storage.Database;
import edu.csula.storage.EventsDAO;

public class EventsDAOImpl implements EventsDAO {
	private final Database context;

	// TODO: fill the Strings with the SQL queries as "prepated statements" and
	// use these queries variable accordingly in the method below
	protected static final String getAllQuery = "SELECT * FROM events;";
	protected static final String getByIdQuery = "SELECT * FROM events where id= ?";
	protected static final String setQuery = "UPDATE events SET name = ? , description = ?, trigger_at = ? where id = ?";
	protected static final String addQuery = "INSERT into events values(null, ?,?,?,null)";
	protected static final String removeQuery = "DELETE FROM events where id = ?";

	public EventsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Event> getAll() {
		// TODO: get all events from jdbc

		List<Event> events = new ArrayList<>();

		try (Connection c = context.getConnection(); Statement stmt = c.createStatement()) {
			ResultSet rs = stmt.executeQuery(getAllQuery);
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String desc = rs.getString(3);
				int trigger = rs.getInt(4);
				Event event = new Event(id, name, desc, trigger);
				events.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return events;

	}

	@Override
	public Optional<Event> getById(int id) {
		try (Connection c = context.getConnection(); PreparedStatement ps = c.prepareStatement(getByIdQuery)) {

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int i = rs.getInt(1);
				String name = rs.getString(2);
				String desc = rs.getString(3);
				int trigger = rs.getInt(4);
				Event event = new Event(id, name, desc, trigger);
				return Optional.of(event);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			Optional.empty();
		}
		return Optional.empty();
	}

	@Override
	public void set(int id, Event event) {
		// TODO: update specific event by id
		try (Connection c = context.getConnection(); PreparedStatement ps = c.prepareStatement(setQuery)) {

			ps.setString(1, event.getName());
			ps.setString(2, event.getDescription());
			ps.setInt(3, event.getTriggerAt());
			ps.setInt(4, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void add(Event event) {
		// TODO: implement jdbc logic to add a new event
		try (Connection c = context.getConnection(); PreparedStatement ps = c.prepareStatement(addQuery)) {
			ps.setString(1, event.getName());
			ps.setString(2, event.getDescription());
			ps.setInt(3, event.getTriggerAt());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(int id) {
		// TODO: implement jdbc logic to remove event by id
		try (Connection c = context.getConnection(); PreparedStatement ps = c.prepareStatement(removeQuery)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
