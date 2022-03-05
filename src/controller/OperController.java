package controller;


import java.net.URL;
import exceptions.*;
import java.util.ResourceBundle;

import application.Main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OperController implements Initializable{

	public Main main;
	
	@FXML
	private TextField amountArea;
	
	@FXML 
	private TextArea descriptionArea;
	
	@FXML
	private RadioButton incomeButton, expenseButton;
	 
	@FXML
	private Button complete;
	
	@FXML
	void completedOp() {
		try {
			addOperation();
			amountArea.clear();
			descriptionArea.clear();
			incomeButton.setSelected(false);
			expenseButton.setSelected(false);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Succesful operation");
			alert.setContentText("Your operation was registered succesfully");
			alert.showAndWait();
			
			} catch (InvalidEntryException | OptionNotSelected | EmptyAmountString e) {
				if(e instanceof InvalidEntryException) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("You didn't select introduce a number");
					alert.setContentText("You may type a number in the amount text field");
					alert.showAndWait();
				}
				else if(e instanceof OptionNotSelected) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("You didn't select a type of operation");
					alert.setContentText("You may select an operation type");
					alert.showAndWait();
				}
				else if(e instanceof EmptyAmountString) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("You didn't fill the amount text field");
					alert.setContentText("You may write the operation amount in the field");
					alert.showAndWait();
				}
				
			e.printStackTrace();
		}
		
	}
	
	public void addOperation() throws InvalidEntryException, OptionNotSelected, EmptyAmountString {
		if (amountArea.getText().equals("")) {
			EmptyAmountString e;
			throw e=new EmptyAmountString("Amount field not filled");
		}
		for (int i=0; i<amountArea.getText().length();i++) {
			if(amountArea.getText().charAt(i)!='1'&&amountArea.getText().charAt(i)!='2'&&amountArea.getText().charAt(i)!='3'&&amountArea.getText().charAt(i)!='4'&&amountArea.getText().charAt(i)!='5'&&amountArea.getText().charAt(i)!='6'&&amountArea.getText().charAt(i)!='7'&&amountArea.getText().charAt(i)!='8'&&amountArea.getText().charAt(i)!='9'&&amountArea.getText().charAt(i)!='0') {
				InvalidEntryException e=new InvalidEntryException("Number not inserted");
				throw e;
			}
		}
		double amount=Double.parseDouble(amountArea.getText());
		
		String description=descriptionArea.getText();
		boolean income = false;
		if(incomeButton.isSelected()==true) {
			income=true;
		}
		else if(expenseButton.isSelected()==true) {
			income=false;
		}
		else if(incomeButton.isSelected()==false && expenseButton.isSelected()==false) {
			OptionNotSelected e=new OptionNotSelected(description);
			throw e;
		}
		main.addOperation(income, amount, description);
	}
	
	public void setMain(Main main) {
		this.main = main;
	}	
	 @Override
	public void initialize(URL location, ResourceBundle resources) {
		 amountArea.setFocusTraversable(false);

	}
}


