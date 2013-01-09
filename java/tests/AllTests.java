import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	sorting.MergeSortTest.class,
	sorting.StupidSortTest.class,
	collections.DeepIteratorTest.class
	    })
	
public class AllTests {
    public static void main(String[] args) {
	org.junit.runner.JUnitCore.main("AllTests");
    }
}
