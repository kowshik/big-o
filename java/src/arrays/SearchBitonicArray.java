package arrays;

/**
 * Search in a bitonic array. An array is bitonic if it is comprised of an
 * increasing sequence of integers followed immediately by a decreasing sequence
 * of integers. Write a program that, given a bitonic array of N distinct
 * integer values, determines whether a given integer is in the array.
 *
 * Signature of expected method:
 * 
 *    public static int searchBitonicArray(int[] array, int value) {...}
 */
public class SearchBitonicArray {
	public static int searchBitonicArray(int[] array, int value) {
		if (array == null) {
			return -1;
		}

		return searchBitonicArray(array, value, 0, array.length - 1);
	}

	private static int searchBitonicArray(int[] array, int value,
			int startIndex, int endIndex) {
		if (startIndex > endIndex) {
			return -1;
		}

		int middleIndex = (startIndex + endIndex) / 2;
		if (value == array[middleIndex]) {
			return middleIndex;
		}

		if (array[middleIndex] < array[startIndex]) {
			//
			//  V /\ V
			// V /  \ V
			// S     \ V
			//        \ V
			//         \ M
			//          \
			//           \
			//            \
			//             E
			//
			// Here,
			// S => startIndex
			// M => middleIndex
			// E => endIndex
			// V => possible indices of searched value.
			//
			// In the diagram above, by definition of bitonic
			// array, when array[middleIndex] < array[startIndex],
			// then array[middleIndex] is always greater than array[endIndex].
			if (value > array[middleIndex]) {
				return searchBitonicArray(array, value, startIndex,
						middleIndex - 1);
			}
			//
			//   /\
			//  /  \
			// S    \
			//       \ M
			//        \ V
			//         \ V
			//          \ V
			//           \
			//             E
			return searchBitonicArray(array, value, middleIndex + 1, endIndex);
		}

		// If value > array[middleIndex], then the value could exist
		// to the right or the left of middleIndex, so we have to
		// search on both sides of middleIndex.
		//
		//   V /\ V
		//    /  \ M
		//   /    \
		//  /      \
		// S        \
		//           \
		//            \
		//             \
		//              E
		//
		//     V /\ V
		//    M /  \
		//     /    \
		//    /      \
		//   /        \
		//  /          E
		// /
		// S
		//
		// Similarly, if value < array[middleIndex], then the value could exist
		// to the right or the left of middleIndex, so we have to
		// search on both sides of middleIndex.
		//
		//     /\
		//    /  \ M
		//   /    \ V
		//  /      \ V
		// S        \ V
		//           \ V
		//            \ V
		//             \
		//               E
		//
		//         /\
		//      M /  \
		//     V /    \
		//    V /      \
		//   V /        \
		//  V /          E
		//   /
		// S
		//
		int found = searchBitonicArray(array, value, startIndex,
				middleIndex - 1);
		if (found == -1) {
			found = searchBitonicArray(array, value, middleIndex + 1, endIndex);
		}

		return found;
	}
}
