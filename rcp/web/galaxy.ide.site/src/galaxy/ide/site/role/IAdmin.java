package galaxy.ide.site.role;

/**
 * 
 * @author caiyu
 * @version 2011-12-13 ����10:52:01
 */
public interface IAdmin {
	String getID();

	String getName();

	void setName(String name);

	String getPassword();

	void setPassword(String password);

	String getUserInfo(String key); // ��ȡ�û���Ϣ

	void putUserInfo(String key, String value);// �����û���Ϣ
}
