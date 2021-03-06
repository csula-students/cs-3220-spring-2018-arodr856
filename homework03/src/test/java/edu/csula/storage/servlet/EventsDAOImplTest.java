package edu.csula.storage.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;

import edu.csula.models.Event;
import edu.csula.storage.EventsDAO;

public class EventsDAOImplTest {
	private ServletContext context;
	private EventsDAO dao;
	private List<Event> list;

	@Before
	public void setup() {
		context = mock(ServletContext.class);
		doAnswer((Answer) invocation -> {
			return list;
		}).when(context).getAttribute(eq(EventsDAOImpl.CONTEXT_NAME));
		doAnswer((Answer) invocation -> {
			list = (List<Event>) invocation.getArgument(1);
			return null;
		}).when(context).setAttribute(eq(EventsDAOImpl.CONTEXT_NAME), any(List.class));
		dao = new EventsDAOImpl(context);
	}

	@Test
	public void getAll() throws Exception {
		// set up initial mock state
		Collection<Event> mockEvents = new ArrayList<>();
		mockEvents.add(new Event(1, "name", "desc", 10));
		when(context.getAttribute(EventsDAOImpl.CONTEXT_NAME)).thenReturn(mockEvents);

		// actual method execution
		Collection<Event> actualEvents = dao.getAll();

		// assert and verify
		verify(context).getAttribute(EventsDAOImpl.CONTEXT_NAME);
		assertEquals(1, actualEvents.size());
	}

	@Test
	public void getAllWhenNull() throws Exception {
		when(context.getAttribute(EventsDAOImpl.CONTEXT_NAME)).thenReturn(null);
		// actual method execution
		Collection<Event> actualEvents = dao.getAll();

		// should return an empty list
		assertEquals(0, actualEvents.size());
	}

	@Test
	public void getById() throws Exception {
		// set up initial mock state
		Collection<Event> mockEvents = new ArrayList<>();
		mockEvents.add(new Event(1, "name", "desc", 10));
		when(context.getAttribute(EventsDAOImpl.CONTEXT_NAME)).thenReturn(mockEvents);

		// actual method execution
		Optional<Event> actualEvent = dao.getById(1);

		// assert and verify
		verify(context).getAttribute(EventsDAOImpl.CONTEXT_NAME);
		assertTrue(actualEvent.isPresent());
		assertEquals(actualEvent.get(), new Event(1, "name", "desc", 10));
	}

	@Test
	public void set() throws Exception {
		// set up initial mock state
		dao.add(new Event(1, "name", "desc", 10));

		// actual method execution
		dao.set(1, new Event(1, "new name", "description", 20));
		Optional<Event> actualEvent = dao.getById(1);

		// assert and verify
		assertTrue(actualEvent.isPresent());
		assertEquals(new Event(1, "new name", "description", 20), actualEvent.get());
	}

	@Test
	public void add() throws Exception {
		// set up
		Collection<Event> expected = new ArrayList<>();
		expected.add(new Event(1, "new event", "description", 10));
		// actual execution
		dao.add(new Event(1, "new event", "description", 10));
		// verify
		assertEquals(expected, dao.getAll());
	}
}
