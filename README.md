# BeverageShopPOS
A Java application for managing beverage orders at a family-owned shop, featuring GUI, order processing, and sales reporting.

## Project Overview
The Bradley Beverage Shop project is a Java application designed to manage beverage orders for a family-owned store. The project covers the creation and processing of different types of beverage orders, tracking customer information, and generating sales reports. It involves various OOP concepts such as inheritance, interfaces, abstract classes, and polymorphism.

## Project Components
1.	Beverage.java: Abstract class representing a generic beverage with properties such as name, type, size, and base price. It includes methods for calculating price and comparing beverages.
2.	Alcohol.java: Subclass of Beverage that adds specific properties for alcoholic beverages, including an additional weekend cost.
3.	Coffee.java: Subclass of Beverage that adds properties for extra shots and syrup in coffee.
4.	Smoothie.java: Subclass of Beverage that includes properties for the number of fruits and whether protein powder is added.
5.	Customer.java: Class representing a customer with properties like name and age.
6.	Order.java: Class that implements OrderInterface and Comparable, representing an order containing multiple beverages, with methods for adding beverages and generating order details.
7.	BevShop.java: Class that implements BevShopInterface, managing all orders and providing functionality for processing orders, calculating totals, and generating sales reports.
8.	Enumerations: Separate classes for Day, Size, and Type to represent the days of the week, beverage sizes, and beverage types respectively.
9.	JUnit Tests: Various JUnit test classes to ensure the correctness of each component, including AlcoholTestStudent.java, BevShopTestStudent.java, CoffeeTestStudent.java, CustomerTestStudent.java, SmoothieTestStudent.java, and more.
10.	BevShopDriverApp.java: Main driver class to demonstrate the functionalities of the beverage shop application.
11.	BevShopGui.java: GUI class to provide a user interface for interacting with the beverage shop system.

## Features
1.	Beverage Management: Ability to create and manage different types of beverages (Coffee, Alcohol, Smoothie) with customizable options and prices.
2.	Order Processing: Create and process orders, add beverages to orders, and manage customer information.
3.	Sales Reporting: Generate reports on the total sales and number of orders for a specific period.
4.	GUI: A graphical user interface to interact with the beverage shop system.
5.	JUnit Testing: Comprehensive testing using JUnit to ensure the correctness and reliability of the application.

## How to Run
1.	Compilation: Compile all Java files using a Java compiler. For example:
javac *.java
2.	Execution: Run the main driver class:
java BevShopDriverApp

## Lessons Learned
•	Mastery of Java inheritance, interfaces, and abstract classes.
•	Practical experience in developing and managing a GUI application.
•	Understanding of JUnit testing and its importance in software development.
## Assumptions and Limitations
•	The shop operates from 8 AM to 11 PM.
•	The minimum age to order alcohol is 21.
•	Limited to managing beverages of types Coffee, Alcohol, and Smoothie.

## Conclusion
This project demonstrates advanced proficiency in Java programming, particularly in GUI development, OOP principles, and testing. The comprehensive management of beverage orders and detailed sales reporting showcases the ability to design and implement complex software solutions effectively.
