package galaxy.sqlanlysis.core.model.expression;

import galaxy.sqlanlysis.core.SqlBuffer;
import galaxy.sqlanlysis.core.dialect.Dialect;
import galaxy.sqlanlysis.core.exception.IllegalExpressionException;
import galaxy.sqlanlysis.core.model.column.ColumnModel;
import galaxy.sqlanlysis.core.model.value.ValueModel;

/**
 * һ��ļ�ֵ�Ա��ʽ
 * 
 * @author caiyu
 * @date 2012-11-30
 */
public class SimpleExpression implements Expression {
	/**
	 * 
	 */
	private static final long serialVersionUID = -552726978589835537L;
	private final int columnKey;
	private final int valueKey;
	private final String operator;

	/**
	 * 
	 * @param columnKey
	 *            ���Թؼ���
	 * @param value
	 *            ֵ�ؼ���
	 * @param operator
	 *            ������
	 */
	public SimpleExpression(int columnKey, int valueKey, String operator) {
		this.columnKey = columnKey;
		this.valueKey = valueKey;
		this.operator = operator;
	}

	@Override
	public String toSqlString(Dialect dialect, ColumnModel column,
			ValueModel value) {
		if (column == null || value == null)
			throw new IllegalExpressionException("�ֶλ���ֵģ��Ϊnull");
		SqlBuffer buffer = new SqlBuffer();
		String columnSql =column.render(dialect,columnKey);
		buffer.append(columnSql);
		buffer.append(operator);
		buffer.append(String.valueOf(value));
		return buffer.getSql();
	}

	protected final String getOp() {
		return operator;
	}
}
