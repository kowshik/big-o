package sorting;
   
import java.util.Comparator;

public class StupidSort {
    public static void main(String[] args) {
	Comparator<Integer> ascending = new Comparator<Integer>() {
	    @Override
	    public int compare(Integer foo, Integer bar) {
		return foo - bar;
	    }
	};
	
	int[] array = {5, 4, 3, 2, 1};
	stupidSort(array, ascending);
	for (int x : array) {
	    System.out.println(x);
	}
    }
    
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