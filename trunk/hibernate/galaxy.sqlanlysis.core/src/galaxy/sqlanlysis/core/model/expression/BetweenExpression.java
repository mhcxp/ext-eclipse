package galaxy.sqlanlysis.core.model.expression;

import galaxy.sqlanlysis.core.SqlBuffer;
import galaxy.sqlanlysis.core.dialect.Dialect;

/**
 * Between表达式
 * 
 * @author caiyu
 * @date ${date}
 */
public class BetweenExpression implements Expression {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4826048335267140275L;
	private final String propertyName;
	private final Object lo;
	private final Object hi;

	/**
	 * 
	 * @param propertyName
	 *            属性名
	 * @param lo
	 *            起始参数
	 * @param hi
	 *            结尾参数
	 */
	public BetweenExpression(String propertyName, Object lo, Object hi) {
		this.propertyName = propertyName;
		this.lo = lo;
		this.hi = hi;
	}

	@Override
	public String toSqlString(Dialect dialect) {
		SqlBuffer buffer = new SqlBuffer();
		buffer.append(propertyName);
		buffer.append("BETWEEN");
		buffer.append(String.valueOf(lo));
		buffer.append("AND");
		buffer.append(String.valueOf(hi));
		return buffer.getSql();
	}

}
