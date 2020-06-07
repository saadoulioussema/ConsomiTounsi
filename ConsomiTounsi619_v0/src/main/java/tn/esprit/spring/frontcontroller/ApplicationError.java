package tn.esprit.spring.frontcontroller;

public class ApplicationError extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ApplicationError(String message) {super(message);}
}


