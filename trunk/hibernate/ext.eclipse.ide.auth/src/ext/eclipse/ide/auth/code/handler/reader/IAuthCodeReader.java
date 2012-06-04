package ext.eclipse.ide.auth.code.handler.reader;

import ext.eclipse.ide.auth.core.AuthCodeAction;
import ext.eclipse.ide.auth.core.AuthCodeResource;

import java.util.Map;

/**
 * Ȩ����Դ��ȡ��
 * 
 * @author caiyu
 * 
 */
public interface IAuthCodeReader {
	/**
	 * ѡ����Ҫ����ȡ����Ŀ����MARS
	 * 
	 * @param projectName
	 */
	void setProject(String projectName);

	Map<String, AuthCodeAction> getActionAuthCodeSet();

	Map<String, AuthCodeResource> getResourceAuthCodeSet();
}
