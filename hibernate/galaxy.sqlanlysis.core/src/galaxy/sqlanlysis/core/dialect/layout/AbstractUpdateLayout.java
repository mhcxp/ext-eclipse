package galaxy.sqlanlysis.core.dialect.layout;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Ĭ�ϵ�Update����
 * 
 * @author caiyu
 * @date 2012-11-15
 */
public abstract class AbstractUpdateLayout implements IUpdateLayout {
	private Map<Object, Object> map = new ConcurrentHashMap<Object, Object>();

	@Override
	public Object getAdapter(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public String render(List<? extends Object> args, int key) {
		return null;
	}

}
