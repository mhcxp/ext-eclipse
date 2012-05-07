package galaxy.ide.auth.code.handler;

import galaxy.ide.auth.code.delegate.IAuthCodeDelegate;
import galaxy.ide.auth.code.handler.impl.AuthCodeException;

/**
 * 权限码处理器，正常的工作依赖权限码委托和项目名。
 * 
 * @author caiyu
 * 
 */
public interface IAuthCodeHandler {
	String DEFAULT_CODE = "FF";

	/**
	 * 设置项目名称
	 * 
	 * @param name
	 * @return
	 */
	void setProjectName(String name);

	/**
	 * 获取项目名称
	 * 
	 * @return
	 */
	String getProjectName();

	/**
	 * 处理委托，委托的type必须符合处理器的type
	 * 
	 * @param delegate
	 *            委托
	 * @return 权限码
	 */
	String handle(IAuthCodeDelegate delegate) throws AuthCodeException;
}
