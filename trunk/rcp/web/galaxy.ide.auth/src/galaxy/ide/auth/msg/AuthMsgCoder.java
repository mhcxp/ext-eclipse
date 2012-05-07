/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.ide.auth.msg;

/**
 * @author dzh
 * @date Nov 16, 2011 11:26:32 AM
 */
public abstract class AuthMsgCoder implements IAuthMsgCoder {

	public static final int Type_DELI = 1; // ·Ö¸ô·û±¨ÎÄ

	protected AuthMsgCoder() {
	}

	public static final AuthMsgCoder newInstance(int type) {
		switch (type) {
		case Type_DELI:
			return new AuthMsgTextCoder();
		}

		throw new IllegalArgumentException("Coder's type is illegal:" + type);
	}

}
