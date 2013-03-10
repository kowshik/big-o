import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ arrays.RearrangeInputToTargetTest.class,
		binarytree.BstClosestNodeTest.class, binarytree.BstFindModeTest.class,
		sorting.MergeSortTest.class, cache.LRUCacheTest.class,
		collections.DeepIteratorTest.class, collections.PeekIteratorTest.class,
		collections.lists.SinglyLinkedListTest.class,
		common.NumberUtilsTest.class, common.StringUtilsTest.class,
		bitsandbytes.NumSetBitsTest.class,
		arrays.PhoneNumberAllotterTest.class, general.RainWaterTest.class,
		sorting.HeapSortTest.class, sorting.QuickSortTest.class,
		sorting.StupidSortTest.class, sorting.InsertionSortTest.class,
		sorting.SimpleHeapImplTest.class, })
public class AllTests {
	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("AllTests");
	}
}
