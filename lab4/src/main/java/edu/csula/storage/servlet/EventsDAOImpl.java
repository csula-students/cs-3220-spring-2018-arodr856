package edu.csula.storage.servlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

import javax.servlet.ServletContext;

import edu.csula.models.Event;
import edu.csula.storage.EventsDAO;

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
public class EventsDAOImpl implements EventsDAO {
	private final ServletContext context;
	protected static final String CONTEXT_NAME = "events";

	public EventsDAOImpl(ServletContext context) {
		this.context = context;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Event> getAll() {
		// TODO: read a list of events from context
		Collection<Event> x = (ArrayList<Event>) context.getAttribute(EventsDAOImpl.CONTEXT_NAME);
		if (x == null) {
			Collection<Event> temp = new ArrayList<Event>();
			this.context.setAttribute(EventsDAOImpl.CONTEXT_NAME, temp);
			return temp;
		}
		return x;
	}

	@Override
	public Optional<Event> getById(int id) {
		// TODO: get a certain event given its id from context (see getAll() on
		// getting a list first and get a certain one from the list)
		for (Event event : this.getAll()) {
			if (event.getId() == id) {
				return Optional.of(event);
			}
		}
		return Optional.empty();
	}

	@Override
	public void set(int id, Event event) {
		// TODO: set a certain event given id to be different from context
		// for (Event currentEvent : getAll()) {
		// if (currentEvent.getId() == id) {
		// currentEvent = event;
		// break;
		// }
		// }

		Collection<Event> temp = getAll();
		Iterator<Event> iterator = temp.iterator();
		while (iterator.hasNext()) {
			Event x = iterator.next();
			if (x.getId() == id) {
				iterator.remove();
				break;
			}

		}
		add(event);

	}

	@Override
	public void add(Event event) {
		Collection<Event> events = getAll();
		events.add(event);

	}
}
