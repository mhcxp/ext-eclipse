package galaxy.sqlanlysis.core.dialect.layout;

import galaxy.sqlanlysis.core.contant.AnlysisSqlKeys;

import java.util.List;

/**
 * ³éÏó²¼¾Ö
 * 
 * @author caiyu
 * @date 2012-11-28
 */
public abstract class AbstractLayout implements IAnlysisSqlLayout {
	protected AnylsisSqlSorter sorter;

	public AnylsisSqlSorter getSorter() {
		return sorter;
	}

	public String render(List<? extends Object> args, int key) {
		switch (key) {
		case AnlysisSqlKeys.HEAD:
			return headRender();
		case AnlysisSqlKeys.TARGET_TABLE:
			return tableRender(args);
		}
		return null;
	}

	protected abstract String headRender();

	protected abstract String tableRender(List<?> args);
}
