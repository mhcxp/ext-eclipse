package galaxy.sqlanlysis.core.model;

import galaxy.sqlanlysis.core.contant.AnlysisSqlKeys;
import galaxy.sqlanlysis.core.exception.IllegalElementKeyException;

import java.util.ArrayList;
import java.util.List;

/**
 * Select查询语句模型
 * 
 * @author caiyu
 * @date 2012-11-13
 */
public class SelectModel extends AnlysisStatementModel {
	private List<Object> enableKey = new ArrayList<Object>();

	@Override
	protected void init() {
		enableKey.add(AnlysisSqlKeys.HEAD);
		enableKey.add(AnlysisSqlKeys.DISTINCT);
		enableKey.add(AnlysisSqlKeys.TARGET_TABLE);
		enableKey.add(AnlysisSqlKeys.COLUMNS);
		enableKey.add(AnlysisSqlKeys.ORDER_BY);
		enableKey.add(AnlysisSqlKeys.GROUP_BY);
		enableKey.add(AnlysisSqlKeys.LIMIT);
	}

	@Override
	public Object getElement(Object elementKey) {
		if (!enableKey.contains(elementKey))
			throw new IllegalElementKeyException("SELECT语句模型不具备该关键字KEY："
					+ elementKey);
		return super.getElement(elementKey);
	}

}
