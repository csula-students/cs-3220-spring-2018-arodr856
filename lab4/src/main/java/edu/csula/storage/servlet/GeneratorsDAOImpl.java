package edu.csula.storage.servlet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import edu.csula.models.Generator;
import edu.csula.storage.GeneratorsDAO;

/**
 * To abstract the storage access from the business layer using ServletContext
 * (application scope). This implementation will store the underlying data under
 * the application scope and read from it accordingly.
 *
 * As ServletContext is like a global HashMap, it's important for you to add a
 * context name to separate out the different section of data (e.g. events vs
 * generators) so that you can have the application scope looks like below:
 *
 * ```json { "events": [ { "id": 0, "name": "event-1", "description": "..." } ],
 * "generators": [ { "id": 0, "name": "generator-1", "description": "..." } ] }
 * ```
 */
public class GeneratorsDAOImpl implements GeneratorsDAO {
	private final ServletContext context;
	protected static final String CONTEXT_NAME = "generators";

	public GeneratorsDAOImpl(ServletContext context) {
		this.context = context;
	}

	@Override
	public List<Generator> getAll() {
		List<Generator> list = (List<Generator>) this.context.getAttribute(CONTEXT_NAME);
		if (list != null) {
			return list;
		} else {
			list = new ArrayList<>();
			this.context.setAttribute(CONTEXT_NAME, list);
			return list;
		}

	}

	@Override
	public Optional<Generator> getById(int id) {
		for (Generator gen : getAll()) {
			if (gen.getId() == id) {
				return Optional.of(gen);
			}
		}
		return Optional.empty();
	}

	@Override
	public void set(int id, Generator generator) {
		List<Generator> list = this.getAll();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				list.set(i, generator);
				break;
			}
		}
	}

	@Override
	public void add(Generator generator) {
		this.getAll().add(generator);
	}

	public void remove(int id) {
		List<Generator> list = this.getAll();
		Iterator<Generator> i = list.iterator();
		while (i.hasNext()) {
			Generator gen = i.next();
			if (gen.getId() == id) {
				i.remove();
				break;
			}
		}
	}

}
