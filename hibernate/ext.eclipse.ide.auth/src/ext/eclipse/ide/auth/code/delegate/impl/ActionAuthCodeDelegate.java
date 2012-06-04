package ext.eclipse.ide.auth.code.delegate.impl;

import ext.eclipse.ide.auth.code.delegate.IAuthCodeDelegate;

/**
 * ����Ȩ����ί��
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
