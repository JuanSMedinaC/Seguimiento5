package model;
import java.util.ArrayList;
public class Bank {
	private ArrayList<Operation> operation; 
	
	public Bank() {
		operation=new ArrayList<>();
	}
	
	public void addOperation(boolean income, double amount, String description) {
		Operation operationObj;
		operationObj=new Operation(income, amount, description);
		
		operation.add(operationObj);
	}
	
	public double operationSum() {
		double sum=0;
		
		
		return sum;
	}
	
	public ArrayList<Operation> getOperationArrayList(){
		return operation;
	}

	public void deleteOperation(Operation operationObj) {
		for (int i=0; i<operation.size();i++) {
			if(operation.get(i).equals(operationObj)) {
				operation.remove(i);
			}
		}
	}
	
}
