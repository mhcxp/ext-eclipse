package galaxy.ide.site.role.impl;

import galaxy.ide.site.role.IAdmin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AdminImpl implements IAdmin {

	private String ID;

	private String password;

	private String name;

	private Map<String, String> userInfo;

	private AdminImpl(String ID) {
		if (ID == null || ID.equals(""))
			throw new IllegalArgumentException(
					"Admin's ID must not be null or empty!");

		this.ID = ID;
		userInfo = Collections.synchronizedMap(new HashMap<String, String>());
	}

	public static final IAdmin newInstance(String ID) {
		return new AdminImpl(ID);
	}

	@Override
	public String getID() {
		return ID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUserInfo(String key) {
		return userInfo.get(key);
	}

	@Override
	public void putUserInfo(String key, String value) {
		userInfo.put(key, value);
	}

}
