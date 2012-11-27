package galaxy.sqlanlysis.core.dialect.layout;

import java.util.List;

import galaxy.sqlanlysis.core.SqlBuffer;
import galaxy.sqlanlysis.core.contant.AnlysisSqlKeys;

/**
 * Insert布局抽象类
 * 
 * @author caiyu
 * @date 2012-11-15
 */
public abstract class AbstractInsertLayout implements IInsertLayout,
		AnlysisSqlKeys {

	private AnylsisSqlSorter sorter = new AnylsisSqlSorter();

	protected AbstractInsertLayout() {
		sorter.put(HEAD);
		sorter.put(DISTINCT);
		sorter.put(LIMIT);
		sorter.put(COLUMNS);
		sorter.put(VALUES);
	}

	public AnylsisSqlSorter getSorter() {
		return sorter;
	}

	@Override
	public IAdapter getAdapter(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public String render(List<? extends Object> args, int key) {
		switch (key) {
		case AnlysisSqlKeys.COLUMNS:
			return columnRender(args);
		}
		return null;
	}

	/**
	 * 根据参数来交付字段列表完整SQL
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
}
