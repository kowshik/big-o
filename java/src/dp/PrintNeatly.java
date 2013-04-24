package dp;

import java.util.Arrays;

/**
 * You are given a sequence of words, and a limit on the number of characters
 * that can be put in one line (line width). You need to print the text neatly.
 * i.e. put line breaks in the given sequence of words such that the lines are
 * printed neatly. The condition is that the length of each word should be
 * smaller than the line width.
 *
 * The idea here is to have balanced lines. The extra spaces includes spaces put
 * at the end of every line except the last one. The problem is to minimize the
 * following total cost.
 *
 * Cost of a line = (Number of extra spaces in the line) ^ 3
 * Total cost = Sum of costs for all lines
 *
 * For example, consider the following string and line width M = 15.
 * "Geeks for Geeks presents word wrap problem"
 *
 * Following is the optimized arrangement of words in 3 lines:
 *
 * Geeks for Geeks|
 * presents word  |
 * wrap problem   |
 *
 * The total extra spaces in line 1, line 2 and line 3 are 0, 2 and 3
 * respectively. So optimal value of total cost is 0 + 2*2 + 3*3 = 13.
 */
public class PrintNeatly {

	private static final long INFINITY = -1;

	public static void printNeatly(String[] input, int width) {
		check(input, width);

		long[][] cost = new long[input.length][input.length];

		for (int j = 0; j < input.length; j++) {
			for (int i = 0; i <= j; i++) {
				cost[i][j] = cost(input, i, j, width);
			}
		}

		int[] print = new int[input.length];
		Arrays.fill(print, -1);

		long[] solution = new long[input.length];
		solution[0] = cost[0][0];
		if (cost[0][0] != INFINITY) {
			print[0] = 0;
		}

		for (int j = 1; j < input.length; j++) {
			int printIndex = 0;
			long minCost = cost[0][j];
			for (int i = 1; i <= j; i++) {
				if (solution[i - 1] != INFINITY
						&& cost[i][j] != INFINITY
						&& (minCost == INFINITY || solution[i - 1] + cost[i][j] < minCost)) {
					minCost = solution[i - 1] + cost[i][j];
					printIndex = i;
				}
			}

			solution[j] = minCost;
			print[j] = printIndex;
		}

		printSolution(input, print, print.length - 1, width);
	}

	private static void check(String[] input, int width) {
		if (width <= 0) {
			throw new IllegalArgumentException(String.format(
					"width: %d has to be positive.", width));
		}

		for (String s : input) {
			if (input == null) {
				System.out.println("Strings cannot be null in the input.");
			}

			if (s.length() > width - 1) {
				throw new IllegalArgumentException(
						String.format(
								"Length of the input word: %s is greater than max allowed width of %d characters for any word.",
								s, width - 1));
			}
		}

	}

	private static void printSolution(String[] words, int[] print, int n,
			int width) {
		if (n >= 0) {
			printSolution(words, print, print[n] - 1, width);
			int index = print[n];
			System.out.printf("%s", words[index]);
			width -= words[index].length();
			++index;
			for (; index <= n; index++) {
				System.out.printf(" %s", words[index]);
				width -= words[index].length() + 1;
			}

			while (width > 0) {
				System.out.print(" ");
				width--;
			}

			System.out.println("|");
		}
	}

	private static long cost(String[] input, int i, int j, int width) {
		int lineLength = j - i;
		for (int index = i; index <= j; index++) {
			lineLength += input[index].length();
		}

		if (lineLength > width) {
			return INFINITY;
		}

		long extraSpaces = width - lineLength;
		return extraSpaces * extraSpaces * extraSpaces;
	}

	public static void main(String[] args) {
		String input = "Tendulkar has been honoured with the Padma Vibhushan award,";
		input += " India's second highest civilian award, and the Rajiv Gandhi Khel Ratna";
		input += " award, India's highest sporting honour. He was also the first sportsperson";
		input += " and the first one without aviation background to be awarded the honorary rank";
		input += " of Group Captain by the Indian Air Force. Tendulkar has received honorary";
		input += " doctorates from University of Mysore and Rajiv Gandhi University of Health Sciences.";

		int width = 50;
		System.out.printf("======= WIDTH: %d =======\n", width);
		printNeatly(input.split(" "), width);

		width = 75;
		System.out.printf("======= WIDTH: %d =======\n", width);
		printNeatly(input.split(" "), width);

		width = 100;
		System.out.printf("======= WIDTH: %d =======\n", width);
		printNeatly(input.split(" "), width);

		width = 200;
		System.out.printf("======= WIDTH: %d =======\n", width);
		printNeatly(input.split(" "), width);

		input = "Geeks for Geeks presents word wrap problem";
		width = 15;
		System.out.printf("======= WIDTH: %d =======\n", width);
		printNeatly(input.split(" "), width);
	}
}
