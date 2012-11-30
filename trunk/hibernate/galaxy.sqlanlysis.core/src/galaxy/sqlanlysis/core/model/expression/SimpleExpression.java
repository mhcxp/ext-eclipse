package galaxy.sqlanlysis.core.model.expression;

import galaxy.sqlanlysis.core.SqlBuffer;
import galaxy.sqlanlysis.core.dialect.Dialect;

/**
 * 一般的键值对表达式
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
	 *            属性名
	 * @param value
	 *            值
	 * @param operator
	 *            操作符
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
