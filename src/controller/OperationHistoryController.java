package controller;

import java.time.LocalDate;


import application.Main;
import exceptions.InvalidDateRangeException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Operation;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OperationHistoryController {

	public Main main;
	
	@FXML
	Label earningsLabel;
	
	@FXML
	Label expensesLabel;
	
	@FXML
	Label balanceLabel;

	@FXML
	TableView<Operation> operationsTable;
	
	@FXML
	TableColumn<Operation, LocalDate> dateColumn;
	
	@FXML
	TableColumn<Operation, Double> amountColumn;
	
	@FXML
	TableColumn<Operation, String> operationType;
	
	@FXML
	TableColumn<Operation, String> operationDescription;
	
	@FXML
	DatePicker initialDate;
	
	@FXML
	DatePicker finalDate;
	
	
	public void setHistory() {
		dateColumn.setCellValueFactory(new PropertyValueFactory<Operation, LocalDate>("date"));
		amountColumn.setCellValueFactory(new PropertyValueFactory<Operation, Double>("amount"));
		operationType.setCellValueFactory(new PropertyValueFactory<Operation, String>("type"));
		operationDescription.setCellValueFactory(new PropertyValueFactory<Operation, String>("description"));
		
		try {
			updateHistoryByDates();
		} catch (InvalidDateRangeException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Invalid date range");
			alert.setContentText("You may select an appropiate date range");
			alert.showAndWait();
		}
	}
	
	public void updateHistoryByDates() throws InvalidDateRangeException {
		LocalDate date1=null;
		LocalDate date2=null;
		double earningsSum=0;
		double expensesSum=0;
		double balance=0;	
		ArrayList<Operation> operations = new ArrayList<>();
		operations=main.getOperationArrayList();
		
		if(initialDate.getValue()!=null) {
			date1=initialDate.getValue();	
		}
		if(finalDate.getValue()!=null) {
			date2=finalDate.getValue();	
		}
		
		if(date1==null&&date2==null) {
			
			for(int i=0; i<operations.size();i++) {
				if(operations.get(i).getIncome()) {
					earningsSum+=operations.get(i).getAmount();
				}
				else {
					expensesSum+=operations.get(i).getAmount();
				}
			}
			balance=earningsSum-expensesSum;
			
			
			
			ObservableList<Operation> operationsList= FXCollections.observableArrayList(operations);
			
			operationsTable.setItems(operationsList);
		}
		else if(date1==null&date2!=null) {
			InvalidDateRangeException e= new InvalidDateRangeException("Innapropiate date range");
			throw e;
		}
		else if(date2.isBefore(date1)) {
			InvalidDateRangeException e= new InvalidDateRangeException("Innapropiate date range");
			throw e;	
		}
		else {
			ArrayList<Operation> dateOperations=new ArrayList<>();
			for (int i=0; i<operations.size();i++) {
				if(operations.get(i).getDate().isAfter(date1)&&operations.get(i).getDate().isBefore(date2)){
					dateOperations.add(operations.get(i));
					ObservableList<Operation> operationsList= FXCollections.observableArrayList(dateOperations);
					
					operationsTable.setItems(operationsList);
				}
				else if (operations.get(i).getDate().isEqual(date1)) {
					dateOperations.add(operations.get(i));
					ObservableList<Operation> operationsList= FXCollections.observableArrayList(dateOperations);
					
					operationsTable.setItems(operationsList);
				}
				else {
					dateOperations.clear();
					ObservableList<Operation> operationsList= FXCollections.observableArrayList(dateOperations);
					
					operationsTable.setItems(operationsList);
				}
			}
			for(int i=0; i<dateOperations.size();i++) {
				if(dateOperations.get(i).getIncome()) {
					earningsSum+=operations.get(i).getAmount();
				}
				else {
					expensesSum+=operations.get(i).getAmount();
				}
			}
			balance=earningsSum-expensesSum;
			
		}
		earningsLabel.setText("$"+earningsSum);
		expensesLabel.setText("$"+expensesSum);
		balanceLabel.setText("$"+balance);
	}
	
	public void deleteOperation() {
		//TODO Try/catch Alert
		Operation operation= operationsTable.getSelectionModel().getSelectedItem();
		main.deleteOperation(operation);
		initialDate.setValue(null);
		finalDate.setValue(null);
		try {
			updateHistoryByDates();
		} catch (InvalidDateRangeException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Invalid date range");
			alert.setContentText("You may select an appropiate date range");
			alert.showAndWait();
		}
	}
	
	public void resetDate() {
		initialDate.setValue(null);
		finalDate.setValue(null);
		try {
			updateHistoryByDates();
		} catch (InvalidDateRangeException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Invalid date range");
			alert.setContentText("You may select an appropiate date range");
			alert.showAndWait();
		}
	}
	public void setMain(Main main) {
		this.main=main;
	}
}
