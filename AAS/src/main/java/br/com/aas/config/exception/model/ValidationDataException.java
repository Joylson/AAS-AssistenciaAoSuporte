package br.com.aas.config.exception.model;

public class ValidationDataException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ValidationDataException() {
		super();
	}

	public ValidationDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationDataException(String message) {
		super(message);
	}

	
	
}
