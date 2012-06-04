/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package ext.eclipse.ide.auth.msg;

import ext.eclipse.ide.auth.role.IPermission;
import ext.eclipse.ide.auth.role.IRole;
import ext.eclipse.ide.auth.role.IUser;
import ext.eclipse.ide.auth.role.RoleFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author dzh
 * @date Nov 16, 2011 10:15:23 AM
 */
class AuthMsgTextCoder extends AuthMsgCoder {

	public static final String MAGIC = "Auth";
	public static final String CHARSETNAME = "UTF-8";
	public static final String DELIMITER = "##";

	public static final String MAGIC_USER = "User";
	public static final String MAGIC_ROLE = "Role";
	public static final String MAGIC_PERM = "Perm";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * galaxy.ide.auth.msg.IAuthMsgCoder#toWire(galaxy.ide.auth.msg.AuthMsg)
	 */
	@Override
	public byte[] toWire(AuthMsg msg) throws UnsupportedEncodingException {
		// header
		IUser user = msg.getUser();
		String msgHeader = MAGIC + DELIMITER + MAGIC_USER + DELIMITER
				+ user.getId() + DELIMITER + msg.getUser().getRoles().size()
				+ DELIMITER;
		Iterator<IRole> iter = msg.getUser().getRoles().iterator();
		IRole role = null;

		StringBuffer buf = new StringBuffer();
		while (iter.hasNext()) {
			role = iter.next();
			// role
			String msgRole = MAGIC_ROLE + DELIMITER + role.getId() + DELIMITER
					+ role.getName() + DELIMITER;
			buf.append(msgRole);
			// permission
			Iterator<IPermission> perms = role.getPermissions().iterator();
			buf.append(MAGIC_PERM + DELIMITER);
			while (perms.hasNext()) {
				IPermission p = perms.next();
				buf.append(p.getId() + DELIMITER + p.getName() + DELIMITER
						+ p.getCategory().getId() + DELIMITER); // maybe
			}

		}
		// join
		String msgString = msgHeader + buf.toString();
		return msgString.getBytes(CHARSETNAME);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see galaxy.ide.auth.msg.IAuthMsgCoder#fromWire(byte[])
	 */
	@Override
	public AuthMsg fromWire(byte[] input) throws IOException, MsgCoderException {
		ByteArrayInputStream msgStream = new ByteArrayInputStream(input);
		Scanner s = new Scanner(new InputStreamReader(msgStream, CHARSETNAME));
		s.useDelimiter(DELIMITER);

		//
		String token;
		token = scan(s);
		if (!token.equals(MAGIC))
			throw new MsgCoderException("Bad magic string: " + token);

		token = scan(s);
		if (!token.equals(MAGIC_USER))
			throw new MsgCoderException("Bad magic user string: " + token);
		token = scan(s); // id
		IUser user = RoleFactory.newUser(token);

		token = scan(s); // total
		int validate_count = Integer.parseInt(token);
		// role
		System.err.println("----\n\n" + token);
		token = scan(s);
		IRole role = null;
		int role_count = 0;
		while (token.equals(MAGIC_ROLE)) {
			// if (!token.equals(MAGIC_ROLE))
			// throw new IOException("Bad magic  role string: " + token);
			role = RoleFactory.newRole(scan(s)); // id
			role.setName(scan(s)); // name
			user.addRole(role);
			// perm
			token = scan(s);
			if (!token.equals(MAGIC_PERM))
				throw new MsgCoderException("Bad magic perm string: " + token);
			//
			IPermission perm = null;
			while (s.hasNext()) {
				token = scan(s);// id or MAGIC_ROLE
				if (token.equals(MAGIC_ROLE))
					break;
				perm = RoleFactory.newPermission(token);

				token = scan(s);// name
				perm.setName(token);

				token = scan(s);// category
				perm.setCategory(RoleFactory.newCategory(token));

				role.addPerm(perm);
			}

			role_count++;
		}
		if (validate_count != role_count)
			throw new MsgCoderException("Role's count is invalid: "
					+ ";validate count:" + validate_count
					+ ",but in fact it is " + role_count);

		return AuthMsg.newAuthMsg(user);
	}

	private String scan(Scanner s) {
		if (s.hasNext())
			return s.next();
		return "N/A";
	}
}
