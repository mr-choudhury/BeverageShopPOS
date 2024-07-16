import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */

class SmoothieTestStudent {
	Smoothie s1, s2, s3;

	@BeforeEach
	void setUp() throws Exception {
		s1 = new Smoothie("Banalata", Size.LARGE, 4, true);
		s2 = new Smoothie("Berrylicious", Size.MEDIUM, 2, false);
		s3 = new Smoothie("Banalata", Size.LARGE, 4, true);
	}

	@AfterEach
	void tearDown() throws Exception {
		s1 = s2 = s3 = null;
	}

	@Test
	void testToString() {
		assertTrue(s1.toString().contains("Banalata"));
		assertTrue(s2.toString().contains("MEDIUM"));
	}

	@Test
	void testEqualsObject() {
		assertTrue(s1.equals(s3));
		assertFalse(s1.equals(s2));
	}

	@Test
	void testCalcPrice() {
		assertEquals(7.5, s1.calcPrice(), .01);
		assertEquals(4.0, s2.calcPrice(), .01);
	}

	@Test
	void testGetNumOfFruits() {
		assertEquals(4, s1.getNumOfFruits());
		assertEquals(2, s2.getNumOfFruits());
	}

	@Test
	void testGetAddProtein() {
		assertTrue(s1.getAddProtein());
		assertFalse(s2.getAddProtein());
	}

}
