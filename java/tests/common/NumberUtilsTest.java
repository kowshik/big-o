package common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NumberUtilsTest {

	@Test
	public void testAtoi_EmptyString() {
		boolean thrown = false;

		try {
			common.NumberUtils.atoi("");
		} catch (NumberFormatException e) {
			// This is expected.
			thrown = true;
		}

		assertTrue(thrown);
	}

	@Test
	public void testAtoi_MalformedNumberSign() {
		boolean thrown = false;

		try {
			common.NumberUtils.atoi("*");
		} catch (NumberFormatException e) {
			// This is expected.
			thrown = true;
		}

		assertTrue(thrown);
	}

	@Test
	public void testAtoi_MalformedNumberWithSign() {
		boolean thrown = false;

		try {
			common.NumberUtils.atoi("+12.3");
		} catch (NumberFormatException e) {
			// This is expected.
			thrown = true;
		}

		assertTrue(thrown);
	}

	@Test
	public void testAtoi_MalformedNumberWithoutSign() {
		boolean thrown = false;

		try {
			common.NumberUtils.atoi("12.3");
		} catch (NumberFormatException e) {
			// This is expected.
			thrown = true;
		}

		assertTrue(thrown);
	}

	@Test
	public void testAtoi_NumberWithPositiveSign() {
		assertEquals(123, common.NumberUtils.atoi("+123"));
	}

	@Test
	public void testAtoi_NumberWithNegativeSign() {
		assertEquals(-123, common.NumberUtils.atoi("-123"));
	}

	@Test
	public void testAtoi_NumberWithoutSign() {
		assertEquals(123, common.NumberUtils.atoi("123"));
	}

	@Test
	public void testAtoi_NumberOnlyWithSign() {
		boolean thrown = false;

		try {
			common.NumberUtils.atoi("+");
		} catch (NumberFormatException e) {
			// This is expected.
			thrown = true;
		}

		assertTrue(thrown);
	}
}
