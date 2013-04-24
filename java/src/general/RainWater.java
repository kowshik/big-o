package general;

/**
 * You have a two-dimensional view of a terrain of uneven land with sharp ups
 * and downs (always perpendicular changes in terrain structure). The terrain
 * can be represented using 'N' non-negative integers given to you as an array
 * of integers, with each integer representing the height of the terrain at that
 * point in the sequence. Lets assume that each piece of the terrain rises from
 * a common base situated at the same height (ground 0). For example, the array:
 * {5,1,3,2,6} can be imagined as a bar graph diagram on an X-Y plane with
 * X-axis representing the base of the terrain and Y-axis representing the
 * height of each piece of the terrain.
 *
 * Now there is a sudden downpour of rain over this uneven terrain, and water
 * gets accumulated in the pits formed due to the ups and downs. Given the
 * terrain, your task is to write a function that can find the accumulated rain
 * water.
 *
 * Here is a picture showing the above case: http://i.imgur.com/3pFFU.png
 *
 * Examples:
 *
 * [1]
 *
 * Array: {5,1}. Answer: 0 units of water. Reason: There are no pits :-(
 *
 * [2]
 *
 * Array: {0,2,1,2}. Answer: 1 unit of water. Reason: 1 unit of water
 * accumulated between 2nd and 4th pieces of the terrain
 *
 * @author Kowshik Prakasam (kowshik@gmail.com)
 */
public class RainWater {

	/**
	 * Calculates rain water accumulated on the terrain between various peaks in
	 * the terrain.
	 *
	 * @param terrain
	 *            terrain described by an array of positive integers, each
	 *            representing the height at a particular point.
	 * @return amount of water accumulated on the given terrain.
	 */
	public static long findAccumulatedWater(int[] terrain) {
		if (terrain == null) {
			return 0;
		}
		int maxIndex = 0;
		long totalWater = 0;
		long tempWater = 0;

		for (int index = maxIndex + 1; index < terrain.length; index++) {
			if (terrain[index] >= terrain[maxIndex]) {
				totalWater += tempWater;
				tempWater = 0; // flushing temp water stored
				maxIndex = index;
			} else {
				tempWater += terrain[maxIndex] - terrain[index];
			}
		}

		tempWater = 0;
		int reverseMaxIndex = terrain.length - 1;
		for (int index = reverseMaxIndex - 1; index >= maxIndex; index--) {
			if (terrain[index] >= terrain[reverseMaxIndex]) {
				totalWater += tempWater;
				tempWater = 0; // flushing temp water stored
				reverseMaxIndex = index;
			} else {
				tempWater += terrain[reverseMaxIndex] - terrain[index];
			}
		}

		return totalWater;
	}
}
