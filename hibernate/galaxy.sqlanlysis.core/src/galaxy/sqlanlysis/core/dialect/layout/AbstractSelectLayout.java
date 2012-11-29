package galaxy.sqlanlysis.core.dialect.layout;

import galaxy.sqlanlysis.core.SqlBuffer;
import galaxy.sqlanlysis.core.contant.AnlysisSqlKeys;
import galaxy.sqlanlysis.core.model.TableModel;

import java.util.List;

public abstract class AbstractSelectLayout extends AbstractLayout {

	public String render(List<? extends Object> args, int key) {
		switch (key) {
		case AnlysisSqlKeys.COLUMNS:
			return columnRender(args);
		case AnlysisSqlKeys.WHERE:
			return conditionRender(args);
		}
		return super.render(args, key);
	}

	protected String conditionRender(List<? extends Object> args) {
		// TODO Auto-generated method stub
		return null;
	}

	protected String tableRender(List<? extends Object> args) {
		SqlBuffer buffer = new SqlBuffer();
		buffer.append("FORM");
		int size = args.size();
		for (int i = 0; i < size; i++) {
			Object obj = args.get(i);
			if (obj instanceof TableModel) {
				TableModel table = (TableModel) obj;
				buffer.append(table.getName());
				if (table.hasAlias()) {
					buffer.append(table.getAlias().getName());
				}
				if (i != size - 1)
					buffer.append(",");
			} else
				throw new SelectException(
						"INSERT���Table����ģ���쳣��Ҫ��galaxy.sqlanlysis.core.model.TableModelģ�ͣ�"
								+ obj.getClass());
		}
		return buffer.getSql();
	}

	protected String headRender() {
		return "SELECT";
	}

	/**
	 * ���ݲ����������ֶ��б�����SQL
	 * 
	 * @param args
	 * @return
	 */
	protected String columnRender(List<? extends Object> args) {
		// TODO Auto-generated method stub
		if (args == null || args.size() == 0)
			return null;
		SqlBuffer buffer = new SqlBuffer();
		int len = args.size();
		for (int i = 0; i < len; i++) {
			String column = (String) args.get(i);
			if (i == len - 1) {
				buffer.append(column);
			} else
				buffer.appendWithComma(column);
		}
		return buffer.getSql();
	}

	private class SelectException extends ArrayStoreException {
		private static final long serialVersionUID = 1L;

		private SelectException(String msg) {
			super(msg);
		}
	}

}
