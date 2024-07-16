import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */

class AlcoholTestStudent {
	Alcohol a1, a2, a3;

	@BeforeEach
	void setUp() throws Exception {
		a1 = new Alcohol("Beer", Size.LARGE, true);
		a2 = new Alcohol("Sangria", Size.MEDIUM, false);
		a3 = new Alcohol("Beer", Size.LARGE, true);
	}

	@AfterEach
	void tearDown() throws Exception {
		a1 = a2 = a3 = null;
	}

	@Test
	void testToString() {
		assertTrue(a1.toString().contains("Beer"));
		assertTrue(a2.toString().contains("MEDIUM"));
	}

	@Test
	void testEqualsObject() {
		assertTrue(a1.equals(a3));
		assertFalse(a1.equals(a2));
	}

	@Test
	void testCalcPrice() {
		assertEquals(4.6, a1.calcPrice(), .01);
		assertEquals(3.0, a2.calcPrice(), .01);
	}

	@Test
	void testIsWeekend() {
		assertTrue(a1.isWeekend());
		assertFalse(a2.isWeekend());
	}

}
