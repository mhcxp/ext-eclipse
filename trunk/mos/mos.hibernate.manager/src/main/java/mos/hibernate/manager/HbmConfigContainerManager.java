package mos.hibernate.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;

/**
 * hibernate配置容器管理器
 * 
 * @author caiyu
 * @date 2012-10-11 上午10:19:25
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
	 * 根据databaseId获取配置单容器
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
	 * 根据databaseId创建配置单容器
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
	 * 判断配置单容器是否存在
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
	 * 销毁容器
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
