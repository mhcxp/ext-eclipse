package galaxy.ide.auth.core;

/**
 * 权限码“操作”对象
 * 
 * @author caiyu
 * 
 */
public class AuthCodeAction implements IAuthCodeElement {
	private String id;
	private String name;
	private String value;

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
