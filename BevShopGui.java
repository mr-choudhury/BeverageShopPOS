
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * GUI that Implements the Beverage Shop, This GUI will test all aspects of all classes and
 * make sure all classes and methods are implemented properly
 * 
 * @version 12/11/2022
 * @author Muhammad Choudhury
 */
public class BevShopGui extends Application{
	private static final String BEV_SHOP = "Bradley Beverage Shop";

	private static int time=0, age=0;
	private static Day day = null;
	private static BevShop myShop = new BevShop();
	private static Size size = null;
	
	private TextField custNameText, timeText, ageText;
	private TextField nameText, selectOrderText, numFruitsText;
	
	private Label customerNameLabel, timeLabel, ageLabel;
	private Label cNameLabel, cOrderNoLabel, cTotalLabel, cAgeLabel, cDayLabel, cTimeLabel, cSummary;
	private Label bevName, selectOrderLabel, numFruitsLabel, hintOrderNo;
	
	private Button newOrderBtn, exitBtn, reportBtn, sortBtn, addAlcoholBtn, addCoffeeBtn, addSmoothieBtn, selectBtn;
	private Button addAlcoholDrinkBtn, addCoffeeDrinkBtn, addSmoothieDrinkBtn, cancel, cDetailsBtn, findOrderBtn;
	
	private RadioButton s1, s2, s3;						// buttons for beverage size
	
	private CheckBox ex1, ex2, protein;					// check boxes for beverage options

	private ToggleGroup radioGroup = new ToggleGroup();	
	
	private ComboBox<String> weekday;
	
	// Main Pane
	private VBox mainPane = new VBox();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		createButtons();
		createCurrLabels();
		
		// Title Pane
		HBox titlePane = new HBox();
		titlePane.setPrefHeight(35);
		titlePane.setMaxWidth(650);
		Label titleLabel = new Label(BEV_SHOP + "\n");
		titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD,19));
		titleLabel.setTextFill(Color.FIREBRICK);
		titlePane.setAlignment(Pos.BOTTOM_CENTER);
		titlePane.getChildren().add(titleLabel);
		
		// New Order pane
		VBox orderPane = new VBox(5);
		buildNewOrderComponents();
		addNewOrderComponentsToMainPane(orderPane);
		TitledPane bevShopTitlePane = new TitledPane("New Order Form", orderPane);
		configureNewOrderTitlePane(bevShopTitlePane);
		
		mainPane.getChildren().addAll(titlePane, bevShopTitlePane);
		
		currOrderPane(mainPane);
		
		addMenuButtonsToMainPane(mainPane);
		
		disableButtons();		// disables buttons until the first order has been started
		
		setButtonActions();		// calls to activate button actions

		//  set the scene to hold root
		stage.setScene(new Scene(mainPane, 650, 380));
		stage.setResizable(false);
		
		//	set stage title
		stage.setTitle(BEV_SHOP);		
		
		//  display the stage
		stage.show();
	}

//********************************************************************************************************	

	private void configureNewOrderTitlePane(TitledPane newOrderTitlePane) {
		newOrderTitlePane.setAlignment(Pos.CENTER);
		newOrderTitlePane.setCollapsible(false);
		newOrderTitlePane.setMaxWidth(650);
		newOrderTitlePane.setPadding(new Insets(10, 10, 5, 10));
	}

	private void buildNewOrderComponents() {
		createNewOrderLabels();
		createNewOrderTextFields();
	}
	
	private void createNewOrderLabels() {
		customerNameLabel = new Label("Customer Name:");
		new Label("Day:");
		timeLabel = new Label("Time:");
		ageLabel = new Label("Age:");
	}
	
	private void createNewOrderTextFields() {
		custNameText = new TextField();
		custNameText.setMaxWidth(150);
		custNameText.textProperty().addListener((observable, oldValue, newValue) -> {
		    if(!newValue.matches("[a-z A-Z]*")){
		    	custNameText.setText(oldValue);
		    }
		});

		weekday = new ComboBox<String>();
		weekday.getItems().addAll("Select Day", "MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY","SUNDAY");
		weekday.setValue("Select Day");

		timeText = new TextField();
		timeText.setMaxWidth(35);
		timeText.textProperty().addListener((observable, oldValue, newValue) -> {
		    if(!newValue.matches("[0-9]*")){
		    	timeText.setText(oldValue);
		    }
		});
		
		ageText = new TextField();
		ageText.setMaxWidth(35);
		ageText.textProperty().addListener((observable, oldValue, newValue) -> {
		    if(!newValue.matches("[0-9]*")){
		    	ageText.setText(oldValue);
		    }
		});
	}
	
	private void addNewOrderComponentsToMainPane(VBox newOrderPane) {
		HBox newOrderComponentsPane = new HBox(7);
		newOrderComponentsPane.getChildren().addAll(customerNameLabel, custNameText);
		newOrderComponentsPane.getChildren().addAll(weekday);
		newOrderComponentsPane.getChildren().addAll(timeLabel, timeText);
		newOrderComponentsPane.getChildren().addAll(ageLabel, ageText);
		newOrderComponentsPane.getChildren().addAll(newOrderBtn);
		newOrderPane.getChildren().addAll(newOrderComponentsPane);
	}
	
