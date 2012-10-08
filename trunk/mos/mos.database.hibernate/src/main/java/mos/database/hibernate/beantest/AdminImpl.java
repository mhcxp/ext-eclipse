package mos.database.hibernate.beantest;

/**
 * 
 * @author caiyu
 * @date 2012-9-21 обнГ4:42:27
 */
public class AdminImpl {
	private String id;
	private String name;
	private String password;

	public String getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
