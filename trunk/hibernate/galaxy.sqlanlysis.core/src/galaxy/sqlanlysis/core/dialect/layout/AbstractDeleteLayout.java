package galaxy.sqlanlysis.core.dialect.layout;

import java.util.List;

/**
 * 
 * @author caiyu
 * @date 2012-11-16
 */
public abstract class AbstractDeleteLayout extends AbstractLayout {

	public String render(List<? extends Object> args, int key) {
		return null;
	}

	protected String headRender() {
		return "DELETE";
	}

	protected String tableRender(List<?> args) {
		return null;
	}
}
