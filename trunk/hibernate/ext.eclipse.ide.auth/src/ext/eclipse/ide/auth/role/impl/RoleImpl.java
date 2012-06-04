/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package ext.eclipse.ide.auth.role.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ext.eclipse.ide.auth.role.IPermission;
import ext.eclipse.ide.auth.role.IRole;

/**
 * @author dzh
 * @date Nov 15, 2011 11:25:51 AM
 */
public class RoleImpl implements IRole {

	private String id;
	private String name;
	private String description;

	private List<IPermission> permissions;

	public RoleImpl() {
		permissions = Collections
				.synchronizedList(new ArrayList<IPermission>());
	}

	private RoleImpl(String id) {
		if (id == null || id.equals(""))
			throw new IllegalArgumentException(
					"Role's id must not be null or empty!");

		this.id = id;
		permissions = Collections
				.synchronizedList(new ArrayList<IPermission>());
	}

	public static IRole newRole(String id) {
		return new RoleImpl(id);
	}

	@SuppressWarnings("unused")
	private void setId(String id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see galaxy.ide.auth.role.IRole#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see galaxy.ide.auth.role.IRole#getName()
	 */
	@Override
	public synchronized String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see galaxy.ide.auth.role.IRole#setName(java.lang.String)
	 */
	@Override
	public synchronized void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see galaxy.ide.auth.role.IRole#getDescription()
	 */
	@Override
	public synchronized String getDescription() {
		return description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see galaxy.ide.auth.role.IRole#setDesciption(java.lang.String)
	 */
	@Override
	public synchronized void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Collection<IPermission> getPermissions() {
		List<IPermission> list = new ArrayList<IPermission>();
		synchronized (permissions) {
			for (IPermission p : permissions) {
				list.add(p.clone());
			}
		}
		return list;
	}

	@Override
	public boolean addPerm(IPermission perm) {
		return permissions.add(perm);
	}

	@Override
	public boolean removePerm(IPermission perm) {
		return permissions.add(perm);
	}

	@Override
	public boolean cantainPerm(IPermission perm) {
		return permissions.contains(perm);
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		synchronized (permissions) {
			for (IPermission p : permissions) {
				buf.append("(" + p.getId() + "," + p.getName() + ","
						+ p.getCategory().getId() + ")\n");
			}
		}

		return "Role: " + "(" + getId() + "," + getName() + "); "
				+ "Permissons: " + buf.toString();
	}

	public boolean equals(Object obj) {
		if (obj instanceof IRole) {
			if (this.getId().equals(((IRole) obj).getId())) {
				return true;
			}
		}
		return false;
	}
}
