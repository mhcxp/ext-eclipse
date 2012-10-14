package mos.hibernate.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;

/**
 * hibernate��������������
 * 
 * @author caiyu
 * @date 2012-10-11 ����10:19:25
 */

public final class HbmConfigContainerManager {
	private static HbmConfigContainerManager INSTANCE;
	private Map<String, HbmConfigContainer> containerPool;

	public static HbmConfigContainerManager getInstance() {
		if (INSTANCE == null)
			INSTANCE = new HbmConfigContainerManager();
		return INSTANCE;
	}

	private HbmConfigContainerManager() {
		init();
	}

	private void init() {
		containerPool = Collections
				.synchronizedMap(new HashMap<String, HbmConfigContainer>());
	}

	/**
	 * ����databaseId��ȡ���õ�����
	 * 
	 * @param sessionFactoryId
	 * @return
	 */
	public synchronized HbmConfigContainer getHbmConfigContainer(
			String sessionFactoryId) {
		if (containerPool.containsKey(sessionFactoryId))
			return containerPool.get(sessionFactoryId);
		else
			return null;
		// HbmConfigContainer container = new
		// HbmConfigContainer(sessionFactoryId);
		// containerPool.put(sessionFactoryId, container);
		// return container;
	}

	/**
	 * ����databaseId�������õ�����
	 * 
	 * @param sessionFactoryId
	 * @return
	 */
	public synchronized HbmConfigContainer createHbmConfigContainer(
			String sessionFactoryId) {
		if (containerPool.containsKey(sessionFactoryId))
			return containerPool.get(sessionFactoryId);
		HbmConfigContainer container = new HbmConfigContainer(sessionFactoryId);
		containerPool.put(sessionFactoryId, container);
		return container;
	}

	/**
	 * �ж����õ������Ƿ����
	 * 
	 * @param sessionFactoryId
	 * @return
	 */
	public synchronized boolean hasHbmConfigContainer(String sessionFactoryId) {
		return containerPool.containsKey(sessionFactoryId);
	}

	public synchronized Map<String, HbmConfigContainer> getAllHbmConfigContainer() {
		if (containerPool == null)
			return null;
		return new HashMap<String, HbmConfigContainer>(containerPool);
	}

	public SessionFactory getSessionFactory(String sessionFactoryId) {
		HbmConfigContainer container = this.containerPool.get(sessionFactoryId);
		if (container == null)
			throw new IllegalArgumentException(
					"Cannot get SessionFactory, SessionFactory with id ["
							+ sessionFactoryId + "] not registered");
		return container.getSessionFactory();
	}

	/**
	 * ��������
	 * 
	 * @param sessionFactoryId
	 */
	public synchronized void destroy(String sessionFactoryId) {
		if (containerPool.containsKey(sessionFactoryId)) {
			HbmConfigContainer container = containerPool
					.remove(sessionFactoryId);
			container.dispose();
		}
	}

}
