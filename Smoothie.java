/**
 * Represents Smoothie Beverage
 *
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */
public class Smoothie extends Beverage{
	
	private int fruits = 0;
	private boolean protein;
	private final double ADD_PROTEIN = 1.50;
	private final double FRUIT_COST = 0.50;
	
	/**
	 * Constructor to create a smoothie object
	 * @param bevName Name of the beverage
	 * @param size Size of the beverage
	 * @param numOfFruits Number of fruits to be added to the smoothie
	 * @param addProtein Whether to add protein to the smoothie or not
	 */
	public Smoothie(String bevName, Size size, int numOfFruits, boolean addProtein) {
		super(bevName, Type.SMOOTHIE, size);
		this.fruits = numOfFruits;
		this.protein = addProtein;
	}

	/**
	 * return number of fruits
	 * @return number of fruits
	 */
	public int getNumOfFruits() {
		return this.fruits;
	}
	
	/**
	 * Indicates whether or not protein is added
	 * @return whether or not protein is added
	 */
	public boolean getAddProtein() {
		return this.protein;
	}
	
	/**
	 * returns the string representation of a Smoothie drink. Contains the name , size, whether or 
	 * not protein added , number of fruits and price
	 * @return the string representation of a Smoothie drink.
	 */
	@Override
	public String toString() {
		String str;
		str = String.format("%10s", "Smoothie: ")+"\t"+String.format("%10s", this.getBevName())+", \tSize: "+
							String.format("%10s",this.getSize()) + ",\t";
		if (this.getAddProtein()) 
			str += " with protein   ";
		else
			str += " with no protein";
		str += " and "+this.getNumOfFruits()+" fruits";
		str += "\t\t\t\tPrice: $ " + String.format("%.2f", this.calcPrice());
		return str;
	}
	
	/**
	 * calculates and returns the Smoothie beverage price based on base price, size, 
	 * added protein and number of fruits 
	 * @return the price of the smoothie
	 */
	@Override
	public double calcPrice() {
		double price = this.addSizePrice();
		if (this.getAddProtein())
			price += ADD_PROTEIN;
		if (this.getNumOfFruits() > 0)
			price += this.getNumOfFruits() * FRUIT_COST;
		return price;
	}
	
	/**
	 * Checks if this Beverage equals to anotherBev
	 * @return true if the name, type, size and base price, number of Fruits 
	 * and add protein are the same, false otherwise
	 * 
	 */
	@Override
	public boolean equals(Object anotherBev) {
		Smoothie other = (Smoothie) anotherBev;
		boolean status = false;
		if (this.getBevName().equals(other.getBevName()) && this.getType().equals(other.getType()) &&
				this.getSize().equals(other.getSize())  && this.getBasePrice()==other.getBasePrice() &&
				this.getNumOfFruits()==other.getNumOfFruits()  &&
				Boolean.compare(this.getAddProtein(), other.getAddProtein())==0)
			status = true;
		return status;
	}
}
