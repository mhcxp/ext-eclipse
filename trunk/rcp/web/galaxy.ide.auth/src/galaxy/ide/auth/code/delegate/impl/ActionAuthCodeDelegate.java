package galaxy.ide.auth.code.delegate.impl;

import galaxy.ide.auth.code.delegate.IAuthCodeDelegate;

/**
 * 操作权限码委托
 * 
 * @author caiyu
 * 
 */
public class ActionAuthCodeDelegate implements IAuthCodeDelegate {
	public static final String TYPE = "ACTION";
	private String projectName;
	private String name;

	@Override
	public String getProjectName() {
		return projectName;
	}

	@Override
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
