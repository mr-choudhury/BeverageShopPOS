import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */
class OrderTestStudent {
	Order one, two, three, four;

	@BeforeEach
	void setUp() throws Exception {
		one = new Order(9, Day.MONDAY, new Customer("Mary", 34));
		two = new Order(13, Day.SATURDAY, new Customer("John", 45));
		three = new Order(20, Day.SUNDAY, new Customer("Kate", 65));
	}

	@AfterEach
	void tearDown() throws Exception {
		one = two = three = four = null;
	}

	@Test
	void testGenerateOrder() {
		assertTrue(one.generateOrder()>=10000);
		assertTrue(one.generateOrder()<=90000);
	}

	@Test
	void testGetOrderNo() {
		assertTrue(two.getOrderNo()>=10000);
		assertTrue(two.getOrderNo()<=90000);
	}

	@Test
	void testGetOrderTime() {
		assertEquals(9, one.getOrderTime());
		assertEquals(20, three.getOrderTime());
	}

	@Test
	void testGetOrderDay() {
		assertEquals(Day.SATURDAY, two.getOrderDay());
		assertEquals(Day.SUNDAY, three.getOrderDay());
	}

	@Test
	void testGetCustomer() {
		four = new Order(19, Day.FRIDAY, three.getCustomer());
		assertEquals("Kate", four.getCustomer().getName());
	}

	@Test
	void testGetDay() {
		assertEquals(Day.SATURDAY, two.getDay());
		assertEquals(Day.MONDAY, one.getDay());
	}

	@Test
	void testIsWeekend() {
		assertFalse(one.isWeekend());
		assertTrue(two.isWeekend());
	}

	@Test
	void testGetBeverage() {
		Coffee lat = new Coffee("Latte", Size.SMALL, false, false);
		Alcohol san = new Alcohol("Sangria", Size.SMALL, false);
		Smoothie sm1 = new Smoothie("Banalata", Size.MEDIUM, 1, false);
		Smoothie sm2 = new Smoothie("Smash", Size.LARGE, 1, false);
		
		one.addNewBeverage("Latte", Size.SMALL, false, false);
		one.addNewBeverage("Sangria", Size.SMALL);
		one.addNewBeverage("Banalata", Size.MEDIUM, 1, false);
		assertTrue(one.getBeverage(0).equals(lat));
		assertTrue(one.getBeverage(1).equals(san));
		assertTrue(one.getBeverage(2).equals(sm1));
		assertFalse(one.getBeverage(2).equals(sm2));
	}

	@Test
	void testGetTotalItems() {
		one.addNewBeverage("Latte", Size.SMALL, false, false);
		one.addNewBeverage("Sangria", Size.SMALL);
		one.addNewBeverage("Banalata", Size.MEDIUM, 1, false);
		assertEquals(3, one.getTotalItems());
	}

	@Test
	void testAddNewBeverageStringSizeBooleanBoolean() {
		one.addNewBeverage("Latte", Size.SMALL, false, false);
		assertEquals("Latte", one.getBeverage(0).getBevName());
	}

	@Test
	void testAddNewBeverageStringSize() {
		one.addNewBeverage("Sangria", Size.SMALL);
		assertEquals("Sangria", one.getBeverage(0).getBevName());
	}

	@Test
	void testAddNewBeverageStringSizeIntBoolean() {
		one.addNewBeverage("Banalata", Size.MEDIUM, 1, false);
		assertEquals("Banalata", one.getBeverage(0).getBevName());
	}

	@Test
	void testCalcOrderTotal() {
		one.addNewBeverage("Latte", Size.SMALL, false, false);
		one.addNewBeverage("Sangria", Size.SMALL);
		one.addNewBeverage("Banalata", Size.MEDIUM, 1, false);
		assertEquals(7.5, one.calcOrderTotal(), .01);
	}

	@Test
	void testFindNumOfBeveType() {
		one.addNewBeverage("Sangria", Size.SMALL);
		one.addNewBeverage("Sangria", Size.MEDIUM);
		one.addNewBeverage("Sangria", Size.LARGE);
		assertEquals(3, one.findNumOfBeveType(Type.ALCOHOL));
	}

	@Test
	void testToString() {
		one.addNewBeverage("Latte", Size.SMALL, false, false);
		one.addNewBeverage("Sangria", Size.SMALL);
		one.addNewBeverage("Banalata", Size.MEDIUM, 1, false);
		assertTrue(one.toString().contains("Latte"));
		assertTrue(one.toString().contains("Banalata"));
	}

	@Test
	void testCompareTo() {
		// Cannot test this method as the order number is randomly generated
		// However, it was tested in the BevShopDriverApp and GUI programs
	}

}
