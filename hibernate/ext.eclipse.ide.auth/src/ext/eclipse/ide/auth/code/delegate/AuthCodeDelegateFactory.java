package ext.eclipse.ide.auth.code.delegate;

import ext.eclipse.ide.auth.code.delegate.impl.ActionAuthCodeDelegate;
import ext.eclipse.ide.auth.code.delegate.impl.ResourceAuthCodeDelegate;

import java.util.HashMap;
import java.util.Map;

/**
 * Ȩ����ί�й������û��ṩ��Ŀ�����Դ\������ù��������һ�����õ�Ȩ����ί�У���Ȩ����ί�н���Ȩ���봦������
 * galaxy.ide.auth.code.handler.IAuthCodeHandler ���?
 * ������ύ��Ϣ�ᵼ��null���ء�
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
	 * �����Ŀ��Ͳ�������ɲ���ί��
	 * 
	 * @param projectName
	 *            ��Ŀ����Mars
	 * @param actionName
	 *            ��������delete
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
	 * �����Ŀ�����Դ���������Դί��
	 * 
	 * @param projectName
	 *            ��Ŀ����Mars
	 * @param resourceName
	 *            �������ͣ���twf
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
