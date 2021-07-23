package es.mindata.superheroes.exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8461932564550091456L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
