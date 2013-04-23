package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You are given a static list of english words. You are supposed to build a
 * data structure from the list of words which is both space efficient, and can
 * also efficiently answer query to check if a particular word exists in the
 * list of words. Note that the searched word can contain the letter '?' in
 * addition to regular characters. The character '?' is a wild card and
 * represents the occurrence of any random letter exactly once.
 * 
 * Examples:
 * ---------
 * 
 * Input: ["dog", "cat", "bat", "seat"]
 * 
 * Queries:
 * 
 * 1. exists("do?") => true (matches dog)
 * 2. exists("???") => true (matches dog, cat, bat)
 * 3. exists ("??a?") => true (matches seat)
 * 4. exists("ca??") => false
 * 5. exists("bats") => false
 */
class Node {
	Set<Node> nodes;
	Character character;
	boolean last;

	Node(char ch) {
		nodes = new HashSet<Node>();
		character = ch;
		last = false;
	}
}

public class SpellChecker {
	private final Set<Node> nodes;

	/**
	 * Populates a graph of letters to optimize for space and search time.
	 */
	public SpellChecker(List<String> words) {
		if (words == null) {
			throw new IllegalArgumentException("List of words can't be null.");
		}
		nodes = new HashSet<Node>();

		for (String word : words) {
			if (word != null && word.length() > 0) {
				addWordToGraph(word.toCharArray(), 0, null, nodes);
			}
		}
	}

	private void addWordToGraph(char[] word, int index, Node currentNode,
			Set<Node> nodes) {
		if (index >= word.length) {
			currentNode.last = true;
			return;
		}

		char ch = word[index];
		for (Node node : nodes) {
			if (node.character == ch) {
				addWordToGraph(word, index + 1, node, node.nodes);
				return;
			}
		}

		Node node = new Node(word[index]);
		nodes.add(node);

		addWordToGraph(word, index + 1, node, node.nodes);
	}

	/**
	 * Checks if a given word exists in the already built graph.
	 */
	public boolean exists(String word) {
		if (word == null || word.length() == 0) {
			return false;
		}

		return check(word.toCharArray(), 0, null, nodes);
	}

	private boolean check(char[] word, int index, Node currentNode,
			Set<Node> nodes) {
		if (index >= word.length) {
			if (currentNode.last) {
				return true;
			}

			return false;
		}

		if (word[index] == '?') {
			for (Node node : nodes) {
				if (check(word, index + 1, node, node.nodes)) {
					return true;
				}
			}
		} else {
			for (Node node : nodes) {
				if (node.character == word[index]) {
					return check(word, index + 1, node, node.nodes);
				}
			}
		}

		return false;
	}
	
	public static void main(String[] args) {
		List<String> words = new ArrayList<String>();
		words.add("dog");
		words.add("cat");
		words.add("mouse");
		words.add("mat");
		words.add("fat");
		
		System.out.printf("Input words: %s\n\n", words);
		SpellChecker checker = new SpellChecker(words);
		
		String word = "dog";
		System.out.printf("exists(%s) => %b\n", word, checker.exists(word));
		word = "cat";
		System.out.printf("exists(%s) => %b\n", word, checker.exists(word));
		word = "mouse";
		System.out.printf("exists(%s) => %b\n", word, checker.exists(word));
		word = "mat";
		System.out.printf("exists(%s) => %b\n", word, checker.exists(word));
		word = "fat";
		System.out.printf("exists(%s) => %b\n", word, checker.exists(word));
		
		word = "?og";
		System.out.printf("exists(%s) => %b\n", word, checker.exists(word));
		word = "??use";
		System.out.printf("exists(%s) => %b\n", word, checker.exists(word));
		word = "ma?";
		System.out.printf("exists(%s) => %b\n", word, checker.exists(word));
		word = "???";
		System.out.printf("exists(%s) => %b\n", word, checker.exists(word));
		word = "?????";
		System.out.printf("exists(%s) => %b\n\n", word, checker.exists(word));
		
		word = "?oc";
		System.out.printf("exists(%s) => %b\n", word, checker.exists(word));
		word = "ma???";
		System.out.printf("exists(%s) => %b\n", word, checker.exists(word));
		word = "????";
		System.out.printf("exists(%s) => %b\n", word, checker.exists(word));
		word = "??????";
		System.out.printf("exists(%s) => %b\n", word, checker.exists(word));
	}
}