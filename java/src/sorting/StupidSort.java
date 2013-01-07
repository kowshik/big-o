package sorting;
   
import java.util.Comparator;

public class StupidSort {
    public static void swap(int[] array, int foo, int bar) {
	int temp = array[foo];
	array[foo] = array[bar];
	array[bar] = temp;
    }

    public static void stupidSort(int[] array, Comparator<Integer> comparator) {
	for (int i = 0; i < array.length; i++) {
	    for (int j = i + 1; j < array.length; j++) {
		if (comparator.compare(array[i], array[j]) > 0) {
		    swap(array, i, j);
		}
	    } 
	}
    }    
}