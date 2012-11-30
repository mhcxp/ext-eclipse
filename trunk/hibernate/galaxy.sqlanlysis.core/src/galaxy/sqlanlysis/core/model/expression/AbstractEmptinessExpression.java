package galaxy.sqlanlysis.core.model.expression;

import galaxy.sqlanlysis.core.dialect.Dialect;
import galaxy.sqlanlysis.core.model.SelectModel;

/**
 * @author caiyu
 * @date 2012-11-30
 */
public abstract class AbstractEmptinessExpression implements Expression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1594445226238423824L;
	protected final String propertyName;
	protected final Object subquery;

	protected AbstractEmptinessExpression(String propertyName, Object subquery) {
		this.propertyName = propertyName;
		this.subquery = subquery;
	}

	protected abstract boolean excludeEmpty();

	public final String toSqlString(Dialect dialect) {
		String innerSelect = null;
		if (subquery instanceof SelectModel) {
			// TODO
			try {
				Dialect d = dialect.getClass().newInstance();
				d.registerAnlysisSqlModel((SelectModel) subquery);
				innerSelect = d.getSqlString();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} else
			innerSelect = (String) subquery;

		return excludeEmpty() ? "exists " + innerSelect : "not exists "
				+ innerSelect;
	}

	public final String toString() {
		return propertyName + (excludeEmpty() ? " is not empty" : " is empty");
	}

}
