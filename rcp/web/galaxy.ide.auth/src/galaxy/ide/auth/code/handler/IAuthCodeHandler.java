package galaxy.ide.auth.code.handler;

import galaxy.ide.auth.code.delegate.IAuthCodeDelegate;
import galaxy.ide.auth.code.handler.impl.AuthCodeException;

/**
 * Ȩ���봦�����������Ĺ�������Ȩ����ί�к���Ŀ����
 * 
 * @author caiyu
 * 
 */
public interface IAuthCodeHandler {
	String DEFAULT_CODE = "FF";

	/**
	 * ������Ŀ����
	 * 
	 * @param name
	 * @return
	 */
	void setProjectName(String name);

	/**
	 * ��ȡ��Ŀ����
	 * 
	 * @return
	 */
	String getProjectName();

	/**
	 * ����ί�У�ί�е�type������ϴ�������type
	 * 
	 * @param delegate
	 *            ί��
	 * @return Ȩ����
	 */
	String handle(IAuthCodeDelegate delegate) throws AuthCodeException;
}
