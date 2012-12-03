package galaxy.sqlanlysis.core.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 可分析SQL模型抽象类
 * 
 * @author caiyu
 * @date 2012-11-14
 */
public abstract class AbstractAnlysisSqlModel implements IAnlysisSqlModel {
	private Map<Integer, Object> elements = new ConcurrentHashMap<Integer, Object>();

	@Override
	public Object getElement(Object elementKey) {
		// TODO Auto-generated method stub
		return elements.get(elementKey);
	}

	@Override
	public void setElement(int elementKey, Object elementValue) {
		elements.put(elementKey, elementValue);
	}

}
