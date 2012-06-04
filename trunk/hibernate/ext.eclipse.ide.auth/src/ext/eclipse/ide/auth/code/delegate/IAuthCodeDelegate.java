package ext.eclipse.ide.auth.code.delegate;

/**
 * Ȩ����ί�У���Ҫ��ȡȨ�������Դ���߲���Ӧ����Ȩ���봦�����ύ��ί��
 * 
 * @author caiyu
 * 
 */
public interface IAuthCodeDelegate {
	/**
	 * ��ȡ��Ŀ���
	 * 
	 * @return
	 */
	String getProjectName();

	/**
	 * ������Ŀ���
	 * 
	 * @param projectName
	 * @return
	 */
	void setProjectName(String projectName);

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	String getType();

	/**
	 * ������
	 * 
	 * @param type
	 * @return
	 */
	void setName(String name);

	/**
	 * ��ȡ��
	 * 
	 * @return
	 */
	String getName();

}
