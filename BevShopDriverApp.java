import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the Driver App for Assignment 6 via console input/output. 
 * It throughly tests all the classes.  There is a full menu system
 * and one extra method has been added to BevShop class called setCurrentOrder
 * which improves the functionality of the app.  Now you can select and 
 * choose which Order should be edited, especially helpful after sorting the list
 * 
 * @version 12/08/2022
 * @author Muhammad Choudhury
 */
public class BevShopDriverApp {

	private static char user=' ';
	private static char orderMenu=' ';
	private static int time=0, age=0, orderNo=0, orderNoFound=0;
	private static Day day = null;
	private static String name = "", strDay = "";
	private static Scanner input = new Scanner(System.in);
	private static BevShop myShop = new BevShop();
	
	public static void main(String[] args) {
		// Start by providing a selectable Main Menu
		System.out.println("\t     ******* Welcome to the BevShop Ordering system *******\n");
		System.out.println("MAIN MENU:");
		System.out.println("'N'-Start New Order; 'X'-Exit Program");
		user = input.nextLine().charAt(0);
		while (user!='N' && user!='n' && user!='X' && user!='x') {			// repeat until correct input
			System.out.println("Invalid Entry, Please Try Again.");
			user = input.nextLine().charAt(0);
		}
		
		while (user=='N' || user=='n' || user=='R' || user=='r' 
				|| user=='S' || user=='s' || user=='E' || user=='e') {		// repeat until correct input
			if (user == 'N' || user == 'n') {								// User selects new order entry
				while (!info()) {											// authenticate valid order information
					System.out.println("Enter New Order Information Again!");
				}
				
				myShop.startNewOrder(time, day, name, age);					// Start new order
				System.out.println("\n\t\t\t**** New Order Started ****");	
				orderEntry();												// Call Order Entry Menu
				while (orderMenu != 'X' && orderMenu != 'x') {				// Repeat order entry menu until exit
					orderEntry();
				}
			}
			
			if (user == 'R' || user == 'r') {								// Prints Monthly report
				System.out.println(myShop);
			}
			
			if (user == 'S' || user == 's') {								// Sorts orders in ascending order by order number
				myShop.sortOrders();
				System.out.println("Orders have been sorted in ascending order number!");
			}
			
			if (user == 'E' || user == 'e') {								// Selectable order to be edited		
				System.out.println("Would you like to edit the current/last order (Y/N)?");
				user = input.nextLine().charAt(0);
				while (user!='Y' && user!='y' && user!='N' && user!='n') {		// repeat until correct input
					System.out.println("Invalid Entry, Please Try Again.");
					user = input.nextLine().charAt(0);
				}
				if (user=='Y' || user=='y') {								// enter order edit mode for current order
					orderEntry();
					while (orderMenu != 'X' && orderMenu != 'x') {
						orderEntry();
					}
				}
				else if (user=='N' || user=='n') {							// select current order by order number
					System.out.println("Enter order number to edit: ");
					try {
						orderNo = input.nextInt();
						input.nextLine();
						orderNoFound = myShop.findOrder(orderNo);			// validate entered order number
						if (orderNoFound != -1) {
							myShop.setCurrentOrder(myShop.getOrderAtIndex(orderNoFound)); //set current order to entered order number
							orderEntry();
							while (orderMenu != 'X' && orderMenu != 'x') {
								orderEntry();
							}
						}
						else
							System.out.println("Invalid order number, exiting to MAIN MENU!");
					}
					catch(InputMismatchException e) {						// input validation catch
						System.out.println("You entered an invalid integer order number, try again!");
						input.nextLine();
					}
				}
			}

			System.out.println("\nMAIN MENU:");
			System.out.println("'N'-Start new order; 'R'-Monthly Report; 'S'-Sort orders; " +
								"'E'-Edit order; 'X'-Exit program");
			user = input.nextLine().charAt(0);
			while (user!='N' && user!='n' && user!='X' && user!='x'
					&& user!='R' && user!='r' && user!='S' && user!='s'
					&& user!='E' && user!='e') {								// repeat until correct input
				System.out.println("Invalid Entry, Please Try Again.");
				user = input.nextLine().charAt(0);
			}
		}
		
		System.out.println("\n\nThank you for using the BevShop ordering system!");
		System.out.println("Programmer - Muhammad Choudhury");
	}
	
