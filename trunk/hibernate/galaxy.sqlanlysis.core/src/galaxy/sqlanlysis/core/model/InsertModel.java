package galaxy.sqlanlysis.core.model;

import galaxy.sqlanlysis.core.contant.AnlysisSqlKeys;

/**
 * 一个完整的Inert语句模型
 * 
 * @author caiyu
 * @date 2012-11-13
 */
public class InsertModel extends AnlysisStatementModel {
	private TableModel table;
	private ColumnModel[] columnList;
	private ValueModel[] valueModel;

	public InsertModel() {
		setElement(AnlysisSqlKeys.HEAD, "INSERT");
	}
}
