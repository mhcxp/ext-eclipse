package mos.hibernate.manager;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
	 * @param sessionFatoryId
	 * @return
	 */
	public synchronized HbmConfigContainer getHbmConfigContainer(
			String sessionFatoryId) {
		if (containerPool.containsKey(sessionFatoryId))
			return containerPool.get(sessionFatoryId);
		HbmConfigContainer container = new HbmConfigContainer(sessionFatoryId);
		containerPool.put(sessionFatoryId, container);
		return container;
	}

	/**
	 * �ж����õ������Ƿ����
	 * 
	 * @param sessionFatoryId
	 * @return
	 */
	public synchronized boolean hasHbmConfigContainer(String sessionFatoryId) {
		return containerPool.containsKey(sessionFatoryId);
	}

	public synchronized Map<String, HbmConfigContainer> getAllHbmConfigContainer() {
		if (containerPool == null)
			return null;
		return new HashMap<String, HbmConfigContainer>(containerPool);
	}

	/**
	 * ��������
	 * 
	 * @param sessionFatoryId
	 */
	public synchronized void destroy(String sessionFatoryId) {
		if (containerPool.containsKey(sessionFatoryId)) {
			HbmConfigContainer container = containerPool
					.remove(sessionFatoryId);
			container.dispose();
		}
	}

}
