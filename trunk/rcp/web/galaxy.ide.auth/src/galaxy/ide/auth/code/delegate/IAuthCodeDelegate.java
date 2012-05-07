package galaxy.ide.auth.code.delegate;

/**
 * 权限码委托，需要获取权限码的资源或者操作应该向权限码处理器提交本委托
 * 
 * @author caiyu
 * 
 */
public interface IAuthCodeDelegate {
	/**
	 * 获取项目名称
	 * 
	 * @return
	 */
	String getProjectName();

	/**
	 * 设置项目名称
	 * 
	 * @param projectName
	 * @return
	 */
	void setProjectName(String projectName);

	/**
	 * 获取类型
	 * 
	 * @return
	 */
	String getType();

	/**
	 * 设置名
	 * 
	 * @param type
	 * @return
	 */
	void setName(String name);

	/**
	 * 获取名
	 * 
	 * @return
	 */
	String getName();

}