//********************************************************************************************************	
	
	private void createCurrLabels() {
		cNameLabel = new Label("Name: ");
		cOrderNoLabel = new Label("Order No.: ");
		cTotalLabel = new Label("Total: ");
		cAgeLabel = new Label("Age: ");
		cDayLabel = new Label("Day: ");
		cTimeLabel = new Label("Time: ");
		cSummary = new Label(" Order Summary: ");
	}
	
	private void currOrderPane(VBox displayMainPane) {
		HBox hbox = new HBox (5);
		VBox vbox1 = new VBox(10);
		VBox vbox2 = new VBox(5);
		VBox vbox3 = new VBox(10);
		
		vbox1.getChildren().addAll(cNameLabel, cOrderNoLabel, cDayLabel, cTimeLabel, cAgeLabel, cTotalLabel);
		vbox1.setMinWidth(200);
		vbox1.setAlignment(Pos.CENTER_LEFT);
		VBox.setMargin(vbox1, new Insets(30, 10, 10, 10));
		
		vbox2.getChildren().addAll(cSummary);
		vbox2.setAlignment(Pos.TOP_LEFT);
		vbox2.setMinWidth(200);
		
		ScrollPane sp = new ScrollPane();
		sp.setPrefWidth(250);
		sp.setFitToWidth(true);
		sp.setPannable(false);
		sp.setContent(vbox2);

		vbox3.getChildren().addAll(addCoffeeBtn, addAlcoholBtn,  addSmoothieBtn, cDetailsBtn);
		vbox3.setAlignment(Pos.CENTER_RIGHT);
		vbox3.setMinWidth(150);
		
		hbox.getChildren().addAll(vbox1, sp, vbox3);
		hbox.setAlignment(Pos.CENTER);

		TitledPane hboxTitled = new TitledPane("Current Order Information", hbox);
		hboxTitled.setCollapsible(false);
		hboxTitled.setMaxWidth(650);
		hboxTitled.setMaxHeight(100);
		hboxTitled.setPadding(new Insets(5, 10, 10, 10));
		hboxTitled.setAlignment(Pos.CENTER);
		
		displayMainPane.getChildren().addAll(hboxTitled);
	}
	
