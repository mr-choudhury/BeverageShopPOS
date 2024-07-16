import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */

class TypeTestStudent {
	Type c,s,a;

	@BeforeEach
	void setUp() throws Exception {
		s = Type.SMOOTHIE;
		c = Type.COFFEE;
		a = Type.ALCOHOL;
	}

	@AfterEach
	void tearDown() throws Exception {
		s = c = a = null;
	}

	@Test
	void test() {
		assertTrue(s.equals(Type.SMOOTHIE));
		assertTrue(c.equals(Type.COFFEE));
		assertTrue(a.equals(Type.ALCOHOL));
		assertFalse(a.equals(Type.COFFEE));
	}

}
