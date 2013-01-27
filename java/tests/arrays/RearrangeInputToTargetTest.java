package arrays;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class RearrangeInputToTargetTest {

	@Test
	public void rearrange_inputIsSameAsOutput() {
		int[] target = { 5, 0, 3, 2, 9 };
		int[] input = { 5, 0, 3, 2, 9 };

		RearrangeInputToTarget.rearrange(input, target);
		assertArrayEquals(input, target);
	}

	@Test
	public void rearrange_inputIsDifferentFromOutput() {
		int[] target = { 5, 0, 3, 2, 9 };
		int[] input = { 0, 2, 5, 9, 3 };

		RearrangeInputToTarget.rearrange(input, target);
		assertArrayEquals(input, target);
	}

	@Test
	public void rearrange_inputIsDifferentFromOutputAndZeroGetsLocked() {
		int[] target = { 0, 5, 3, 2, 9 };
		int[] input = { 0, 2, 5, 9, 3 };

		RearrangeInputToTarget.rearrange(input, target);
		assertArrayEquals(input, target);
	}
}
