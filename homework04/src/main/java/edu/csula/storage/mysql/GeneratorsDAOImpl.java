package edu.csula.storage.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Generator;

public class GeneratorsDAOImpl implements GeneratorsDAO {
	private final Database context;

	// TODO: fill the Strings with the SQL queries as "prepated statements" and
	// use these queries variable accordingly in the method below
	protected static final String getAllQuery = "SELECT * FROM generators;";
	protected static final String getByIdQuery = "";
	protected static final String setQuery = "";
	protected static final String addQuery = "";
	protected static final String removeQuery = "";

	public GeneratorsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Generator> getAll() {
		// TODO: get all generators from jdbc

		List<Generator> gens = new ArrayList<>();
		try (Connection c = this.context.getConnection(); Statement stmt = c.createStatement()) {
			ResultSet rs = stmt.executeQuery(getAllQuery);

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				int rate = rs.getInt(4);
				int baseCost = rs.getInt(5);
				int unlockAt = rs.getInt(6);
				Generator gen = new Generator(id, name, description, rate, baseCost, unlockAt);
				gens.add(gen);
			}
		} catch (SQLException e) {
			return new ArrayList<>();
		}
		return gens;
	}

	@Override
	public Optional<Generator> getById(int id) {
		// TODO: get specific generator by id
		return Optional.empty();
	}

	@Override
	public void set(int id, Generator generator) {
		// TODO: update specific generator by id
	}

	@Override
	public void add(Generator generator) {
		// TODO: implement jdbc logic to add a new generator
	}

	@Override
	public void remove(int id) {
		// TODO: implement jdbc logic to remove generator by id
	}
}
