package galaxy.ide.auth.role.impl;

import galaxy.ide.auth.role.IPCategory;
import galaxy.ide.auth.role.IPermission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author caiyu
 * @date 2011/11/15 14:40
 */
public class PCategoryImpl implements IPCategory {
	private String ID;
	private String name;
	private String description;
	private List<IPermission> permissions;

	private PCategoryImpl(String ID) {
		if (ID == null || ID.equals(""))
			throw new IllegalArgumentException(
					"Category's ID must not be null or empty!");

		this.ID = ID;
		permissions = Collections
				.synchronizedList(new ArrayList<IPermission>());
	}

	public static PCategoryImpl newCategory(String ID) {
		return new PCategoryImpl(ID);
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

	@Override
	public synchronized String getDescription() {
		return description;
	}

	@Override
	public synchronized void setDesciption(String description) {
		this.description = description;
	}

	@Override
	public Collection<IPermission> permissions() {
		List<IPermission> list = new ArrayList<IPermission>();
		synchronized (permissions) {
			Iterator<IPermission> iter = permissions.iterator();
			while (iter.hasNext()) {
				list.add(iter.next().clone());
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
		return permissions.remove(perm);
	}

	@Override
	public boolean containPerm(IPermission perm) {
		return permissions.contains(perm);
	}

	public String toString() {
		return "PCategory:" + getID();
	}

	@Override
	public String toJSONString() {
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		buf.append("ID:" + "\'" + ID + "\'");
		buf.append(",Name:" + "\'" + name + "\'");
		buf.append(",Description:" + "\'" + description + "\'");
		buf.append("}");
		return buf.toString();
	}

}
