package general;

public class BinaryGap {
	public int binaryGap(int number) {
		while (number != 0 && (number & 1) == 0) {
			number >>= 1;
		}

		if (number == 0) {
			return 0;
		}

		int count = 0;
		int max = Integer.MIN_VALUE;
		while (number != 0) {
			if ((number & 1) == 0) {
				count++;
			} else {
				if (count > max) {
					max = count;
				}
				count = 0;
			}

			number >>= 1;
		}

		return count;
	}
}
