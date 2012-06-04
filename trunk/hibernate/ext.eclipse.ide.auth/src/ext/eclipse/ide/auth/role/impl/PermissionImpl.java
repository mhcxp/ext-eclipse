package ext.eclipse.ide.auth.role.impl;

import ext.eclipse.ide.auth.role.IPCategory;
import ext.eclipse.ide.auth.role.IPermission;

/**
 * 
 * @author caiyu
 * @date 2011/11/15 14:40
 */
public class PermissionImpl implements IPermission, Cloneable {
	private String id;
	private String name;
	private IPCategory category;
	private String description;

	public PermissionImpl() {
	}

	private PermissionImpl(String id) {
		if (id == null || id.equals(""))
			throw new IllegalArgumentException(
					"Permission's id must not be null or empty!");

		this.id = id;
	}

	public static PermissionImpl newPermission(String id) {
		return new PermissionImpl(id);
	}

	// private PermissionImpl(String id, String name, IPCategory category,
	// String desc) {
	// if (name == null || name.equals(""))
	// throw new IllegalArgumentException(
	// "Permission's name must not be null or empty!");
	// if (category == null)
	// throw new IllegalArgumentException(
	// "Permission's category must not be null or empty!");
	//
	// this.id = id;
	// this.name = name;
	// this.category = category;
	// this.description = desc;
	// }

	public String getId() {
		return id;
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
	public synchronized void setDescription(String description) {
		this.description = description;
	}

	public boolean equals(Object perm) {
		if (perm instanceof IPermission) {
			return ((IPermission) perm).getId().equals(id);
		}
		return false;
	}

	public IPermission clone() {
		IPermission p = PermissionImpl.newPermission(id);
		p.setName(getName());
		p.setDescription(getDescription());
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
