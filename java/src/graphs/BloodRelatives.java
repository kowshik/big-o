package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * You are given a graph of persons. Each node in the graph is of type class
 * person (see below). Each person is connected to his parents.
 *
 * Two persons A and B are defined to be blood relatives if they share atleast
 * one ancestor. Write a method to check if any two persons A and B in a graph
 * are blood relatives.
 */

class Person {
	private List<Person> parents;

	public Person(Person mother, Person father) {
		if (mother == null) {
			throw new IllegalArgumentException(
					"A person's mother cannot be null.");
		}

		if (father == null) {
			throw new IllegalArgumentException(
					"A person's father cannot be null.");
		}

		parents.add(mother);
		parents.add(father);
	}

	public List<Person> getParents() {
		return new ArrayList<Person>(parents);
	}
}

public class BloodRelatives {
	public static boolean areBloodRelatives(Person a, Person b) {
		if (a == null || b == null) {
			return false;
		}

		Queue<Person> queueA = new LinkedList<Person>();
		Queue<Person> queueB = new LinkedList<Person>();

		Set<Person> visitedA = new HashSet<Person>();
		Set<Person> visitedB = new HashSet<Person>();

		queueA.add(a);
		queueB.add(b);

		while (!queueA.isEmpty() && !queueB.isEmpty()) {
			if (doBfs(queueA.remove(), queueA, visitedA, visitedB)) {
				return true;
			}

			if (doBfs(queueB.remove(), queueB, visitedB, visitedA)) {
				return true;
			}
		}

		return checkBloodRelatives(queueA, visitedA, visitedB)
				|| checkBloodRelatives(queueB, visitedB, visitedA);
	}

	private static boolean checkBloodRelatives(Queue<Person> queue,
			Set<Person> visited, Set<Person> otherVisited) {
		while (!queue.isEmpty()) {
			if (doBfs(queue.remove(), queue, visited, otherVisited)) {
				return true;
			}
		}

		return false;
	}

	private static boolean doBfs(Person person, Queue<Person> queue,
			Set<Person> visited, Set<Person> otherVisited) {
		for (Person parent : person.getParents()) {
			if (!visited.contains(parent)) {
				if (otherVisited.contains(parent)) {
					return true;
				}

				visited.add(parent);
				queue.add(parent);
			}
		}

		return false;
	}
}
