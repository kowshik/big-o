package general;

public class PowerFunctions {

	public static double pow(int x, int y) {
		if (y < 0) {
			return 1.0F / pow(x + 0L, (y * -1) + 0L);
		}

		return pow(x + 0L, y + 0L);
	}

	private static long pow(long x, long y) {
		if (y < 0) {
			throw new IllegalArgumentException(String.format(
					" y can't be negative. you passed: %d", y));
		}

		if (y == 0) {
			if (x == 0) {
				return 0;
			}

			return 1;
		}

		if (y == 1) {
			return x;
		}

		long pow = pow(x, y / 2);
		if (y % 2 == 0) {
			return pow * pow;
		}

		return pow * pow * x;
	}

	public static boolean isPerfectPower(int n) {
		int num = (n < 0) ? n * -1 : n;

		if (num == 0 || num == 1) {
			return true;
		}

		int bMax = new Double(Math.floor(Math.log(num))).intValue() + 1;

		for (int b = 2; b <= bMax; b++) {
			int start = 0;
			int end = num / 2;

			while (start <= end) {
				int middle = (start + end) / 2;
				int result = new Double(Math.pow(middle, b)).intValue();

				if (result == num) {
					return true;
				} else if (result > num) {
					end = middle - 1;
				} else {
					start = middle + 1;
				}
			}
		}

		return false;
	}

	public static double squareRoot(double number) {
		if (number < 0) {
			throw new IllegalArgumentException(String.format(
					"Number cannot be negative. You passed: %d", number));
		}

		if (number == 0 || number == 1) {
			return number;
		}

		double start = 2, end = number, middle, squared;
		double sqrt = 0.0f;
		while (start <= end) {
			middle = (start + end) / 2;
			System.out.println(String.format(
					"start: %f, end: %f, middle: %f\n", start, end, middle));
			squared = middle * middle;
			if (squared == number) {
				sqrt = middle;
				break;
			} else if (squared < number) {
				start = middle;
			} else {
				end = middle;
			}
		}

		return sqrt;
	}
}