	/**
	 * This method prompts user to enter new order information and validated input data
	 * @return true if correct new order information was entered, false otherwise
	 */
	public static boolean info() {
		boolean status = true;
		System.out.print("Enter the time: ");
		try {
			time = input.nextInt();
			input.nextLine();											// read time input data
			if (myShop.isValidTime(time)) {								// validate time
					System.out.print("Enter the Day: 'MONDAY, TUESDAY," + 
									" WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY': ");
					strDay = input.nextLine();							// read day input data
					day = Day.valueOf(strDay.toUpperCase());			// convert to upper case
					System.out.print("Enter Customer name: ");
					name = input.nextLine();							// read customer name
					System.out.print("Enter Customer age: ");
					age = input.nextInt();								// read customer age
					input.nextLine();
			}
			else {
				System.out.println("Invalid time for order.");			// if invalid time set false
				status = false;
			}
		}
		catch(InputMismatchException e) {								// catch invalid age
			System.out.println("You entered an invalid integer!");
			input.nextLine();
			status = false;
		}
		catch(IllegalArgumentException e) {								// catch invalid day
			System.out.println("You entered an invalid Day!");
			status = false;
		}
		catch(Throwable e) {											// catch any other invalid data
			System.out.println("You entered an invalid Data!");
			status = false;
		}
		return status;			// returns the status of the user information, true if good false otherwise
	}

