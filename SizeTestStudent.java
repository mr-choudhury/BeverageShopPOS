import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */

public class SizeTestStudent {
	Size small, med, large;

	@BeforeEach
	public void setUp() throws Exception {
		small = Size.SMALL;
		med = Size.MEDIUM;
		large = Size.LARGE;
	}

	@AfterEach
	public void tearDown() throws Exception {
		small = med = large = null;
	}

	@Test
	public void test() {
		assertTrue(small.equals(Size.SMALL));
		assertTrue(med.equals(Size.MEDIUM));
		assertTrue(large.equals(Size.LARGE));
		assertFalse(med.equals(Size.LARGE));
	}

}
