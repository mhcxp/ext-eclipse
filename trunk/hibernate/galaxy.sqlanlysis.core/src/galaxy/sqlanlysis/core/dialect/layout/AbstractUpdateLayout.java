package galaxy.sqlanlysis.core.dialect.layout;

import galaxy.sqlanlysis.core.SqlBuffer;
import galaxy.sqlanlysis.core.contant.AnlysisSqlKeys;
import galaxy.sqlanlysis.core.exception.AnlysisSqlException;
import galaxy.sqlanlysis.core.model.TableModel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Ĭ�ϵ�Update����
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
			throw new UpdateException("UPDATE�������޸�һ������ֶ�");
		}
		return null;
	}

	protected String headRender() {
		return "UPDATE";
	}

	protected String tableRender(List<?> args) {
		if (args == null || args.size() != 1) {
			throw new UpdateException("UPDATE������߱��ҽ��߱�һ��Ŀ���");
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
					"UPDATE�����Ҫgalaxy.sqlanlysis.core.model.TableModel����"
							+ arg.getClass());
	}

	private class UpdateException extends AnlysisSqlException {
		private UpdateException(String msg) {
			super(msg);
		}
	}
}
