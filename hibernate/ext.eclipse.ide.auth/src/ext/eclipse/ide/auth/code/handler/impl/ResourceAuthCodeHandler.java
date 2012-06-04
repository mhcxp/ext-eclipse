package ext.eclipse.ide.auth.code.handler.impl;

import ext.eclipse.ide.auth.code.delegate.IAuthCodeDelegate;
import ext.eclipse.ide.auth.code.handler.IAuthCodeHandler;
import ext.eclipse.ide.auth.code.handler.reader.AuthCodeReaderFactory;
import ext.eclipse.ide.auth.code.handler.reader.IAuthCodeReader;
import ext.eclipse.ide.auth.core.AuthCodeResource;

import java.util.Map;

/**
 * ��ԴȨ���봦������ͨ���ȡ��Դ���޸ĸ��಻����̳У��µ�Handler����ͨ��ʵ��IAuthCodeHandler��ɡ�
 * 
 * @author caiyu
 * 
 */
public class ResourceAuthCodeHandler implements IAuthCodeHandler {
	public final String TYPE = "RESOURCE";
	private String projectName;
	private Map<String, AuthCodeResource> resourceCache;

	public ResourceAuthCodeHandler(String projectName) {
		this.projectName = projectName;
		initAuthorationCache(AuthCodeReaderFactory.XML_READER);
	}

	public ResourceAuthCodeHandler(String projectName, String readerType) {
		this.projectName = projectName;
		initAuthorationCache(readerType);
	}

	private void initAuthorationCache(String readerType) {
		IAuthCodeReader reader = AuthCodeReaderFactory.getReader(readerType);
		reader.setProject(projectName);
		resourceCache = reader.getResourceAuthCodeSet();
	}

	@Override
	public String getProjectName() {
		return projectName;
	}

	@Override
	public String handle(IAuthCodeDelegate delegate) throws AuthCodeException {
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
		AuthCodeResource resource = resourceCache.get(delegate.getName());
		if (resource == null) {
			throw new AuthCodeException(this.getClass()
					+ " can not handle this delegate's resource type:"
					+ delegate.getName());
		}
		return resource.getId();
	}

	@Override
	public void setProjectName(String name) {
		this.projectName = name;
	}

}
