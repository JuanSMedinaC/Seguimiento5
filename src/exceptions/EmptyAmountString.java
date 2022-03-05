package exceptions;

public class EmptyAmountString extends Exception {
	public EmptyAmountString(String errorMessage) {
		super(errorMessage);
	}
}
