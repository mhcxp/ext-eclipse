package galaxy.sqlanlysis.core.model.condition;

import galaxy.sqlanlysis.core.dialect.Dialect;
import galaxy.sqlanlysis.core.exception.AnlysisSqlException;
import galaxy.sqlanlysis.core.model.SqlElementModel;
import galaxy.sqlanlysis.core.model.column.ColumnModel;
import galaxy.sqlanlysis.core.model.expression.Expression;
import galaxy.sqlanlysis.core.model.value.ValueModel;

/**
 * ��ѯ����ģ��
 * 
 * @author caiyu
 * @date 2012-11-21
 */
public class ConditionModel extends SqlElementModel {
	private ColumnModel column;
	private ValueModel value;
	private Expression expression;

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public ColumnModel getColumn() {
		return column;
	}

	public void setColumn(ColumnModel column) {
		this.column = column;
	}

	public ValueModel getValue() {
		return value;
	}

	public void setValue(ValueModel value) {
		this.value = value;
	}

	/**
	 * ���ݷ��Խ�������
	 * 
	 * @param dialect
	 * @return
	 */
	public String render(Dialect dialect) {
		if (dialect == null || expression == null) {
			throw new AnlysisSqlException("�����쳣");
		}
		return expression.toSqlString(dialect, column, value);
	}
}
