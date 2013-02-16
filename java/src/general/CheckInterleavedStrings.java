package general;

public class CheckInterleavedStrings {

	public static boolean isValidInterleavedString(String foo, String bar,
			String target) {
		if (foo == null || bar == null || target == null) {
			return false;
		}

		if (target.length() != foo.length() + bar.length()) {
			return false;
		}

		return isValidInterleavedString(foo, 0, bar, 0, target, 0);
	}

	private static boolean isValidInterleavedString(String foo, int fooIndex,
			String bar, int barIndex, String target, int targetIndex) {
		if (targetIndex == target.length()) {
			if (fooIndex != foo.length() || barIndex != bar.length()) {
				return false;
			}

			return true;
		}

		boolean valid = false;
		char targetChar = target.charAt(targetIndex);
		boolean found = fooIndex != foo.length() ? foo.charAt(fooIndex) == targetChar
				: false;
		if (found) {
			valid = isValidInterleavedString(foo, fooIndex + 1, bar, barIndex,
					target, targetIndex + 1);
		}

		if (!valid) {
			found = barIndex != bar.length() ? bar.charAt(barIndex) == targetChar
					: false;
			if (found) {
				valid = isValidInterleavedString(foo, fooIndex, bar,
						barIndex + 1, target, targetIndex + 1);
			}
		}

		return valid;
	}

	public static void main(String[] args) {
		System.out.println(isValidInterleavedString("abc", "def", "daecfb"));
	}
}
