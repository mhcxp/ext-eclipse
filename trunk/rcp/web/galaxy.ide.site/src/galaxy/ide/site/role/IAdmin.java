package galaxy.ide.site.role;

/**
 * 
 * @author caiyu
 * @version 2011-12-13 上午10:52:01
 */
public interface IAdmin {
	String getID();

	String getName();

	void setName(String name);

	String getPassword();

	void setPassword(String password);

	String getUserInfo(String key); // 获取用户信息

	void putUserInfo(String key, String value);// 设置用户信息
}
