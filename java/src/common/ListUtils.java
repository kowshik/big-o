package common;

import java.util.List;

public class ListUtils {
	public static <T> void swap(List<T> list, int fooIndex, int barIndex) {
		T tmp = list.get(fooIndex);
		list.set(fooIndex, list.get(barIndex));
		list.set(barIndex, tmp);
	}
}
