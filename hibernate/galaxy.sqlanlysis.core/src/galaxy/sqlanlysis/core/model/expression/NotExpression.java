package galaxy.sqlanlysis.core.model.expression;

import galaxy.sqlanlysis.core.dialect.Dialect;
import galaxy.sqlanlysis.core.dialect.MySQLDialect;
import galaxy.sqlanlysis.core.model.column.ColumnModel;
import galaxy.sqlanlysis.core.model.value.ValueModel;

/**
 * NOT±Ì¥Ô Ω
 * 
 * @author caiyu
 * @date 2012-11-30
 */
public class NotExpression implements Expression {
	/**
	 * 
	 */
	private static final long serialVersionUID = -794828581024609752L;
	private final Expression expression;

	public NotExpression(Expression expression) {
		this.expression = expression;
	}

	@Override
	public String toSqlString(Dialect dialect, ColumnModel column,
			ValueModel value) {
		if (dialect instanceof MySQLDialect) {
			return "not (" + expression.toSqlString(dialect, column, value) + ')';
		} else {
			return "not " + expression.toSqlString(dialect, column, value);
		}
	}

}
