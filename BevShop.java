import java.util.ArrayList;

/**
 * Class to represent a beverage shop. This class implements BevShopInterface 
 * One extra method has been added to BevShop class called setCurrentOrder
 * which improves the functionality of the app.  Now you can select and 
 * choose which Order should be edited, especially helpful after sorting the list
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */
public class BevShop implements BevShopInterface {
	
	/** ArrayList to hold orders for BevShop */
	private ArrayList<Order> orders;
	private Order currentOrder;
	
	/**
	 * default Constructor Initializes a BevShop Object
	 */
	public BevShop() {
		 orders = new ArrayList<Order>(); 			// create a new orders list
	}
	
	
	/**
	 * Checks if the time is valid (between 8 and 23 )
	 * @param time represents the time
	 * @return true if times is within the range of 8 to 23 , false otherwise
	 */
	@Override
	public boolean isValidTime(int time) {
		if (time >= 8 && time <= 23)
			return true;
		return false;
	}

	/**
	 * returns the constant value for the maximum number of fruits for smoothies
	 * @return returns the value for the maximum number of fruits for smoothies
	 */
	@Override
	public int getMaxNumOfFruits() {
		return MAX_FRUIT;
	}

	/**
	 * returns the constant value for the Maximum age for offering Alcohol drink
	 * @return returns the value for the minimum age for offering Alcohol drink
	 */
	@Override
	public int getMinAgeForAlcohol() {
		return MIN_AGE_FOR_ALCOHOL;
	}

	/**
	 * returns true if the passed parameter exceeds the Maximum FRUIT allowed
	 * @return true if the passed parameter exceeds the MAXIMUM number of fruits allowed 
	 * for the SMOOTHIE drink, false otherwise
	 */
	@Override
	public boolean isMaxFruit(int numOfFruits) {
		if (numOfFruits > getMaxNumOfFruits())
			return true;
		return false;
	}

	/**
	 * returns constant maximum number of alcohol beverages/per order offered by the beverage shop
	 * @return constant maximum number of alcohol beverages/per order offered by the beverage shop
	 */
	@Override
	public int getMaxOrderForAlcohol() {
		return MAX_ORDER_FOR_ALCOHOL;
	}
	
	/**
	 * checks if the number of alcohol beverages for the current order has reached the maximum
	 * @return true if number of alcohol drinks for the current order has reached the maximum, 
	 * false otherwise
	 */
	@Override
	public boolean isEligibleForMore() {
		if (this.getNumOfAlcoholDrink() == this.getMaxOrderForAlcohol())
			return true;
		else
			return false;
	}
	
	/**
	 * returns the number of alcohol drinks for the current order
	 * @return returns the number of alcohol drinks for the current order
	 */
	@Override
	public int getNumOfAlcoholDrink() {
		return this.currentOrder.findNumOfBeveType(Type.ALCOHOL);
	}
	
	/**
	 * check the valid age for the alcohol drink
	 * @return returns true if age is more than minimum eligible age , false otherwise
	 */
	@Override
	public boolean isValidAge(int age) {
		if (age >= this.getMinAgeForAlcohol())
			return true;
		return false;
	}

	/**
	 * Creates a new order , NO BEVERAGE is added to the order yet
	 * @param time time of the order
	 * @param day day of the order of type DAY
	 * @param customerName customer name
	 * @param customerAge customer age
	 */
	@Override
	public void startNewOrder(int time, Day day, String customerName, int customerAge) {
		Customer cust = new Customer(customerName, customerAge);
		this.currentOrder = new Order(time, day, cust);
		this.orders.add(this.currentOrder);
	}

	/**
	 * process the Coffee order for the current order by adding it to the current order
	 * @param bevName beverage name
	 * @param size beverage size
	 * @param extraShot true if the coffee beverage has extra shot , false otherwise
	 * @param extraSyrup true if the coffee beverage has extra syrup , false otherwise
	 */
	@Override
	public void processCoffeeOrder(String bevName, Size size, boolean extraShot, boolean extraSyrup) {
		this.currentOrder.addNewBeverage(bevName, size, extraShot, extraSyrup);
	}

