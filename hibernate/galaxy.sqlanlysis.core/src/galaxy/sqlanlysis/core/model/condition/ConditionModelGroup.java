package galaxy.sqlanlysis.core.model.condition;

import galaxy.sqlanlysis.core.dialect.Dialect;
import galaxy.sqlanlysis.core.exception.AnlysisSqlException;
import galaxy.sqlanlysis.core.model.SqlElementModel;

/**
 * ��ѯ��������
 * 
 * @author caiyu
 * @date 2012-11-28
 */
public abstract class ConditionModelGroup extends SqlElementModel {
	protected boolean isRoot = true;

	/**
	 * ���ݷ��Խ�������
	 * 
	 * @param dialect
	 *            ����
	 * @return SQL�ı�
	 */
	public abstract String render(Dialect dialect);

	protected class ConditionSqlException extends AnlysisSqlException {
		private static final long serialVersionUID = 1L;

		protected ConditionSqlException(String msg) {
			super(msg);
		}
	}
}
