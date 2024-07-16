import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */

class BevShopTestStudent {
	
	BevShop myShop = new BevShop();
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void testIsValidTime() {
		assertTrue(myShop.isValidTime(12));
		assertTrue(myShop.isValidTime(23));
		assertFalse(myShop.isValidTime(6));
		assertFalse(myShop.isValidTime(24));
	}

	@Test
	void testGetMaxNumOfFruits() {
		assertEquals(5, myShop.getMaxNumOfFruits());
	}

	@Test
	void testGetMinAgeForAlcohol() {
		assertEquals(21, myShop.getMinAgeForAlcohol());
	}

	@Test
	void testIsMaxFruit() {
		assertFalse(myShop.isMaxFruit(4));
		assertFalse(myShop.isMaxFruit(5));
		assertTrue(myShop.isMaxFruit(6));
	}

	@Test
	void testGetMaxOrderForAlcohol() {
		assertEquals(3, myShop.getMaxOrderForAlcohol());
	}

	@Test
	void testIsEligibleForMore() {
		myShop.startNewOrder(12, Day.MONDAY, "John", 24);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);

		assertFalse(myShop.isEligibleForMore());
		
		myShop.processAlcoholOrder("Sangria", Size.SMALL);

		assertTrue(myShop.isEligibleForMore());
	}

	@Test
	void testGetNumOfAlcoholDrink() {
		myShop.startNewOrder(12, Day.MONDAY, "John", 24);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);

		assertEquals(2, myShop.getNumOfAlcoholDrink());
		
		myShop.processAlcoholOrder("Sangria", Size.SMALL);

		assertEquals(3, myShop.getNumOfAlcoholDrink());
	}

	@Test
	void testIsValidAge() {
		assertFalse(myShop.isValidAge(20));
		assertFalse(myShop.isValidAge(10));
		assertTrue(myShop.isValidAge(21));
		assertTrue(myShop.isValidAge(45));
	}

	@Test
	void testProcessCoffeeOrder() {
		myShop.startNewOrder(12, Day.MONDAY, "John", 24);
		assertEquals(0, myShop.getCurrentOrder().getTotalItems());
		myShop.processCoffeeOrder("Latte", Size.SMALL, false, false);
		assertEquals(1, myShop.getCurrentOrder().getTotalItems());
		myShop.processCoffeeOrder("Espresso", Size.LARGE, true, false);
		assertEquals(2, myShop.getCurrentOrder().getTotalItems());
		
		
	}

	@Test
	void testProcessAlcoholOrder() {
		myShop.startNewOrder(12, Day.MONDAY, "John", 24);
		assertEquals(0, myShop.getCurrentOrder().getTotalItems());
		myShop.processAlcoholOrder("Beer", Size.SMALL);
		assertEquals(1, myShop.getCurrentOrder().getTotalItems());
		myShop.processAlcoholOrder("Sake", Size.LARGE);
		assertEquals(2, myShop.getCurrentOrder().getTotalItems());
	}

	@Test
	void testProcessSmoothieOrder() {
		myShop.startNewOrder(12, Day.MONDAY, "John", 24);
		assertEquals(0, myShop.getCurrentOrder().getTotalItems());
		myShop.processSmoothieOrder("Latte", Size.SMALL, 2, false);
		assertEquals(1, myShop.getCurrentOrder().getTotalItems());
		myShop.processSmoothieOrder("Espresso", Size.LARGE, 3, true);
		assertEquals(2, myShop.getCurrentOrder().getTotalItems());
	}

	@Test
	void testFindOrder() {
		myShop.startNewOrder(12, Day.MONDAY, "John", 24);
		assertEquals(-1, myShop.findOrder(13245));
	}

	@Test
	void testTotalOrderPrice() {
		myShop.startNewOrder(12, Day.MONDAY, "John", 24);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);
		
		myShop.startNewOrder(12, Day.TUESDAY, "Deer", 25);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.LARGE);
		int orderNo = myShop.getCurrentOrder().getOrderNo();
		
		myShop.startNewOrder(12, Day.MONDAY, "Mary", 56);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);
		
		assertEquals(8.0, myShop.totalOrderPrice(orderNo), 0.01);
	}

	@Test
	void testTotalMonthlySale() {
		myShop.startNewOrder(12, Day.MONDAY, "John", 24);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);
		assertEquals(7.0, myShop.totalMonthlySale(), 0.01);
		
		myShop.startNewOrder(12, Day.TUESDAY, "Deer", 25);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.LARGE);
		myShop.startNewOrder(12, Day.MONDAY, "Mary", 56);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);
		assertEquals(22.0, myShop.totalMonthlySale(), 0.01);
	}
	

	@Test
	void testTotalNumOfMonthlyOrders() {
		myShop.startNewOrder(12, Day.MONDAY, "John", 24);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);
		assertEquals(1, myShop.totalNumOfMonthlyOrders());
		
		myShop.startNewOrder(12, Day.TUESDAY, "Deer", 25);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.LARGE);
		myShop.startNewOrder(12, Day.MONDAY, "Mary", 56);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);
		assertEquals(3, myShop.totalNumOfMonthlyOrders());
	}

	@Test
	void testGetCurrentOrder() {
		myShop.startNewOrder(12, Day.MONDAY, "John", 24);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);
		myShop.startNewOrder(12, Day.TUESDAY, "Deer", 25);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.LARGE);
		myShop.startNewOrder(12, Day.MONDAY, "Mary", 56);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);
		
		Order test = myShop.getCurrentOrder();
		assertEquals(7.0, myShop.getCurrentOrder().calcOrderTotal(), 0.01);
		
		myShop.processSmoothieOrder("Espresso", Size.LARGE, 3, false);
		assertEquals(12.5, test.calcOrderTotal(), 0.01);
	}
	
	@Test
	void testSetCurrentOrder() {
		myShop.startNewOrder(12, Day.MONDAY, "John", 24);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);
		myShop.startNewOrder(12, Day.TUESDAY, "Deer", 25);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.LARGE);
		Order test = myShop.getCurrentOrder();
		
		myShop.startNewOrder(12, Day.MONDAY, "Mary", 56);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);

		assertEquals("Mary", myShop.getCurrentOrder().getCustomer().getName());
		
		myShop.setCurrentOrder(test);
		
		assertEquals("Deer", myShop.getCurrentOrder().getCustomer().getName());
	}
	
	

	@Test
	void testGetOrderAtIndex() {
		myShop.startNewOrder(12, Day.MONDAY, "John", 24);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);
		myShop.startNewOrder(12, Day.TUESDAY, "Deer", 25);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.LARGE);
		myShop.startNewOrder(12, Day.MONDAY, "Mary", 56);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);
		Order test = myShop.getOrderAtIndex(1);
		
		assertEquals(8.0, test.calcOrderTotal(), 0.01);
	}

	@Test
	void testSortOrders() {
		// Cannot test this method as the order number is randomly generated
		// However, it was tested in the BevShopDriverApp and GUI program
	}

	@Test
	void testToString() {
		myShop.startNewOrder(12, Day.MONDAY, "John", 24);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.MEDIUM);
		myShop.startNewOrder(12, Day.TUESDAY, "Deer", 25);
		myShop.processAlcoholOrder("Beer", Size.LARGE);
		myShop.processAlcoholOrder("Sake", Size.LARGE);
		
		assertTrue(myShop.toString().contains("Deer"));
		assertTrue(myShop.toString().contains("John"));
		assertTrue(myShop.toString().contains("Sake"));
		assertTrue(myShop.toString().contains("Beer"));
		assertTrue(myShop.toString().contains("MONDAY"));
		assertTrue(myShop.toString().contains("MEDIUM"));
		assertTrue(myShop.toString().contains("15.0"));
	}

}
