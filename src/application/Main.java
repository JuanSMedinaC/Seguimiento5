package application;
	
import java.io.IOException;
import java.util.ArrayList;

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Bank;
import model.Operation;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private Stage currentStage;
	private Bank bank;
	@Override
	public void start(Stage primaryStage) {
		bank=new Bank();
		try {
			showMenu();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showMenu() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Menu.fxml"));
			
			BorderPane root;
			root = (BorderPane)loader.load();
			MenuController controller = loader.getController();
			
			controller.setMain(this);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			currentStage = stage;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showOperationMenu() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Operation.fxml"));
			
			BorderPane operation;
			operation = (BorderPane)loader.load();
			OperController controller = loader.getController();
			
			controller.setMain(this);
			
			BorderPane root;
			Stage stage = currentStage;
			root = (BorderPane)stage.getScene().getRoot();
			root.setCenter(operation);
			stage.setHeight(600);
			stage.setWidth(600);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showHistory() {
		try { 
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/OperationHistory.fxml"));
			
			BorderPane history;
			history= (BorderPane)loader.load();
			OperationHistoryController controller = loader.getController();
			
			controller.setMain(this);
			controller.setHistory();
			
			BorderPane root;
			Stage stage = currentStage;
			root = (BorderPane)stage.getScene().getRoot();
			root.setCenter(history);
			stage.setWidth(1158);
			stage.setHeight(916);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showMenuSameStage() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/Menu.fxml"));
			
			BorderPane root;
			root = (BorderPane)loader.load();
			MenuController controller = loader.getController();
			
			controller.setMain(this);
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("../ui/application.css").toExternalForm());
			currentStage.setHeight(426);
			currentStage.setWidth(634);
		
			currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addOperation(boolean income, double amount, String description) {
		bank.addOperation(income, amount, description);
	}
	
	public ArrayList<Operation> getOperationArrayList(){
		ArrayList<Operation> operation=bank.getOperationArrayList();
		return operation;
	}
	
	public void deleteOperation(Operation operation) {
		bank.deleteOperation(operation);
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
