package galaxy.ide.auth.code.handler.impl;

public class AuthCodeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3914691987291338277L;

	public AuthCodeException(String message) {
		super(message);
	}

	public AuthCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthCodeException(Throwable cause) {
		super(cause);
	}
}
