package galaxy.ide.auth.code.delegate;

import galaxy.ide.auth.code.delegate.impl.ActionAuthCodeDelegate;
import galaxy.ide.auth.code.delegate.impl.ResourceAuthCodeDelegate;

import java.util.HashMap;
import java.util.Map;

/**
 * 权限码委托工厂，用户提供项目名和资源\操作名，该工厂会生产一个适用的权限码委托，该权限码委托将由权限码处理器：
 * galaxy.ide.auth.code.handler.IAuthCodeHandler 处理。
 * 错误的提交信息会导致null结果返回。
 * 
 * @author caiyu
 * 
 */
public final class AuthCodeDelegateFactory {
	public final static AuthCodeDelegateFactory INSTANCE;
	private final Map<String, Map<String, IAuthCodeDelegate>> actionDelegateCache;
	private final Map<String, Map<String, IAuthCodeDelegate>> resourceDelegateCache;

	static {
		INSTANCE = new AuthCodeDelegateFactory();
	}

	private AuthCodeDelegateFactory() {
		actionDelegateCache = new HashMap<String, Map<String, IAuthCodeDelegate>>();
		resourceDelegateCache = new HashMap<String, Map<String, IAuthCodeDelegate>>();
	}

	/**
	 * 根据项目名和操作名生成操作委托
	 * 
	 * @param projectName
	 *            项目名，如Mars
	 * @param actionName
	 *            操作名，如delete
	 * @return
	 */
	public IAuthCodeDelegate getActionAuthCodeDelegate(String projectName,
			String actionName) {
		IAuthCodeDelegate delegate = null;
		Map<String, IAuthCodeDelegate> cache = actionDelegateCache
				.get(projectName);
		if (cache == null) {
			cache = new HashMap<String, IAuthCodeDelegate>();
			actionDelegateCache.put(projectName, cache);
		} else {
			delegate = cache.get(actionName);
		}
		if (delegate == null) {
			delegate = new ActionAuthCodeDelegate();
			delegate.setProjectName(projectName);
			delegate.setName(actionName);
			cache.put(actionName, delegate);
		}
		return delegate;
	}

	/**
	 * 根据项目名和资源类型生成资源委托
	 * 
	 * @param projectName
	 *            项目名，如Mars
	 * @param resourceName
	 *            操作类型，如twf
	 * @return
	 */
	public IAuthCodeDelegate getResourceAuthCodeDelegate(String projectName,
			String resourceName) {
		IAuthCodeDelegate delegate = null;
		Map<String, IAuthCodeDelegate> cache = resourceDelegateCache
				.get(projectName);
		if (cache == null) {
			cache = new HashMap<String, IAuthCodeDelegate>();
			resourceDelegateCache.put(projectName, cache);
		} else {
			delegate = cache.get(resourceName);
		}
		if (delegate == null) {
			delegate = new ResourceAuthCodeDelegate();
			delegate.setProjectName(projectName);
			delegate.setName(resourceName);
			cache.put(resourceName, delegate);
		}
		return delegate;
	}

	public boolean equals(Object obj) {
		throw new UnsupportedOperationException();
	}
}
