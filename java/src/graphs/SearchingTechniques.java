package graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SearchingTechniques {
	public static <T> boolean bfs(Map<GraphNode<T>, List<GraphNode<T>>> graph,
			GraphNode<T> node) {
		for (Map.Entry<GraphNode<T>, List<GraphNode<T>>> entry : graph
				.entrySet()) {
			if (doBfs(graph, entry.getKey(), node)) {
				return true;
			}
		}

		return false;
	}

	private static <T> boolean doBfs(
			Map<GraphNode<T>, List<GraphNode<T>>> graph,
			GraphNode<T> startNode, GraphNode<T> nodeToBeSearched) {
		if (startNode.equals(nodeToBeSearched)) {
			return true;
		}

		Set<GraphNode<T>> visited = new HashSet<GraphNode<T>>();
		Queue<GraphNode<T>> queue = new LinkedList<GraphNode<T>>();
		visited.add(startNode);

		while (!queue.isEmpty()) {
			GraphNode<T> node = queue.remove();

			for (GraphNode<T> adjacentNode : graph.get(node)) {
				if (!visited.contains(adjacentNode)) {
					if (adjacentNode.equals(nodeToBeSearched)) {
						return true;
					}
					visited.add(adjacentNode);
					queue.add(adjacentNode);
				}
			}
		}

		return false;
	}

	public static <T> boolean dfs(Map<GraphNode<T>, List<GraphNode<T>>> graph,
			GraphNode<T> node) {
		for (Map.Entry<GraphNode<T>, List<GraphNode<T>>> entry : graph
				.entrySet()) {
			if (doDfs(graph, entry.getKey(), node)) {
				return true;
			}
		}

		return false;
	}

	private static <T> boolean doDfs(
			Map<GraphNode<T>, List<GraphNode<T>>> graph,
			GraphNode<T> startNode, GraphNode<T> nodeToBeSearched) {
		return doDfs(graph, startNode, nodeToBeSearched,
				new HashSet<GraphNode<T>>());
	}

	private static <T> boolean doDfs(
			Map<GraphNode<T>, List<GraphNode<T>>> graph,
			GraphNode<T> startNode, GraphNode<T> nodeToBeSearched,
			Set<GraphNode<T>> visited) {
		if (startNode.equals(nodeToBeSearched)) {
			return true;
		}
		visited.add(startNode);

		for (GraphNode<T> adjacentNode : graph.get(startNode)) {
			if (!visited.contains(adjacentNode)) {
				if (doDfs(graph, adjacentNode, nodeToBeSearched, visited)) {
					return true;
				}
			}
		}

		return false;
	}

	public static <T> void printTopologicalOrder(
			Map<GraphNode<T>, List<GraphNode<T>>> graph) {
		Set<GraphNode<T>> visited = new HashSet<GraphNode<T>>();
		for (Map.Entry<GraphNode<T>, List<GraphNode<T>>> entry : graph
				.entrySet()) {
			GraphNode<T> startNode = entry.getKey();
			if (!visited.contains(startNode)) {
				printTopologicalOrder(graph, startNode, visited);
			}
		}
	}

	public static <T> void printTopologicalOrder(
			Map<GraphNode<T>, List<GraphNode<T>>> graph,
			GraphNode<T> startNode, Set<GraphNode<T>> visited) {
		visited.add(startNode);
		for (GraphNode<T> adjacentNode : graph.get(startNode)) {
			if (!visited.contains(adjacentNode)) {
				printTopologicalOrder(graph, adjacentNode, visited);
			}
		}

		System.out.printf("Visited => %s\n", startNode);
	}
}
