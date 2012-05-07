package galaxy.ide.auth.code.handler.reader;

import galaxy.ide.auth.core.AuthCodeAction;
import galaxy.ide.auth.core.AuthCodeResource;

import java.util.Map;

/**
 * 权限码源读取器
 * 
 * @author caiyu
 * 
 */
public interface IAuthCodeReader {
	/**
	 * 选择需要被读取的项目名，如MARS
	 * 
	 * @param projectName
	 */
	void setProject(String projectName);

	Map<String, AuthCodeAction> getActionAuthCodeSet();

	Map<String, AuthCodeResource> getResourceAuthCodeSet();
}
