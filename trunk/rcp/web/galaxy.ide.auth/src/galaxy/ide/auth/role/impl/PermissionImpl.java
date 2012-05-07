package galaxy.ide.auth.role.impl;

import galaxy.ide.auth.role.IPCategory;
import galaxy.ide.auth.role.IPermission;

/**
 * 
 * @author caiyu
 * @date 2011/11/15 14:40
 */
public class PermissionImpl implements IPermission, Cloneable {
	private String ID;
	private String name;
	private IPCategory category;
	private String description;

	private PermissionImpl(String ID) {
		if (ID == null || ID.equals(""))
			throw new IllegalArgumentException(
					"Permission's ID must not be null or empty!");

		this.ID = ID;
	}

	public static PermissionImpl newPermission(String ID) {
		return new PermissionImpl(ID);
	}

	// private PermissionImpl(String ID, String name, IPCategory category,
	// String desc) {
	// if (name == null || name.equals(""))
	// throw new IllegalArgumentException(
	// "Permission's name must not be null or empty!");
	// if (category == null)
	// throw new IllegalArgumentException(
	// "Permission's category must not be null or empty!");
	//
	// this.ID = ID;
	// this.name = name;
	// this.category = category;
	// this.description = desc;
	// }

	public String getID() {
		return ID;
	}

	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	@Override
	public synchronized String getDescription() {
		return description;
	}

	@Override
	public synchronized void setDesciption(String description) {
		this.description = description;
	}

	public boolean equals(Object perm) {
		if (perm instanceof IPermission) {
			return ((IPermission) perm).getID().equals(ID);
		}
		return false;
	}

	public IPermission clone() {
		IPermission p = PermissionImpl.newPermission(ID);
		p.setName(getName());
		p.setDesciption(getDescription());
		p.setCategory(getCategory());
		return p;
	}

	@Override
	public synchronized void setCategory(IPCategory cate) {
		this.category = cate;
	}

	@Override
	public synchronized IPCategory getCategory() {
		return category;
	}
}