	/**
	 * process the Alcohol order for the current order by adding it to the current order
	 * @param bevName beverage name
	 * @param size beverage size
	 */
	@Override
	public void processAlcoholOrder(String bevName, Size size) {
		if (isValidAge(this.currentOrder.getCustomer().getAge())) {
			if (!this.isEligibleForMore()) {
				this.currentOrder.addNewBeverage(bevName, size);
			}
			else
				System.out.println("Maximum number of alcohol drinks per order reached!");
		}
		else
			System.out.println("Age not appropriate for alcohol drink!");
	}

	/**
	 * process the Smoothie order for the current order by adding it to the current order
	 * @param bevName beverage name
	 * @param size beverage size
	 * @param numOfFruits number of fruits to be added
	 * @param addProtein true if protein is added , false otherwise
	 */
	@Override
	public void processSmoothieOrder(String bevName, Size size, int numOfFruits, boolean addProtein) {
		if (!this.isMaxFruit(numOfFruits))
			this.currentOrder.addNewBeverage(bevName, size, numOfFruits, addProtein);
		else
			System.out.println("Exceeded Maximum Number of Fruits Allowed!");
	}

	/**
	 * locate an order based on the order number
	 * @param orderNo - the order number
	 * @return the index of the order in the list of Orders if found or -1 if not found
	 */
	@Override
	public int findOrder(int orderNo) {
		for (int i=0; i<this.orders.size(); i++) {
			if (this.orders.get(i).getOrderNo() == orderNo)
				return i;
		}
		return -1;
	}

	/**
	 * locates an order in the list of orders and returns the total value on the order.
	 * @param orderNo the order number
	 * @return the calculated price on this order.
	 */
	@Override
	public double totalOrderPrice(int orderNo) {
		int index = this.findOrder(orderNo);
		return this.orders.get(index).calcOrderTotal();
	}

	/**
	 * Calculates the total sale of all the orders for this beverage shop
	 * @return the total sale of all the orders
	 */
	@Override
	public double totalMonthlySale() {
		double total = 0.0;
		for (int i=0; i<this.orders.size(); i++) {
			total += this.orders.get(i).calcOrderTotal();
		}
		return total;
	}

	/**
	 * returns total numbers of orders within the month
	 * @return total numbers of orders within the month
	 */
	@Override
	public int totalNumOfMonthlyOrders() {
		return this.orders.size();
	}

	/**
	 * returns the current Order located in the index in the list of orders. 
	 * Notes: this method returns the shallow copy of the order
	 * @return the current order
	 */
	@Override
	public Order getCurrentOrder() {
		Order order = this.currentOrder;
		return order;
	}
	
	/**
	 * This sets the current Order to point to the an existing order.
	 * You must first locate an existing order by implementing existing methods  
	 * Hint: First find the order index by using findOrder then use that value with
	 * method getOrderAtIndex and point the current Order to it
	 * @param order shallow copy of an order to set current order to
	 */
	public void setCurrentOrder(Order order) {
		this.currentOrder = order;
	}

	/**
	 * returns Order in the list of orders at the index Notes: this method returns the 
	 * shallow copy of the order
	 * @param index the order index
	 * @return Order in the list of orders at the index
	 */
	@Override
	public Order getOrderAtIndex(int index) {
		Order order = this.orders.get(index);
		return order;
	}

	/**
	 * sorts the orders within this bevShop using the Selection sort algorithm
	 */
	@Override
	public void sortOrders() {
		Order temp;
		int min;
		for (int index=0; index<this.orders.size()-1; index++) {
			min = index;
			for (int scan=index+1; scan<this.orders.size(); scan++) {
				if (this.orders.get(scan).compareTo(this.orders.get(min))<0)
					min = scan;
			}
			temp = this.orders.get(min);
			this.orders.set(min, this.orders.get(index));
			this.orders.set(index, temp);
		}
	}

	/**
	 * returns the string representation of all the orders and the total monthly sale
	 * @return returns the information of all the orders
	 */
	public String toString() {
		String str;
		str = "\n                           ***** Full Report for Beverage Shop *****\n";
		str += "===========================================================================\n";
		for (int i=0; i<this.totalNumOfMonthlyOrders(); i++) {
			str += this.orders.get(i);
		}
		str += "\n******* TOTAL NUMBER OF MONTHLY ORDERS: " + this.totalNumOfMonthlyOrders();
		str += "\n\n******* TOTAL MONTHLY SALES: $ " + String.format("%.2f", this.totalMonthlySale());
		str += "\n\n===========================================================================\n";
		return str;
	}
}