//********************************************************************************************************	

	private void createButtons() {
		newOrderBtn = new Button("New Order");
		newOrderBtn.setTooltip(new Tooltip("Start a new Order Entry"));
		
		reportBtn = new Button("Monthly Report");
		reportBtn.setPrefWidth(135);
		reportBtn.setTooltip(new Tooltip("Generate Detailed Monthly Report"));
		
		sortBtn = new Button("Sort Orders");
		sortBtn.setPrefWidth(135);
		sortBtn.setTooltip(new Tooltip("Sort Orders in ascending Order Number"));
		
		selectBtn = new Button("Change Current Order");
		selectBtn.setPrefWidth(135);
		
		exitBtn = new Button("Exit");
		exitBtn.setPrefWidth(100);
		exitBtn.setTooltip(new Tooltip("Exit Program - Goodbye"));
		
		addAlcoholBtn = new Button("Add Alcohol");
		addAlcoholBtn.setPrefWidth(110);
		
		addCoffeeBtn = new Button("Add Coffee");
		addCoffeeBtn.setPrefWidth(110);
		
		addSmoothieBtn = new Button("Add Smoothie");
		addSmoothieBtn.setPrefWidth(110);
		
		cDetailsBtn = new Button("Order Details");
		cDetailsBtn.setPrefWidth(110);
		cDetailsBtn.setTooltip(new Tooltip("Show current order detail report"));
		
		findOrderBtn = new Button("Find Order");
		findOrderBtn.setTooltip(new Tooltip("Find order from provided order No"));
		
		addAlcoholDrinkBtn = new Button("Add Alcoholic Beverage");
		
		addCoffeeDrinkBtn = new Button("Add Coffee Beverage");
		
		addSmoothieDrinkBtn = new Button("Add Smoothie Beverage");
				
		cancel = new Button("Cancel");
		cancel.setTooltip(new Tooltip("Cancel and Exit"));
		
		s1 = new RadioButton("Small");
		s2 = new RadioButton("Medium");
		s3 = new RadioButton("Large");
		
		ex1 = new CheckBox("Extra Shot");
		ex2 = new CheckBox("Extra Syrup");
		
		protein = new CheckBox("Add Protein");	
	}
	
	private void disableButtons() {
		reportBtn.setDisable(true);
		sortBtn.setDisable(true);
		addAlcoholBtn.setDisable(true);
		addCoffeeBtn.setDisable(true);
		addSmoothieBtn.setDisable(true);
		cDetailsBtn.setDisable(true);
		selectBtn.setDisable(true);
	}
	
	private void enableButtons() {
		reportBtn.setDisable(false);
		sortBtn.setDisable(false);
		addAlcoholBtn.setDisable(false);
		addCoffeeBtn.setDisable(false);
		addSmoothieBtn.setDisable(false);
		cDetailsBtn.setDisable(false);
		selectBtn.setDisable(false);
	}
	
	//********************************************************************************************************	
	
	private void addMenuButtonsToMainPane(VBox mainPane) {
		HBox buttonPane = new HBox(40);
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.getChildren().addAll(reportBtn, sortBtn, selectBtn, exitBtn);
		buttonPane.setMaxWidth(650);
		mainPane.getChildren().addAll(buttonPane);
	}

	private void setButtonActions() {
		newOrderBtn.setOnAction(new ButtonEventHandler());
		reportBtn.setOnAction(new ButtonEventHandler());
		sortBtn.setOnAction(new ButtonEventHandler());
		addAlcoholBtn.setOnAction(new ButtonEventHandler());
		selectBtn.setOnAction(new ButtonEventHandler());
		exitBtn.setOnAction(new ButtonEventHandler());
		addAlcoholDrinkBtn.setOnAction(new ButtonEventHandler());
		cancel.setOnAction(new ButtonEventHandler());
		cDetailsBtn.setOnAction(new ButtonEventHandler());
		findOrderBtn.setOnAction(new ButtonEventHandler());
		addCoffeeBtn.setOnAction(new ButtonEventHandler());
		addCoffeeDrinkBtn.setOnAction(new ButtonEventHandler());
		addSmoothieBtn.setOnAction(new ButtonEventHandler());
		addSmoothieDrinkBtn.setOnAction(new ButtonEventHandler());
		
		newOrderBtn.setDefaultButton(true);	
		cancel.setCancelButton(true);
	}
	
	// Event Handler class for all Buttons
	private class ButtonEventHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			
			if (e.getSource() == newOrderBtn) {					// handler for new Order
				newOrder();
			}
			else if (e.getSource() == addAlcoholBtn) {			// handler for Alcohol Order
				addAlcohol();
			}
			else if (e.getSource() == addCoffeeBtn) {			// handler for Coffee Order
				addCoffee();
			}
			else if (e.getSource() == addSmoothieBtn) {			// handler for Smoothie order
				addSmoothie();
			}
			else if (e.getSource() == cancel) {					// handler for cancel button
			    Stage closeStage = (Stage) cancel.getScene().getWindow();
				closeStage.close();
			}
			else if (e.getSource() == reportBtn) {				// handler for Monthly report
				displayReportBox(myShop.toString());
			}
			else if (e.getSource() == cDetailsBtn){				// handler for current order details
				displayCurrentBox(myShop.getCurrentOrder().toString());
			}
			else if (e.getSource() == sortBtn){					// handler for sorting orders
				myShop.sortOrders();
				displayAlertBox("Orders have been sorted in ascending order number!");
			}
			else if (e.getSource() == selectBtn){				// handler for selecting a order
				selectOrderBox();
			}
			else if (e.getSource() == findOrderBtn) {			// handler for finding order
				selectOrder();
			}
			else if (e.getSource() == addAlcoholDrinkBtn) {		// handler for Alcohol Order
				processSize();
				addAlcoholDrink();
			}
			else if (e.getSource() == addCoffeeDrinkBtn) {		// handler for Coffee Order
				processSize();
				addCoffeeDrink();
			}
			else if (e.getSource() == addSmoothieDrinkBtn) {	// handler for Smoothie order
				processSize();
				addSmoothieDrink();
			}
			else if (e.getSource() == exitBtn)			 	// handler for Exit button
				System.exit(0);
		}
	}
	
	//********************************************************************************************************	

	public void selectOrderBox() {
		selectOrderLabel = new Label("Order No.");
		selectOrderText = new TextField();
		hintOrderNo = new Label("Hint: See Monthly Report for all order numbers.");
		selectOrderText.textProperty().addListener((observable, oldValue, newValue) -> {
		    if(!newValue.matches("[0-9]*")){
		    	selectOrderText.setText(oldValue);
		    }
		});

		HBox hbox = new HBox(20, selectOrderLabel, selectOrderText);
		hbox.setAlignment(Pos.CENTER);

		HBox buttonBox = new HBox(20, findOrderBtn, cancel);
		buttonBox.setAlignment(Pos.CENTER);
		VBox vbox = new VBox(20, hbox, hintOrderNo, buttonBox);
		Scene selectScene = new Scene(vbox, 300, 150);
		vbox.setAlignment(Pos.CENTER);
		Stage selectStage = new Stage();
		selectStage.setScene(selectScene);
		selectStage.setTitle("Select new Order No.");
		selectStage.showAndWait();
	}
	
	public void selectOrder() {
		int orderNoFound = myShop.findOrder(Integer.parseInt(selectOrderText.getText()));
		if (orderNoFound != -1) {
			myShop.setCurrentOrder(myShop.getOrderAtIndex(orderNoFound));
			
			cTimeLabel.setText(Integer.toString(myShop.getCurrentOrder().getOrderTime()));
			cAgeLabel.setText(Integer.toString(myShop.getCurrentOrder().getCustomer().getAge()));
			cOrderNoLabel.setText(Integer.toString(myShop.getCurrentOrder().getOrderNo()));
			cDayLabel.setText(myShop.getCurrentOrder().getDay().toString());
			cTotalLabel.setText(String.format("$ %.2f", myShop.getCurrentOrder().calcOrderTotal()));				
			cNameLabel.setText(myShop.getCurrentOrder().getCustomer().getName());
			
			cSummary.setText(" Order Summary: ");
			for (int i=0; i < myShop.getCurrentOrder().getTotalItems(); i++) {
				cSummary.setText(cSummary.getText() + "\n " + (i+1) + "\t" +  
						myShop.getCurrentOrder().getBeverage(i).getType());
				if (myShop.getCurrentOrder().getBeverage(i).getType().equals(Type.COFFEE))
					cSummary.setText(cSummary.getText() + "   \t\t\t\t");
				else
					cSummary.setText(cSummary.getText() + "   \t\t\t");
				cSummary.setText(cSummary.getText() + String.format("$ %.2f", myShop.getCurrentOrder().getBeverage(i).calcPrice()));
			}
			
			displayAlertBox("New order had been selected!");
		}
		else
			displayErrorBox("No orders exist with provided order Number!");
		
	    Stage closeStage = (Stage) findOrderBtn.getScene().getWindow();
		closeStage.close();
	}
	
	//********************************************************************************************************	

	private void addDrinkComponents() {
		bevName = new Label("Enter name of Beverage");
		nameText = new TextField();
		nameText.textProperty().addListener((observable, oldValue, newValue) -> {
		    if(!newValue.matches("[a-z A-Z]*")){
		    	nameText.setText(oldValue);
		    }
		});
	}
	
	public void processSize() {
		if (s1.isSelected())
			size = Size.SMALL;
		if (s2.isSelected())
			size = Size.MEDIUM;
		if (s3.isSelected())
			size = Size.LARGE;
	}
	
	private boolean isNameFieldEmpty() {
		return (nameText.getText().equals(""));
	}
	
	public void addCoffee() {
		addDrinkComponents();
		
		s1.setToggleGroup(radioGroup);
		s2.setToggleGroup(radioGroup);
		s3.setToggleGroup(radioGroup);
		
		s1.setSelected(true);
		ex1.setSelected(false);
		ex2.setSelected(false);
		
		HBox textBox = new HBox(20, bevName, nameText);
		textBox.setAlignment(Pos.CENTER);
		HBox radioHbox = new HBox(20, s1, s2, s3);
		radioHbox.setAlignment(Pos.CENTER);
		HBox checkHbox = new HBox(20, ex1, ex2);
		checkHbox.setAlignment(Pos.CENTER);
		HBox addBox = new HBox(30, addCoffeeDrinkBtn, cancel);
		addBox.setAlignment(Pos.CENTER);
		
		VBox vbox = new VBox(20, textBox, radioHbox, checkHbox, addBox);
		Scene coffeeScene = new Scene(vbox, 450, 250);
		vbox.setAlignment(Pos.CENTER);
		Stage coffeeStage = new Stage();
		coffeeStage.setScene(coffeeScene);
		coffeeStage.setTitle("Add Coffee Beverage to Current Order");
		coffeeStage.showAndWait();
	}
	
	private void addCoffeeDrink() {
		boolean shot = false, syrup = false;
		if (ex1.isSelected())
			shot = true;
		if (ex2.isSelected())
			syrup = true;
		
		if (!isNameFieldEmpty()) {
			myShop.processCoffeeOrder(nameText.getText(), size, shot, syrup);
			String str = "Coffee beverage: " + nameText.getText() + ", Size: " + size + ",\n";
			int index = myShop.getCurrentOrder().getTotalItems();
			if (shot)
				str += "with an extra shot, ";
			if (syrup)
				str += "with extra syrup, ";
			if (shot || syrup)
				str += "\n";
			str += "added to this order, cost: $ " + 
					String.format("%.2f", myShop.getCurrentOrder().getBeverage(index-1).calcPrice()) ;
			displayAlertBox(str);
		    Stage closeStage = (Stage) addCoffeeDrinkBtn.getScene().getWindow();
			closeStage.close();
			cSummary.setText(cSummary.getText() + "\n " + myShop.getCurrentOrder().getTotalItems() + "\t" +  "COFFEE\t\t\t\t" + 
					String.format("$ %.2f", myShop.getCurrentOrder().getBeverage(myShop.getCurrentOrder().getTotalItems()-1).calcPrice()));
			cTotalLabel.setText("Total: " + String.format("$ %.2f", myShop.getCurrentOrder().calcOrderTotal()));
			nameText.setText("");
		}
		else
			handleEmptyNameField();
	}

	public void addSmoothie() {
		addDrinkComponents();
		numFruitsLabel = new Label("Number of Fruits");
		numFruitsText = new TextField();
		numFruitsText.setMaxWidth(50);
		numFruitsText.textProperty().addListener((observable, oldValue, newValue) -> {
		    if(!newValue.matches("[0-9]*")){
		    	numFruitsText.setText(oldValue);
		    }
		});
		
		
		s1.setToggleGroup(radioGroup);
		s2.setToggleGroup(radioGroup);
		s3.setToggleGroup(radioGroup);
		
		s1.setSelected(true);

		protein.setSelected(false);
		
		HBox textBox = new HBox(20, bevName, nameText);
		textBox.setAlignment(Pos.CENTER);
		HBox radioHbox = new HBox(20, s1, s2, s3);
		radioHbox.setAlignment(Pos.CENTER);
		HBox checkHbox = new HBox(20, numFruitsLabel, numFruitsText, protein);
		checkHbox.setAlignment(Pos.CENTER);
		HBox addBox = new HBox(30, addSmoothieDrinkBtn, cancel);
		addBox.setAlignment(Pos.CENTER);
		
		VBox vbox = new VBox(20, textBox, radioHbox, checkHbox, addBox);
		Scene coffeeScene = new Scene(vbox, 450, 250);
		vbox.setAlignment(Pos.CENTER);
		Stage coffeeStage = new Stage();
		coffeeStage.setScene(coffeeScene);
		coffeeStage.setTitle("Add Smoothie Beverage to Current Order");
		coffeeStage.showAndWait();
	}
	
	private void addSmoothieDrink() {
		boolean p = false;
		int f = 0;
		
		if (!numFruitsText.getText().equals(""))
			f = Integer.parseInt(numFruitsText.getText());
		
		if (protein.isSelected())
			p = true;
		
		if (!isNameFieldEmpty()) {
			if (f >= 0 && f <= 5) {
				myShop.processSmoothieOrder(nameText.getText(), size, f, p);
				String str = "Smoothie beverage: " + nameText.getText() + ", Size: " + size + "\n";
				int index = myShop.getCurrentOrder().getTotalItems();
				if (f==1)
					str += "with " + f + " extra fruit, ";
				if (f>1)
					str += "with " + f + " extra fruits, ";
				if (p)
					str += "with added Protein, ";
				if (p || f>0)
					str += "\n";
				str += "added to this order, cost: $ " + 
						String.format("%.2f", myShop.getCurrentOrder().getBeverage(index-1).calcPrice()) ;
				displayAlertBox(str);
				
			    Stage closeStage = (Stage) addSmoothieDrinkBtn.getScene().getWindow();
				closeStage.close();
				cSummary.setText(cSummary.getText() + "\n " + myShop.getCurrentOrder().getTotalItems() + "\t" +  "SMOOTHIE\t\t\t" + 
						String.format("$ %.2f", myShop.getCurrentOrder().getBeverage(myShop.getCurrentOrder().getTotalItems()-1).calcPrice()));
				cTotalLabel.setText("Total: " + String.format("$ %.2f", myShop.getCurrentOrder().calcOrderTotal()));
				nameText.setText("");
			}
			else
				displayErrorBox("Invalid number of fruits (0-5)");
		}
		else
			handleEmptyNameField();
	}
	
	public void addAlcohol() {
		addDrinkComponents();
		
		s1.setToggleGroup(radioGroup);
		s2.setToggleGroup(radioGroup);
		s3.setToggleGroup(radioGroup);
		
		s1.setSelected(true);
		
		HBox textBox = new HBox(20, bevName, nameText);
		textBox.setAlignment(Pos.CENTER);
		HBox radioHbox = new HBox(20, s1, s2, s3);
		radioHbox.setAlignment(Pos.CENTER);
		HBox addBox = new HBox(30, addAlcoholDrinkBtn, cancel);
		addBox.setAlignment(Pos.CENTER);
		
		VBox vbox = new VBox(20, textBox, radioHbox, addBox);
		Scene alcoholScene = new Scene(vbox, 450, 200);
		vbox.setAlignment(Pos.CENTER);
		Stage alcoholStage = new Stage();
		alcoholStage.setScene(alcoholScene);
		alcoholStage.setTitle("Add Alcoholic Beverage to Current Order");
		alcoholStage.showAndWait();	
	}
	
	private void addAlcoholDrink() {
		if (!isNameFieldEmpty()) {
			if (myShop.isValidAge(myShop.getCurrentOrder().getCustomer().getAge())) {
				if (!myShop.isEligibleForMore()) {
					myShop.processAlcoholOrder(nameText.getText(), size);
					String str = "Alcoholic beverage: " + nameText.getText() + ", Size: " + size;
					int index = myShop.getCurrentOrder().getTotalItems();
					str += "\nadded to this order, cost: $ " + 
							String.format("%.2f", myShop.getCurrentOrder().getBeverage(index-1).calcPrice()) ;
					displayAlertBox(str);
				    Stage closeStage = (Stage) addAlcoholDrinkBtn.getScene().getWindow();
					closeStage.close();
					cSummary.setText(cSummary.getText() + "\n " + myShop.getCurrentOrder().getTotalItems() + "\t" +  "ALCOHOL\t\t\t" + 
							String.format("$ %.2f", myShop.getCurrentOrder().getBeverage(myShop.getCurrentOrder().getTotalItems()-1).calcPrice()));
					cTotalLabel.setText("Total: " + String.format("$ %.2f", myShop.getCurrentOrder().calcOrderTotal()));
					nameText.setText("");
				}
				else {
					displayErrorBox("Maximum Alcohol Limit Reached!");
				    Stage closeStage = (Stage) addAlcoholDrinkBtn.getScene().getWindow();
					closeStage.close();
				}
			}
			else {
				displayErrorBox("Underage, NO ALCOHOL ALLOWED");
			    Stage closeStage = (Stage) addAlcoholDrinkBtn.getScene().getWindow();
				closeStage.close();
			}
		}
		else
			handleEmptyNameField();
	}
	
	//********************************************************************************************************	

	private void newOrder() {
		if (!isnewOrderFieldEmpty()) {
			time = Integer.parseInt(timeText.getText());
			age = Integer.parseInt(ageText.getText());
			if (daySelection()) {
				if (isTimeValid(time)) {
					if (isAgeValid(age)) {
						myShop.startNewOrder(time, day, custNameText.getText(), age);
						String str = "New order started for " + custNameText.getText() + ",\n";
						str += "on " + day + " at " + time + " hours, age: " + age;
						displayAlertBox(str);
						
						custNameText.setText("");
						timeText.setText("");
						ageText.setText("");
						weekday.setValue("Select Day");
						enableButtons();
						
						cTimeLabel.setText("Time: " + Integer.toString(myShop.getCurrentOrder().getOrderTime()));
						cAgeLabel.setText("Age: " + Integer.toString(myShop.getCurrentOrder().getCustomer().getAge()));
						cOrderNoLabel.setText("Order No.: " + Integer.toString(myShop.getCurrentOrder().getOrderNo()));
						cDayLabel.setText("Day: " + myShop.getCurrentOrder().getDay().toString());
						cTotalLabel.setText("Total: " + String.format("$ %.2f", myShop.getCurrentOrder().calcOrderTotal()));				
						cNameLabel.setText("Name: " + myShop.getCurrentOrder().getCustomer().getName());
						cSummary.setText(" Order Summary: "); 
					}
					else
						handleInvalidAge();
				}
				else
					handleInvalidTime();
			}
			else
				handleInvalidDay();
		}
		else
			handleEmptyNewOrderField();
	}

	private boolean isnewOrderFieldEmpty() {
		boolean status = false;
		if (custNameText.getText().equals("") || timeText.getText().equals("") || ageText.getText().equals(""))
			status = true;
		return status;
	}
		
	private boolean isTimeValid(int time) {
		boolean status = false;
		if (time >= 8 && time <= 23)
			status = true;
		return status;
	}
	
	private boolean isAgeValid(int age) {
		return (age > 0);
	}
	
	private void handleInvalidAge() {
		displayErrorBox("Age is not valid, Enter an Integer value");
	}
	
	private void handleInvalidTime() {
		displayErrorBox("Time is not valid, Correct value is between 8-23");
	}
	
	private void handleInvalidDay() {
		displayErrorBox("Must choose a the day for order");
	}

	private void handleEmptyNewOrderField() {
		displayErrorBox("All fields must have data\nPlease enter correct values");
	}
	
	private void handleEmptyNameField() {
		displayErrorBox("Must enter Name of beverage!");
	}

	//********************************************************************************************************	
	
	public boolean daySelection() {
		String str = (String) weekday.getValue();
		switch (str) {
		case "MONDAY": {
			day = Day.MONDAY;
			return true;
		}
		case "TUESDAY": {
			day = Day.TUESDAY;
			return true;
		}
		case "WEDNESDAY": {
			day = Day.WEDNESDAY;
			return true;
		}
		case "THURSDAY": {
			day = Day.THURSDAY;
			return true;
		}
		case "FRIDAY": {
			day = Day.FRIDAY;
			return true;
		}
		case "SATURDAY": {
			day = Day.SATURDAY;
			return true;
		}
		case "SUNDAY": {
			day = Day.SUNDAY;
			return true;
		}
		default:
			return false;
		}
	}

	private void displayAlertBox(String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Bradley Beverage Shop - Informational");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}
	
	private void displayErrorBox(String text) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Bradley Beverage Shop - ERROR!");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}
	
	private void displayCurrentBox(String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.getDialogPane().setPrefSize(700, 500);
		
		TextArea area = new TextArea(text);
		area.setWrapText(true);
		area.setEditable(false);

		alert.getDialogPane().setContent(area);
		alert.setResizable(true);
		
		alert.setTitle("Bradley Beverage Shop - Current Order Details");
		alert.setHeaderText("Current Order Details");
		alert.showAndWait();
	}
	
	private void displayReportBox(String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.getDialogPane().setPrefSize(700, 600);
		
		TextArea area = new TextArea(text);
		area.setWrapText(true);
		area.setEditable(false);

		alert.getDialogPane().setContent(area);
		alert.setResizable(true);
		
		alert.setTitle("Bradley Beverage Shop - Monthly Report");
		alert.setHeaderText("MONTHLY SALES REPORT");
		alert.showAndWait();
	}
}
