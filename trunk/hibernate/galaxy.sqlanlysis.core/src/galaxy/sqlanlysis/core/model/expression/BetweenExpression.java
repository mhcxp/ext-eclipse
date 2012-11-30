package galaxy.sqlanlysis.core.model.expression;

import galaxy.sqlanlysis.core.SqlBuffer;
import galaxy.sqlanlysis.core.dialect.Dialect;

/**
 * Between���ʽ
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
	 *            ������
	 * @param lo
	 *            ��ʼ����
	 * @param hi
	 *            ��β����
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
