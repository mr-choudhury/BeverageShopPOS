import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */

class CustomerTestStudent {
	Customer c1, c2, c3, c4;

	@BeforeEach
	void setUp() throws Exception {
		c1 = new Customer("John", 23);
		c2 = new Customer("Mary", 30);
		c3 = new Customer("John", 23);

	}

	@AfterEach
	void tearDown() throws Exception {
		c1 = c2 = c3 = c4 = null;
	}

	@Test
	void testCustomer() {
		c4 = new Customer(c2);
		assertEquals("Mary", c4.getName());
	}

	@Test
	void testGetAge() {
		assertEquals(23, c1.getAge());
		assertEquals(30, c2.getAge());
	}

	@Test
	void testSetAge() {
		c1.setAge(5);
		assertEquals(5, c1.getAge());
	}

	@Test
	void testGetName() {
		assertEquals("Mary", c2.getName());
	}

	@Test
	void testSetName() {
		c2.setName("Jane");
		assertEquals("Jane", c2.getName());
	}

	@Test
	void testToString() {
		assertTrue(c1.toString().contains("John"));
		assertTrue(c2.toString().contains("30"));
	}

}
