package exceptions;

public class InvalidDateRangeException extends Exception {
	public InvalidDateRangeException(String errorMessage) {
		super(errorMessage);
	}
}
