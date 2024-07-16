import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

public class myTest {

	public static char user=' ';
	public static char orderMenu=' ';
	public static int time=0, age=0, orderNo=0, orderNoFound=0;
	public static Day day = null;
	public static String name = "", strDay = "";
	public static Scanner input = new Scanner(System.in);
	public static BevShop myShop = new BevShop();
	Order one, two, three, four;
	
	public static void main(String[] args) {
		Order one, two, three, four;
		
		one = new Order(9, Day.MONDAY, new Customer("Mary", 34));
		two = new Order(13, Day.SATURDAY, new Customer("John", 45));
		three = new Order(20, Day.SUNDAY, new Customer("Kate", 65));
		
		Coffee lat = new Coffee("Latte", Size.SMALL, false, false);
		Alcohol san = new Alcohol("Sangria", Size.SMALL, false);
		Smoothie sm1 = new Smoothie("Banalata", Size.MEDIUM, 1, false);
		Smoothie sm2 = new Smoothie("Smash", Size.LARGE, 1, false);
		
		one.addNewBeverage("Latte", Size.SMALL, false, false);
		one.addNewBeverage("Sangria", Size.SMALL);
		one.addNewBeverage("Banalata", Size.MEDIUM, 1, false);
		
		
		System.out.println(one.getBeverage(0).getBevName());
		
		
//		assertTrue(one.getBeverage(0).equals(lat));
//		assertTrue(one.getBeverage(1).equals(san));
//		assertTrue(one.getBeverage(2).equals(sm1));
//		assertFalse(one.getBeverage(2).equals(sm2));
		
		
	}
}
