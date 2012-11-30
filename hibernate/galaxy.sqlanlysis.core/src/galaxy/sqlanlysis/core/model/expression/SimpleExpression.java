package galaxy.sqlanlysis.core.model.expression;

import galaxy.sqlanlysis.core.SqlBuffer;
import galaxy.sqlanlysis.core.dialect.Dialect;

/**
 * һ��ļ�ֵ�Ա��ʽ
 * 
 * @author caiyu
 * @date 2012-11-30
 */
public class SimpleExpression implements Expression {
	private final String propertyName;
	private final Object value;
	private final String operator;

	/**
	 * 
	 * @param propertyName
	 *            ������
	 * @param value
	 *            ֵ
	 * @param operator
	 *            ������
	 */
	public SimpleExpression(String propertyName, Object value, String operator) {
		this.propertyName = propertyName;
		this.value = value;
		this.operator = operator;
	}

	@Override
	public String toSqlString(Dialect dialect) {
		SqlBuffer buffer = new SqlBuffer();
		buffer.append(propertyName);
		buffer.append(operator);
		buffer.append(String.valueOf(value));
		return buffer.getSql();
	}

	protected final String getOp() {
		return operator;
	}
}
