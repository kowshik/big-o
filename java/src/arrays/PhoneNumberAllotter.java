package arrays;

import common.ArrayUtils;

/**
 * You are supposed to design an API and a backend for a system that can allot
 * phone numbers to people living in a city. The API should enable the clients
 * (people in the city) to do the following:
 *
 * When a client requests for a phone number, it allots one of the available
 * numbers to them. Some clients may want fancy numbers, so they can
 * specifically ask for a number to be alloted to them. If the requested number
 * is available then the system allots it to them, otherwise the system allots
 * any available number.
 *
 * The system need not have to know which number is alloted to which client. The
 * same client may make successive requests and get multiple phone numbers for
 * himself, but the system is not bothered. At any point of time, the system
 * only knows which phone numbers are alloted and which phone numbers are free.
 */
class NoNumbersLeftException extends RuntimeException {
	private static final long serialVersionUID = -2301472541196529961L;

	public NoNumbersLeftException(String message) {
		super(message);
	}
}

public class PhoneNumberAllotter {
	private final int size;
	public int[] phoneNumbers;
	private int next;

	public PhoneNumberAllotter(int size) {
		if (size < 0) {
			throw new IllegalArgumentException(String.format(
					"Size cannot be negative. You passed: %d", size));
		}
		this.size = size;
		phoneNumbers = new int[size];
		for (int phoneNumber = 0; phoneNumber < size; phoneNumber++) {
			phoneNumbers[phoneNumber] = phoneNumber;
		}

		next = 0;
	}

	public int get() {
		if (next == phoneNumbers.length) {
			throw new NoNumbersLeftException("No phone numbers left.");
		}

		return phoneNumbers[next++];
	}

	public int get(int fancyNumber) {
		if (fancyNumber >= phoneNumbers.length) {
			throw new IllegalArgumentException(
					String.format(
							"Phone numbers requested should be in the range: %d to %d. You passed: %d.",
							0, size - 1, fancyNumber));
		}

		if (phoneNumbers[fancyNumber] <= next) {
			// We cannot allot the fancy number as it is already taken.
			// So we will allot the next available number;
			return get();
		}

		if (phoneNumbers[fancyNumber] == fancyNumber) {
			// This fancy number's slot marker was never disturbed before, so we
			// will take it now, but push it into the used window.
			ArrayUtils.swap(phoneNumbers, fancyNumber, next);
		} else {
			// This fancy number's slot marker was disturbed before.
			// So we will put the previously allotted number at the tip
			// of the window, and put the tip of the window in the previously
			// allotted number's slot marker space.
			int previouslyAllottedNumber = phoneNumbers[fancyNumber];
			phoneNumbers[fancyNumber] = fancyNumber;
			phoneNumbers[next] = previouslyAllottedNumber;
			phoneNumbers[previouslyAllottedNumber] = next;
		}

		next++;
		return fancyNumber;
	}
}
