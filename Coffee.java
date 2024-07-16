/**
 * Represent a Coffee beverage
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */
public class Coffee extends Beverage{
	
	private boolean shot, syrup;
	private final double EXTRA_SHOT_PRICE = 0.50;
	private final double EXTRA_SYRUP_PRICE = 0.50;
	
	/**
	 * Creates a Coffee object using the given values
	 * @param bevName beverage name
	 * @param size beverage size
	 * @param extraShot true if extra coffee shot added , false otherwise
	 * @param extraSyrup true if extra syrup is added , false otherwise
	 */
	public Coffee (String bevName, Size size, boolean extraShot, boolean extraSyrup) {
		super(bevName, Type.COFFEE, size);
		this.shot = extraShot;
		this.syrup = extraSyrup;
	}

	/**
	 * Indicates whether or not extra shot is added
	 * @return extra shot
	 */
	public boolean getExtraShot() {
		return this.shot;
	}
	
	/**
	 * Indicates whether or not extra syrup is added
	 * @return extra syrup
	 */
	public boolean getExtraSyrup() {
		return this.syrup;
	}
	
	/**
	 * Calculates the price based on base price, size, extra coffee shot and extra syrup
	 * @return the price of the coffee
	 */
	@Override
	public double calcPrice() {
		double price = this.addSizePrice();
		if (this.getExtraShot())
			price += EXTRA_SHOT_PRICE;
		if (this.getExtraSyrup())
			price += EXTRA_SYRUP_PRICE;
		return price;
	}
	
	/**
	 * Represents a Coffee beverage in the following String format: name,size, whether it contains extra shot, 
	 * extra syrup and the price
	 * @return String representation of a Coffee
	 */
	@Override
	public String toString() {
		String str;
		str = String.format("%10s", "Coffee:   ") + "\t"+String.format("%10s", this.getBevName()) + ", \tSize: " + 
				String.format("%10s",this.getSize()) + ",\t";
		if (this.getExtraShot())
			str += " with Extra Shot,   \t";
		else
			str += " with no Extra Shot,\t";
		if (this.getExtraSyrup())
			str += " and Extra Syrup,   \t\t";
		else
			str += " and no Extra Syrup,\t";
		str += "Price: $ " + String.format("%.2f", this.calcPrice());
		return str;
	}
	
	/**
	 * Checks if this Beverage equals to anotherBev
	 * @return true if the name, type, size and base price and whether or not it contains extra shot and 
	 * extra syrup false otherwise
	 * 
	 */
	@Override
	public boolean equals(Object anotherBev) {
		Coffee other = (Coffee) anotherBev;
		boolean status = false;
		if (this.getBevName().equals(other.getBevName()) && this.getType().equals(other.getType()) &&
				this.getSize().equals(other.getSize())  && this.getBasePrice()==other.getBasePrice() &&
				Boolean.compare(this.getExtraShot(), other.getExtraShot())==0  &&
				Boolean.compare(this.getExtraSyrup(), other.getExtraSyrup())==0)
			status = true;
		return status;
	}
}
