package general;

/**
 * Write a program to print Pascal's triangle.
 * 
 * See this: http://en.wikipedia.org/wiki/Binomial_theorem. The trick is that
 * for a given value N, each binomial coffecient can be calculated from the
 * previous binomial coefficient.
 */
public class PascalsTriangle {
	public static void printPascal(int n) {
		for (int line = 1; line <= n; line++) {
			int pascalsValue = 1;
			for (int i = 1; i <= line; i++) {
				System.out.printf("%d ", pascalsValue);
				pascalsValue = pascalsValue * (line - i) / i;
			}
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		printPascal(10);
	}
}
