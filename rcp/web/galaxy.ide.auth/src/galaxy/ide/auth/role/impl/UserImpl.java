/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.ide.auth.role.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import galaxy.ide.auth.role.IRole;
import galaxy.ide.auth.role.IUser;

/**
 * @author dzh
 * @date Nov 16, 2011 3:53:28 PM
 */
public class UserImpl implements IUser {

	private String ID;

	private String password;

	private String name;

	private Map<String, String> userInfo;

	private List<IRole> roles;

	private UserImpl(String id) {
		if (id == null || id.equals(""))
			throw new IllegalArgumentException(
					"User's ID must not be null or empty!");

		this.ID = id;
		userInfo = Collections.synchronizedMap(new HashMap<String, String>());
		roles = Collections.synchronizedList(new ArrayList<IRole>());
	}

	public static final UserImpl newUser(String id) {
		return new UserImpl(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see galaxy.ide.auth.role.IUser#setPassword(java.lang.String)
	 */
	@Override
	public synchronized void setPassword(String password) {
		this.password = password;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see galaxy.ide.auth.role.IUser#getPassword()
	 */
	@Override
	public synchronized String getPassword() {
		return password;
	}

	@Override
	public String getID() {
		return ID;
	}

	@Override
	public synchronized String getName() {
		return name;
	}

	@Override
	public synchronized void setName(String name) {
		this.name = name;
	}

	public Map<String, String> getUserInfo() {
		return userInfo;
	}

	@Override
	public String getUserInfo(String key) {
		return userInfo.get(key);
	}

	@Override
	public void putUserInfo(String key, String value) {
		userInfo.put(key, value);
	}

	@Override
	public Collection<IRole> getRoles() {
		return roles;
	}

	@Override
	public void addRole(IRole role) {
		roles.add(role);
	}

	@Override
	public void removeRole(IRole role) {
		roles.remove(role);
	}

	@Override
	public boolean hasRole(IRole role) {
		synchronized (roles) {
			Iterator<IRole> iter = roles.iterator();
			while (iter.hasNext()) {
				if (iter.next().getID().equals(role.getID()))
					return true;
			}
		}
		return false;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("[");
		buf.append(getID() + "\n");
		synchronized (roles) {
			Iterator<IRole> iter = roles.iterator();
			while (iter.hasNext()) {
				buf.append(iter.next().toString() + "\n");
			}
		}
		buf.append("]");
		return buf.toString();
	}

}
