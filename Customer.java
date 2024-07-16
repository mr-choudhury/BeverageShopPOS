

/**
 * Represents a Customer
 *
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */
public class Customer {

		private String name;
		private int age;
		
		/**
		 * Constructor
		 * @param name name of the customer
		 * @param age age of the customer
		 */
		public Customer(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		/**
		 * Copy Constructor
		 * @param c a Customer
		 */
		public Customer(Customer c) {
			this.name = c.getName();
			this.age = c.getAge();
		}
		
		/**
		 * get method for age
		 * @return returns the age
		 */
		public int getAge() {
			return this.age;
		}
		
		/**
		 * set method for age
		 * @param age age of the customer
		 */
		public void setAge(int age) {
			this.age = age;
		}
		
		/**
		 * get method for name
		 * @return the name
		 */
		public String getName() {
			return this.name;
		}
		
		/**
		 * set method for name
		 * @param name name of the customer
		 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * String representation of the customer includes the name and age of the customer.
		 * @return String containing the customer information
		 */
		@Override
		public String toString() {
			return "Customer name: "+this.getName()+", Age: "+this.getAge();
		}
}
