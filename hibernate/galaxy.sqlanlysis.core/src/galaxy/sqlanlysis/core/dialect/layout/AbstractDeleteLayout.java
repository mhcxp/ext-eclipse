package galaxy.sqlanlysis.core.dialect.layout;

import java.util.List;

/**
 * 
 * @author caiyu
 * @date 2012-11-16
 */
public abstract class AbstractDeleteLayout implements IDeleteLayout {

	@Override
	public IAdapter getAdapter(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public String render(List<? extends Object> args, int key) {
		return null;
	}

}
