import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ sorting.MergeSortTest.class, sorting.HeapSortTest.class,
		sorting.QuickSortTest.class, sorting.StupidSortTest.class,
		sorting.InsertionSortTest.class, sorting.SimpleHeapImplTest.class,
		collections.DeepIteratorTest.class, collections.PeekIteratorTest.class,
		collections.lists.SinglyLinkedListTest.class,
		arrays.RearrangeInputToTargetTest.class, common.NumberUtilsTest.class,
		common.StringUtilsTest.class, general.RainWaterTest.class })
public class AllTests {
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("AllTests");
	}
}
