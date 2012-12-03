package galaxy.sqlanlysis.core.model.condition;

import galaxy.sqlanlysis.core.dialect.Dialect;
import galaxy.sqlanlysis.core.exception.AnlysisSqlException;
import galaxy.sqlanlysis.core.model.SqlElementModel;

/**
 * 查询条件集合
 * 
 * @author caiyu
 * @date 2012-11-28
 */
public abstract class ConditionModelGroup extends SqlElementModel {
	protected boolean isRoot = true;

	/**
	 * 根据方言交付内容
	 * 
	 * @param dialect
	 *            方言
	 * @return SQL文本
	 */
	public abstract String render(Dialect dialect);

	protected class ConditionSqlException extends AnlysisSqlException {
		private static final long serialVersionUID = 1L;

		protected ConditionSqlException(String msg) {
			super(msg);
		}
	}
}
