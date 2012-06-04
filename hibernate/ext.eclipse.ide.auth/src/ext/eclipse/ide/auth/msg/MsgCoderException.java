/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package ext.eclipse.ide.auth.msg;

/**
 * @author dzh
 * @date Nov 17, 2011 11:15:31 AM
 */
public class MsgCoderException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7098103916328762805L;

	public MsgCoderException() {
		super();
	}

	public MsgCoderException(String message) {
		super(message);
	}

	public MsgCoderException(String message, Throwable cause) {
		super(message, cause);
	}

	public MsgCoderException(Throwable cause) {
		super(cause);
	}

}