	/**
	 * This is the order entry menu system, it allows the user to add beverages to current order via
	 * a menu system that user can select what type of beverage is being added 
	 */
	public static void orderEntry() {
		String bev;
		char shot, syrup, protein, s;
		boolean extraShot, extraSyrup, addProtein;
		Size size = Size.SMALL;
		int fruits;
		
		System.out.println("\nOrder Entry Menu:");
		System.out.println("'C'-New Coffee order; 'S'-New Smoothie order; 'A'-New Alcohol order;");
		System.out.println("'T'-Total for order; 'D'-Details of order; 'X'-Exit to MAIN MENU");		
		orderMenu = input.nextLine().charAt(0);		
		
		while (orderMenu!='C' && orderMenu!='c' && orderMenu!='S' && orderMenu!='s' 
				&& orderMenu!='A' && orderMenu!='a' && orderMenu!='X' && orderMenu!='x'
				&& orderMenu!='T' && orderMenu!='t' && orderMenu!='D' && orderMenu!='d') {	// repeat until correct input
			System.out.println("Invalid Entry, Please Try Again.");
			orderMenu = input.nextLine().charAt(0);
		} 
		
		if (orderMenu == 'C' || orderMenu == 'c') {							// add new Coffee beverage to current Order
			try {
				System.out.print("Enter name of Coffee Beverage:");
				bev = input.nextLine();
				
				System.out.print("Enter Size ('S, M, L'): ");
				s = input.nextLine().charAt(0);	
				while (s!='S' && s!='s' && s!='M' && s!='m' && s!='L' && s!='l') {			// repeat until correct input
					System.out.println("Invalid Entry, Please Try Again.");
					s = input.nextLine().charAt(0);	
				} 
				if (s == 'S' || s == 's')
					size = Size.SMALL;
				if (s == 'M' || s == 'm')
					size = Size.MEDIUM;
				if (s == 'L' || s == 'l')
					size = Size.LARGE;
				
				System.out.print("Extra Shot (Y/N): ");
				shot = input.nextLine().charAt(0);	
				while (shot!='y' && shot!='Y' && shot!='n' && shot!='N') {
					System.out.println("Invalid Entry, Please Try Again.");
					shot = input.nextLine().charAt(0);	
				} 
				if (shot == 'Y' || shot == 'y')
					extraShot = true;
				else
					extraShot =false;
				
				System.out.print("Extra Syrup (Y/N): ");
				syrup = input.nextLine().charAt(0);	
				while (syrup!='y' && syrup!='Y' && syrup!='n' && syrup!='N') {
					System.out.println("Invalid Entry, Please Try Again.");
					syrup = input.nextLine().charAt(0);	
				} 
				if (syrup == 'Y' || syrup == 'y')
					extraSyrup = true;
				else
					extraSyrup =false;
				
				myShop.processCoffeeOrder(bev, size, extraShot, extraSyrup);
			}
			catch (Throwable e) {											// catch any invalid entry by user and print message
				System.out.println("Invalid Data, PLease Try Again!");
			}
		}
		
		if (orderMenu == 'S' || orderMenu == 's') {							// add new Smoothie beverage to current Order
			try {
				System.out.print("Enter name of Smoothie Beverage: ");
				bev = input.nextLine();
				
				System.out.print("Enter Size ('S, M, L'): ");
				s = input.nextLine().charAt(0);	
				while (s!='S' && s!='s' && s!='M' && s!='m' && s!='L' && s!='l') {
					System.out.println("Invalid Entry, Please Try Again.");
					s = input.nextLine().charAt(0);	
				} 
				if (s == 'S' || s == 's')
					size = Size.SMALL;
				if (s == 'M' || s == 'm')
					size = Size.MEDIUM;
				if (s == 'L' || s == 'l')
					size = Size.LARGE;
				
				
				System.out.print("Extra Number of Fruits (max 5): ");
				fruits = input.nextInt();
				input.nextLine();
				while (fruits < 0 || fruits >5) {
					System.out.println("Invalid Entry, Please Try Again.");
					fruits = input.nextInt();
					input.nextLine();	
				} 
				
				System.out.print("Add Protein (Y/N): ");
				protein = input.nextLine().charAt(0);	
				while (protein!='y' && protein!='Y' && protein!='n' && protein!='N') {
					System.out.println("Invalid Entry, Please Try Again.");
					protein = input.nextLine().charAt(0);	
				} 
				if (protein == 'Y' || protein == 'y')
					addProtein = true;
				else
					addProtein =false;
				myShop.processSmoothieOrder(bev, size, fruits, addProtein);
			}
			catch (Throwable e) {											// catch any invalid entry by user and print message
				System.out.println("Invalid Data, PLease Try Again!");
			}
		}
		
		if (orderMenu == 'A' || orderMenu == 'a') {							// add new Alcohol beverage to current Order
			try {
				System.out.print("Enter name of Alcohol Beverage: ");
				bev = input.nextLine();
				
				System.out.print("Enter Size ('S, M, L'): ");
				s = input.nextLine().charAt(0);	
				while (s!='S' && s!='s' && s!='M' && s!='m' && s!='L' && s!='l') {
					System.out.println("Invalid Entry, Please Try Again.");
					s = input.nextLine().charAt(0);	
				} 
				if (s == 'S' || s == 's')
					size = Size.SMALL;
				if (s == 'M' || s == 'm')
					size = Size.MEDIUM;
				if (s == 'L' || s == 'l')
					size = Size.LARGE;
				myShop.processAlcoholOrder(bev, size);
			}
			catch (Throwable e) {											// catch any invalid entry by user and print message
				System.out.println("Invalid Data, PLease Try Again!");
			}
		}
		
		if (orderMenu == 'T' || orderMenu == 't') {							// prints the total for current order
			System.out.println("The total for this order is: $ " + String.format("%.2f", myShop.getCurrentOrder().calcOrderTotal()));
		}
		
		if (orderMenu == 'X' || orderMenu == 'x') {							// Exits out of order entry menu to Main menu
			System.out.println("Exiting Order Entry Menu");
		}
		
		if (orderMenu == 'D' || orderMenu == 'd') {							// prints details of the current order, invokes toString
			System.out.println("===========================================================================");
			System.out.println(myShop.getCurrentOrder());
		}
	}
		
}
