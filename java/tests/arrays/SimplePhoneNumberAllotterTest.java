package arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SimplePhoneNumberAllotterTest {
	@Test
	public void get_RaiseErrorWhenNoNumbersAreLeft() {
		PhoneNumberAllotter tested = new SimplePhoneNumberAllotter(0);

		boolean thrown = false;
		try {
			tested.get();
		} catch (NoNumbersLeftException e) {
			thrown = true;
		}

		assertTrue(thrown);
	}

	@Test
	public void get_ReturnExpectedPhoneNumbers() {
		PhoneNumberAllotter tested = new SimplePhoneNumberAllotter(5);

		assertEquals(0, tested.get());
		assertEquals(4, tested.get(4));
		assertEquals(2, tested.get());
		assertEquals(3, tested.get());
		assertEquals(1, tested.get());
	}

	@Test
	public void get_ReturnSequentialPhoneNumbers() {
		PhoneNumberAllotter tested = new SimplePhoneNumberAllotter(5);

		assertEquals(0, tested.get());
		assertEquals(1, tested.get());
		assertEquals(2, tested.get());
		assertEquals(3, tested.get());
		assertEquals(4, tested.get());
	}

	@Test
	public void get_FancyNumber_RaiseErrorWhenNoNumbersAreLeft() {
		PhoneNumberAllotter tested = new SimplePhoneNumberAllotter(1);

		tested.get();

		boolean thrown = false;
		try {
			tested.get(0);
		} catch (NoNumbersLeftException e) {
			thrown = true;
		}

		assertTrue(thrown);
	}

	@Test
	public void get_FancyNumber_RaiseErrorWhenBadFancyNumberIsPassed() {
		PhoneNumberAllotter tested = new SimplePhoneNumberAllotter(0);

		boolean thrown = false;
		try {
			tested.get(0);
		} catch (IllegalArgumentException e) {
			thrown = true;
		}

		assertTrue(thrown);
	}

	@Test
	public void get_FancyNumber_ReturnExpectedFancyNumbers() {
		PhoneNumberAllotter tested = new SimplePhoneNumberAllotter(8);

		assertEquals(4, tested.get(4));
		assertEquals(1, tested.get(4));
		assertEquals(3, tested.get(3));
		assertEquals(2, tested.get(2));
		assertEquals(0, tested.get(1));
		assertEquals(5, tested.get(5));
		assertEquals(6, tested.get(6));
		assertEquals(7, tested.get(6));
	}
}
