/**
 * Represents Alcohol Beverage
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */
public class Alcohol extends Beverage{
	
	private boolean weekend;
	private final double WEEKEND_EXTRA = 0.60;
	
	/**
	 * Creates an Alcohol object using given values
	 * @param bevName beverage name
	 * @param size beverage size
	 * @param isWeekend true if the beverage is offered in the weekend false otherwise
	 */
	public Alcohol (String bevName, Size size, boolean isWeekend) {
		super(bevName, Type.ALCOHOL, size);
		this.weekend = isWeekend;
	}
	
	/**
	 * Calculates the price of the alcohol
	 * @return the price of an alcohol beverage
	 */
	@Override
	public double calcPrice() {
		double price = this.addSizePrice();
		if (this.isWeekend())
			price += WEEKEND_EXTRA;
		return price;
	}
	
	/**
	 * Checks if is weekend.
	 * @return true, if is weekend
	 */
	public boolean isWeekend() {
		return this.weekend;
	}
	
	/**
	 * String representation of Alcohol beverage, includes the name, size, whether or not beverage is 
	 * offered in weekend and the price
	 * @return a string containing the information of an alcohol beverage
	 */
	public String toString() {
		String str;
		str = String.format("%10s", "Alcohol:  ")+"\t"+String.format("%10s", this.getBevName())+", \tSize: "+
				String.format("%10s",this.getSize())+",\t";
		str += " W/E availability: ";
		if (this.isWeekend())
			str +="YES,\t\t\t\t";
		else
			str +="NO, \t\t\t\t";
		str += "\tPrice: $ " + String.format("%.2f", this.calcPrice());
			
		return str;
	}
	
	/**
	 * Checks if this Beverage equals to anotherBev
	 * @return true if the name, type, size and base price and whether beverage is offered in weekend or not 
	 * are the same, false otherwise
	 * 
	 */
	@Override
	public boolean equals(Object anotherBev) {
		Alcohol other = (Alcohol) anotherBev;
		boolean status = false;
		if (this.getBevName().equals(other.getBevName()) && this.getType().equals(other.getType()) &&
				this.getSize().equals(other.getSize()) && this.getBasePrice()==other.getBasePrice()
				&& Boolean.compare(this.isWeekend(), other.isWeekend())==0  )
			status = true;
		return status;
	}

}
