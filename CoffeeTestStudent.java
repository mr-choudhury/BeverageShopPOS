import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */

class CoffeeTestStudent {
	Coffee c1, c2, c3;

	@BeforeEach
	void setUp() throws Exception {
		c1 = new Coffee("Latte", Size.MEDIUM, true, false);
		c2 = new Coffee("Espresso", Size.LARGE, false, true);
		c3 = new Coffee("Latte", Size.MEDIUM, true, false);
	}

	@AfterEach
	void tearDown() throws Exception {
		c1 = c2 = c3 = null;
	}

	@Test
	void testToString() {
		assertTrue(c1.toString().contains("Latte"));
		assertTrue(c2.toString().contains("LARGE"));
	}

	@Test
	void testEqualsObject() {
		assertTrue(c1.equals(c3));
		assertFalse(c1.equals(c2));
	}

	@Test
	void testCalcPrice() {
		assertEquals(3.5, c1.calcPrice(), .01);
		assertEquals(4.5, c2.calcPrice(), .01);
	}

	@Test
	void testGetExtraShot() {
		assertTrue(c1.getExtraShot());
		assertFalse(c2.getExtraShot());
	}

	@Test
	void testGetExtraSyrup() {
		assertFalse(c1.getExtraSyrup());
		assertTrue(c2.getExtraSyrup());
	}

}
