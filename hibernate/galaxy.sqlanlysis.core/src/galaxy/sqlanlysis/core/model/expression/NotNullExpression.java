package galaxy.sqlanlysis.core.model.expression;

import galaxy.sqlanlysis.core.dialect.Dialect;
import galaxy.sqlanlysis.core.model.column.ColumnModel;
import galaxy.sqlanlysis.core.model.value.ValueModel;

/**
 * @author caiyu
 * @date 2012-11-30
 */
public class NotNullExpression implements Expression {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4943204919900956598L;
	private final String propertyName;

	public NotNullExpression(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public String toSqlString(Dialect dialect,ColumnModel column,ValueModel value) {
		return this.propertyName + " IS NOT NULL";
	}

}
