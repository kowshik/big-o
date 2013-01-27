package general;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RainWaterTest {

	@Test
	public void findAccumulatedWater_NoWaterAccumulated() {
		assertEquals(0, RainWater.findAccumulatedWater(new int[] { 2, 1 }));
	}

	@Test
	public void findAccumulatedWater_SomeWaterAccumulated() {
		assertEquals(
				25,
				RainWater.findAccumulatedWater(new int[] { 2, 0, 2, 1, 3, 2, 6,
						0, 5, 2, 10, 5, 5, 0, 0, 2, 4 }));
	}
}
