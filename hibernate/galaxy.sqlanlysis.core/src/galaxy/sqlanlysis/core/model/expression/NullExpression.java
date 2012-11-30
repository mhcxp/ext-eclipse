package galaxy.sqlanlysis.core.model.expression;

import galaxy.sqlanlysis.core.dialect.Dialect;

/**
 * 
 * @author caiyu
 * @date 2012-11-30
 */
public class NullExpression implements Expression {

	/**
	 * 
	 */
	private static final long serialVersionUID = -604539072428780528L;

	private final String propertyName;

	public NullExpression(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public String toSqlString(Dialect dialect) {
		return this.propertyName + " IS NULL";
	}

}
