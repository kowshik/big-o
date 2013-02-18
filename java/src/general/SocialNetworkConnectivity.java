package general;

import java.util.Arrays;

/**
 * Given a social network containing N members and a log file containing M
 * timestamps at which times pairs of members formed friendships, design an
 * algorithm to determine the earliest time at which all members are connected
 * (i.e., every member is a friend of a friend of a friend ... of a friend).
 * 
 * Assume that the log file is sorted by timestamp and that friendship is an
 * equivalence relation (i.e. it is reflexive, symmetric and transitive). The
 * running time of your algorithm should be M logN or better and use extra space
 * proportional to N.
 * 
 * Sample structure of log file:
 * 
 * T1: A B
 * T2: C D
 * T3: A D 
 * .
 * .
 * .
 * etc.
 * 
 * "T1: A B" => This means A and B became friends at time T1.
 * Note that T1 < T2 < T3 etc. (i.e. file is sorted based on timestamps in ascending order)
 * 
 * @author kprakasam
 * 
 */
class WeightedUnionFind {

	private final int[] graph;
	private final int[] sizes;
	private int numRoots;
	private final int size;

	public WeightedUnionFind(int size) {
		if (size < 0) {
			throw new IllegalArgumentException(String.format(
					"Size of the graph has to be non-negative. You passed: %d",
					size));
		}
		this.size = size;
		graph = new int[size];
		numRoots = size;

		for (int index = 0; index < size; index++) {
			graph[index] = index; // each node is a separate component.
		}

		sizes = new int[size];
		Arrays.fill(sizes, 1);
	}

	private void checkNodeIndex(int index) {
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
					String.format(
							"Index of a node in the graph has to be >= 0 and < %d. You passed: %d",
							size, index));
		}
	}

	public void union(int foo, int bar) {
		checkNodeIndex(foo);
		checkNodeIndex(bar);

		int fooRoot = root(foo);
		int barRoot = root(bar);

		if (fooRoot != barRoot) {
			numRoots -= 1;

			if (sizes[fooRoot] < sizes[barRoot]) {
				// barRoot is the new root for foo.
				graph[fooRoot] = barRoot;
				sizes[barRoot] += sizes[fooRoot];
			} else {
				// fooRoot is the new root for bar.
				graph[barRoot] = fooRoot;
				sizes[fooRoot] += sizes[barRoot];
			}
		}
	}

	public boolean allConnected() {
		return numRoots == 1;
	}

	public boolean connected(int foo, int bar) {
		checkNodeIndex(foo);
		checkNodeIndex(bar);

		return root(foo) == root(bar);
	}

	private int root(int index) {
		while (index != graph[index]) {
			index = graph[index];
		}

		return index;
	}
}

public class SocialNetworkConnectivity {
	public static void main(String[] args) {
		WeightedUnionFind w = new WeightedUnionFind(6);

		int foo = 0;
		int bar = 5;
		w.union(foo, bar);
		System.out.printf("%d => %d\n", foo, bar);
		System.out.printf("all connected? => %s\n", w.allConnected());

		foo = 1;
		bar = 5;
		w.union(foo, bar);
		System.out.printf("%d => %d\n", foo, bar);
		System.out.printf("all connected? => %s\n", w.allConnected());

		foo = 2;
		bar = 0;
		w.union(foo, bar);
		System.out.printf("%d => %d\n", foo, bar);
		System.out.printf("all connected? => %s\n", w.allConnected());

		foo = 3;
		bar = 4;
		w.union(foo, bar);
		System.out.printf("%d => %d\n", foo, bar);
		System.out.printf("all connected? => %s\n", w.allConnected());

		foo = 3;
		bar = 0;
		w.union(foo, bar);
		System.out.printf("%d => %d\n", foo, bar);
		System.out.printf("all connected? => %s\n", w.allConnected());
	}
}
