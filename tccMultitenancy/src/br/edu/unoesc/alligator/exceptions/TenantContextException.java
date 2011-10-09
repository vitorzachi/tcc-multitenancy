package br.edu.unoesc.alligator.exceptions;

public class TenantContextException extends RuntimeException {

	private static final long serialVersionUID = 4448107299362655778L;

	public TenantContextException(String msg) {
		super(msg);
	}
}
