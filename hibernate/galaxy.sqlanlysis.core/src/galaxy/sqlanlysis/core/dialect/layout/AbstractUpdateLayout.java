package galaxy.sqlanlysis.core.dialect.layout;

import galaxy.sqlanlysis.core.SqlBuffer;
import galaxy.sqlanlysis.core.contant.AnlysisSqlKeys;
import galaxy.sqlanlysis.core.exception.AnlysisSqlException;
import galaxy.sqlanlysis.core.model.TableModel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的Update布局
 * 
 * @author caiyu
 * @date 2012-11-15
 */
public abstract class AbstractUpdateLayout extends AbstractLayout {
	private Map<Object, Object> map = new ConcurrentHashMap<Object, Object>();

	public String render(List<? extends Object> args, int key) {
		switch (key) {
		case AnlysisSqlKeys.SET:
			return setsRender(args);
		}
		return super.render(args, key);
	}

	protected String setsRender(List<? extends Object> args) {
		// TODO Auto-generated method stub
		if (args == null || args.size() == 0) {
			throw new UpdateException("UPDATE语句必须修改一到多个字段");
		}
		return null;
	}

	protected String headRender() {
		return "UPDATE";
	}

	protected String tableRender(List<?> args) {
		if (args == null || args.size() != 1) {
			throw new UpdateException("UPDATE语句必须具备且仅具备一个目标表");
		}
		Object arg = args.get(0);
		if (arg instanceof TableModel) {
			TableModel table = (TableModel) arg;
			SqlBuffer buffer = new SqlBuffer();
			buffer.append(table.getName());
			if (table.hasAlias())
				buffer.append(table.getAlias().getName());
			return buffer.getSql();
		} else
			throw new UpdateException(
					"UPDATE语句需要galaxy.sqlanlysis.core.model.TableModel对象："
							+ arg.getClass());
	}

	private class UpdateException extends AnlysisSqlException {
		private UpdateException(String msg) {
			super(msg);
		}
	}
}
