package galaxy.sqlanlysis.core.dialect.layout;

import java.util.List;

import galaxy.sqlanlysis.core.SqlBuffer;
import galaxy.sqlanlysis.core.contant.AnlysisSqlKeys;
import galaxy.sqlanlysis.core.model.TableModel;

/**
 * Insert���ֳ�����
 * 
 * @author caiyu
 * @date 2012-11-15
 */
public abstract class AbstractInsertLayout extends AbstractLayout {

	private AnylsisSqlSorter sorter = new AnylsisSqlSorter();

	protected AbstractInsertLayout() {
		sorter.put(AnlysisSqlKeys.HEAD);
		sorter.put(AnlysisSqlKeys.DISTINCT);
		sorter.put(AnlysisSqlKeys.LIMIT);
		sorter.put(AnlysisSqlKeys.COLUMNS);
		sorter.put(AnlysisSqlKeys.VALUES);
	}

	public AnylsisSqlSorter getSorter() {
		return sorter;
	}

	public String render(List<? extends Object> args, int key) {
		switch (key) {
		case AnlysisSqlKeys.COLUMNS:
			return columnRender(args);
		}
		return super.render(args, key);
	}

	protected String tableRender(List<?> args) {
		if (args == null || args.size() != 1) {
			throw new InsertException("INSERT������߱��ҽ��߱�һ��Table����");
		}
		Object obj = args.get(0);
		if (obj instanceof TableModel) {
			TableModel table = (TableModel) obj;
			SqlBuffer buffer = new SqlBuffer();
			buffer.append("INTO");
			buffer.append(table.getName());
			return buffer.getSql();
		} else
			throw new InsertException(
					"INSERT���Table����ģ���쳣��Ҫ��galaxy.sqlanlysis.core.model.TableModelģ�ͣ�"
							+ obj.getClass());
	}

	protected String headRender() {
		return "INSERT";
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
		buffer.append("(");
		int len = args.size();
		for (int i = 0; i < len; i++) {
			String column = (String) args.get(i);
			if (i == len - 1) {
				buffer.append(column);
			} else
				buffer.appendWithComma(column);
		}
		buffer.append(")");
		return buffer.getSql();
	}

	private class InsertException extends ArrayStoreException {
		private static final long serialVersionUID = 1L;

		InsertException(String msg) {
			super(msg);
		}
	}
}
