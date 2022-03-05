package controller;

import java.net.URL;

import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;

public class MenuController implements Initializable{
	public Main main;
	@FXML
	private ComboBox comboBox;
	
	@FXML
	private Label labelTitle;
	
	@FXML
	private Label labelText;
	
	
	@FXML
	void Select (ActionEvent event) {
		String option = comboBox.getSelectionModel().getSelectedItem().toString();
		if(option.equalsIgnoreCase("Realize operation")){
			main.showOperationMenu();
		}
		else if(option.equalsIgnoreCase("Check history/balance")){
			main.showHistory();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> optionList= FXCollections.observableArrayList("Realize operation", "Check history/balance");
		comboBox.setItems(optionList);
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void showMenu() {
		main.showMenuSameStage();
	}
	
	public void showOperation() {
		main.showOperationMenu();
	}
	
	public void showHistory() {
		main.showHistory();
	}

	
}
