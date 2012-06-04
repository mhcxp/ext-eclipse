package ext.eclipse.ide.auth.core;

/**
 * Ȩ������Դ����
 * 
 * @author caiyu
 * 
 */
public class AuthCodeResource implements IAuthCodeElement {
	private String id;
	private String ext;
	private String value;

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return ext;
	}

	public void setType(String type) {
		this.ext = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
