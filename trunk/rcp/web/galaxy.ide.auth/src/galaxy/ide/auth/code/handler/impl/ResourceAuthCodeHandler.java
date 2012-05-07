package galaxy.ide.auth.code.handler.impl;

import galaxy.ide.auth.code.delegate.IAuthCodeDelegate;
import galaxy.ide.auth.code.handler.IAuthCodeHandler;
import galaxy.ide.auth.code.handler.reader.AuthCodeReaderFactory;
import galaxy.ide.auth.code.handler.reader.IAuthCodeReader;
import galaxy.ide.auth.core.AuthCodeResource;

import java.util.Map;

/**
 * 资源权限码处理器。通过获取资源来修改该类不允许继承，新的Handler建议通过实现IAuthCodeHandler完成。
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
