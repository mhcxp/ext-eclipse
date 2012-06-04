/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package ext.eclipse.ide.auth.msg;

import ext.eclipse.ide.auth.role.IUser;

/**
 * @author dzh
 * @date Nov 15, 2011 7:09:58 PM Ȩ�ޱ��ĸ�ʽ
 */
public class AuthMsg {

	private IUser user;

	private AuthMsg(IUser user) {
		if (user == null)
			throw new IllegalArgumentException("Role must't be null!");

		this.user = user;
	}

	public static AuthMsg newAuthMsg(IUser user) {
		return new AuthMsg(user);
	}

	public String toString() {
		return "AuthMsg: " + user.toString();
	}

	public IUser getUser() {
		return user;
	}

}
