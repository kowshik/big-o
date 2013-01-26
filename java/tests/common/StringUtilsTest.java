package common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringUtilsTest {
	@Test
	public void testItoa_NegativeValue() {
		assertEquals("-25", common.StringUtils.itoa(-25));
	}

	@Test
	public void testItoa_PositiveValue() {
		assertEquals("25", common.StringUtils.itoa(25));
	}
}
