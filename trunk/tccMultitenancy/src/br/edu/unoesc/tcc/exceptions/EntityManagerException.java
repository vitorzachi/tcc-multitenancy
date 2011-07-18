package br.edu.unoesc.tcc.exceptions;

/**
 * 
 * @author vitor
 */
public class EntityManagerException extends RuntimeException {

	private static final long serialVersionUID = 3671920172482500739L;

	public EntityManagerException(String string) {
		super(string);
	}
}
