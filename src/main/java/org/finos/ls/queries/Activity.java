package org.finos.ls.queries;

import java.util.List;

public class Activity {

	private final long score;
	private final List<String> people;
	
	public Activity(long score, List<String> people) {
		super();
		this.score = score;
		this.people = people;
	}
	
	public long getScore() {
		return score;
	}
	public List<String> getPeople() {
		return people;
	}

	@Override
	public String toString() {
		return "Activity [score=" + score + ", people=" + people + "]";
	}
}