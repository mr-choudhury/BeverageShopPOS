import java.util.ArrayList;
import java.util.Random;
/**
 * Class to represent an order. This class implements two interfaces: OrderInterface and Comparable.  
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */
public class Order implements OrderInterface, Comparable<Order> {
	
	private int number;
	private int time;
	private Day day;
	private Customer customer;
	private ArrayList<Beverage> beverages = new ArrayList<Beverage>();

	/**
	 * Constructor to create an instance of Order
	 * @param orderTime time of the order (between 8 and 23 )
	 * @param orderDay day of the order
	 * @param cust a customer object
	 */
	public Order (int orderTime, Day orderDay, Customer cust) {
		this.time = orderTime;
		this.day = orderDay;
		this.customer = new Customer(cust);
		this.number = generateOrder();
	}
	
	/**
	 * Automatically generate order number
	 * @return a random order number between 10000 and 90000
	 */
	public int generateOrder() {
		int number;
		Random rand = new Random();
		number = rand.nextInt(80001)+10000;
		return number;
	}
	
	/**
	 * returns the order number
	 * @return order number
	 */
	public int getOrderNo() {
		return this.number;
	}
	
	/**
	 * returns the order time
	 * @return order time
	 */
	public int getOrderTime() {
		return this.time;
	}
	
	/**
	 * returns the order day
	 * @return order day
	 */
	public Day getOrderDay() {
		return this.day;
	}
	
	/**
	 * returns the customer for this order
	 * @return the customer
	 */
	public Customer getCustomer() {
		Customer cust = new Customer(this.customer);
		return cust;
	}
	
	/**
	 * return order day (MONDAY, TUESDAY.....,SUNDAY)
	 * @return order day
	 */
	public Day getDay() {
		return this.day;
	}
	
	/**
	 * public boolean isWeekend()
	 * isWeekend in interface OrderInterface
	 * @return true if the day is a weekend day (Saturday or Sunday)
	 */
	@Override
	public boolean isWeekend() {
		boolean status = false;
		if (this.day.equals(Day.SATURDAY)  || this.day.equals(Day.SUNDAY))
			status = true;
		return status;
	}
	
	/**
	 * returns the beverage listed in the itemNo of the order, for example if itemNo is 0 this method 
	 * will return the first beverage in this order Note: this method returns the shallow copy of the Beverage
	 * @return the beverage listed in the itemNo of the order or null if there is no item in the order
	 */
	@Override
	public Beverage getBeverage(int itemNo) {
		Beverage bev;
		bev = this.beverages.get(itemNo);
		return bev;
	}
	
	/**
	 * returns the total number of beverages ordered within this order
	 * @return total number of beverages ordered within this order
	 */
	public int getTotalItems() {
		return this.beverages.size();
	}
	
	/**
	 * adds coffee order to this order
	 * @param bevName beverage name
	 * @param size beverage size of type SIZE
	 * @param extraShot true if the coffee beverage has extra shot , false otherwise
	 * @param extraSyrup true if the coffee beverage has extra syrup , false otherwise
	 */
	@Override
	public void addNewBeverage(String bevName, Size size, boolean extraShot, boolean extraSyrup) {
		Coffee coffee = new Coffee(bevName, size, extraShot, extraSyrup);
		this.beverages.add(coffee);
	}
	
	/**
	 * adds alcohol order to this order
	 * @param bevName beverage name
	 * @param size beverage size
	 */ 
	@Override
	public void addNewBeverage(String bevName, Size size) {
		Alcohol alcohol = new Alcohol(bevName, size, isWeekend());
		this.beverages.add(alcohol);
	}

	/**
	 * Adds the Smoothie beverage to this order
	 * @param bevName beverage name
	 * @param size beverage size
	 * @param numOfFruits number of fruits added 
	 * @param addProtein true if protein is added, false otherwise
	 */
	@Override
	public void addNewBeverage(String bevName, Size size, int numOfFruits, boolean addProtein) {
		Smoothie smoothie = new Smoothie(bevName, size, numOfFruits, addProtein);
		this.beverages.add(smoothie);
	}

	/**
	 * Calculates and returns the total amount for this order
	 * @return total amount for this order
	 */
	@Override
	public double calcOrderTotal() {
		double total = 0.0;
		
		for (int i=0; i<this.getTotalItems(); i++) {
			total += this.beverages.get(i).calcPrice();
		}
		return total;
	}

	/**
	 * returns the number of beverages of same type in an order
	 * @return number of beverages of passed type in this order
	 */
	@Override
	public int findNumOfBeveType(Type type) {
		int num = 0;

		for (int i=0; i<this.getTotalItems(); i++) {
			if (this.beverages.get(i).getType().equals(type))
				num ++;
		}
		return num;
	}

	/**
	 * String representation of the order, Includes order number, time , day, 
	 * customer name and age and the list of beverages
	 */
	@Override
	public String toString() {
		String str;
		str = "Order number: " + this.getOrderNo() + "\n";
		str += "Time: " + String.format("%2d", this.getOrderTime()) + "\t\t";
		str += "Day: " + String.format("%10s", this.getDay()) + "\t\t";
		str += "Name: " + String.format("%20s", this.getCustomer().getName()) + "\t\t";
		str += "Age: " + this.getCustomer().getAge()+"\n\n";
		for (int i=0; i<this.getTotalItems(); i++) {
			str += String.format("%2d. ", i+1) + this.beverages.get(i) + "\n";
		}
		str += "\nThe total for this order is: $ " + String.format("%.2f", this.calcOrderTotal()) + "\n";
		str += "===========================================================================\n";
		return str;
	}
	
	/**
	 * compare this order with another order based on the order number. 
	 * @return Returns 0 if this order number is same as another order's order number, 
	 * 1 if it is greater than another order's order number, -1 if it smaller than another order's order number.
	 */
	@Override
	public int compareTo(Order o) {
		if (this.getOrderNo() == o.getOrderNo())
			return 0;
		if (this.getOrderNo() < o.getOrderNo())
			return -1;
		else
			return 1;
	}
}
