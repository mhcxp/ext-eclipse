package ext.eclipse.ide.auth.code.handler.impl;

import ext.eclipse.ide.auth.code.delegate.IAuthCodeDelegate;
import ext.eclipse.ide.auth.code.handler.IAuthCodeHandler;
import ext.eclipse.ide.auth.code.handler.reader.AuthCodeReaderFactory;
import ext.eclipse.ide.auth.code.handler.reader.IAuthCodeReader;
import ext.eclipse.ide.auth.core.AuthCodeAction;

import java.util.Map;

/**
 * ����Ȩ���봦���������಻����̳У��µ�Handler����ͨ��ʵ��IAuthCodeHandler���
 * 
 * @author caiyu
 * 
 */
public final class ActionAuthCodeHandler implements IAuthCodeHandler {
	public final String TYPE = "ACTION";
	private String projectName;
	private Map<String, AuthCodeAction> actionCache;

	public ActionAuthCodeHandler(String projectName) {
		this.projectName = projectName;
		initAuthorationCache(AuthCodeReaderFactory.XML_READER);
	}

	public ActionAuthCodeHandler(String projectName, String readerType) {
		this.projectName = projectName;
		initAuthorationCache(readerType);
	}

	private void initAuthorationCache(String readerType) {
		IAuthCodeReader reader = AuthCodeReaderFactory.getReader(readerType);
		reader.setProject(projectName);
		actionCache = reader.getActionAuthCodeSet();
	}

	@Override
	public String getProjectName() {
		return projectName;
	}

	@Override
	public String handle(IAuthCodeDelegate delegate) throws AuthCodeException {
		// TODO Auto-generated method stub
		if (!TYPE.equals(delegate.getType())) {
			throw new AuthCodeException(this.getClass()
					+ " can not handle this delegate's type:"
					+ delegate.getType());
		}
		if (projectName == null
				|| !projectName.equals(delegate.getProjectName())) {
			throw new AuthCodeException(this.getClass()
					+ " can not handle this delegate's project:"
					+ delegate.getProjectName());
		}
		AuthCodeAction action = actionCache.get(delegate.getName());
		if (action == null) {
			throw new AuthCodeException(this.getClass()
					+ " can not handle this delegate's action:"
					+ delegate.getName());
		}
		return action.getId();
	}

	@Override
	public void setProjectName(String name) {
		this.projectName = name;
	}

}
