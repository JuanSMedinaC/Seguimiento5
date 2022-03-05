package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Operation {
	//Attributes
	private boolean income;
	private String type;
	private double amount;
	private String description;
	private LocalDate date;
	private String dateFormat;
	
	
	public Operation(boolean income, double amount, String description) {
		
		if(income==true) {
			this.type="Income";
		}
		else {
			this.type="Expense";
		}
		this.income=income;
		this.amount=amount;
		this.description=description;
		this.date=LocalDate.now();
		dateFormat = date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
	}
public Operation(LocalDate date, boolean income, double amount, String description) {
		
		if(income==true) {
			this.type="Income";
		}
		else {
			this.type="Expense";
		}
		this.income=income;
		this.amount=amount;
		this.description=description;
		this.date=date;
		dateFormat = date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
	}
	
	public Double getAmount(){
		return amount;
	}
	
	public boolean getIncome() {
		return income;
	}
	
	public String getDescription() {
		return description;
	}
	
	public LocalDate getDate(){
		return date;
	}

	public String getType() {
		return type;
	}
	
	
}
