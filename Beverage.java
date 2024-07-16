/**
 * Represents a Beverage Object
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */
public abstract class Beverage {
	
	private String name;
	private Size size;
	private Type type;
	private final double BASE_PRICE = 2.0;
	private final double SIZE_PRICE = 1.0;
	
	/**
	 * Creates a beverage object using given values.
	 * @param bevName - beverage name
	 * @param type - beverage type
	 * @param size - beverage size
	 */
	public Beverage (String bevName, Type type, Size size) {
		this.name = bevName;
		this.type = type;
		this.size = size;
	}
	
	/**
	 * Gets the base price
	 * @return the base price
	 */
	public double getBasePrice() {
		return this.BASE_PRICE;
	}
	
	/**
	 * Gets beverage type
	 * @return type of beverage
	 */
	public Type getType() {
		return this.type;
	}
	
	/**
	 * Gets the name of the beverage
	 * @return the beverage name
	 */
	public String getBevName() {
		return this.name;
	}
	
	/**
	 * Gets the size of the beverage
	 * @return size of the beverage
	 */
	public Size getSize() {
		return this.size;
	}
	
	/**
	 * Calculates a new price by adding the size price to the base price. There is no additional 
	 * cost for small size, for medium and large beverages the additional cost of size price is 
	 * added to base price For example if the base price is 2 and SIZE_PRICE is .5 then the cost 
	 * of small beverage is 2, the medium beverage is 2.5 and the large beverage is 3.
	 * @return A new price that by adding the size price to the base price
	 */
	public double addSizePrice() {
		double newPrice = this.getBasePrice();
		if (this.getSize().equals(Size.MEDIUM))
			newPrice += SIZE_PRICE;
		if (this.getSize().equals(Size.LARGE))
			newPrice += SIZE_PRICE*2;		
		return newPrice;
	}
	
	/**
	 * Represents a Beverage object in String with the format of bevName,size
	 * @return the String representation of beverage
	 */
	@Override
	public String toString() {
		return "The name of the beverage is "+this.getBevName()+
				" and the size is "+this.getSize()+".";
	}
	
	/**
	 * Checks if this Beverage equals to anotherBev
	 * @return true if the name, type, size , false otherwise
	 */
	@Override
	public boolean equals(Object anotherBev) {
		Beverage other = (Beverage) anotherBev;
		boolean status = false;
		if (this.getBevName().equals(other.getBevName()) && this.getType().equals(getType())
				&& this.getSize().equals(getSize()))
			status = true;
		return status;
	}
	
	/**
	 * Calculates the beverage price
	 */
	public abstract double calcPrice();
}
