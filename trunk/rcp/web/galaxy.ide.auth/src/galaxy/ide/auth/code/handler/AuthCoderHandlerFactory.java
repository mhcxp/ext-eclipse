package galaxy.ide.auth.code.handler;

import galaxy.ide.auth.code.handler.impl.ActionAuthCodeHandler;
import galaxy.ide.auth.code.handler.impl.ResourceAuthCodeHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限码处理器工厂
 * 
 * @author caiyu
 * 
 */
public final class AuthCoderHandlerFactory {
	public final static AuthCoderHandlerFactory INSTANCE;
	private final Map<String, IAuthCodeHandler> actionHandlerCache;
	private final Map<String, IAuthCodeHandler> resourceHandlerCache;

	static {
		INSTANCE = new AuthCoderHandlerFactory();
	}

	private AuthCoderHandlerFactory() {
		actionHandlerCache = new HashMap<String, IAuthCodeHandler>();
		resourceHandlerCache = new HashMap<String, IAuthCodeHandler>();
	}

	/**
	 * 根据项目名获取处理器
	 * 
	 * @param projectName
	 * @return
	 */
	public IAuthCodeHandler getActionAuthCodeHandler(String projectName) {
		IAuthCodeHandler handler = actionHandlerCache.get(projectName);
		if (handler == null) {
			handler = new ActionAuthCodeHandler(projectName);
			actionHandlerCache.put(projectName, handler);
		}
		return handler;
	}

	/**
	 * 根据项目名获取处理器
	 * 
	 * @param projectNamee
	 * @return
	 */
	public IAuthCodeHandler getResourceAuthCodeHandler(String projectName) {
		IAuthCodeHandler handler = resourceHandlerCache.get(projectName);
		if (handler == null) {
			handler = new ResourceAuthCodeHandler(projectName);
			resourceHandlerCache.put(projectName, handler);
		}
		return handler;
	}

	public boolean equals(Object obj) {
		throw new UnsupportedOperationException();
	}
}
