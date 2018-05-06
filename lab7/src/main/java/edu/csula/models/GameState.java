package edu.csula.models;

import java.util.Collection;

public class GameState {

	private Collection<Generator> generators;
	private Collection<Event> stories;

	public GameState(Collection<Generator> generators, Collection<Event> stories) {
		this.generators = generators;
		this.stories = stories;
	}

}
