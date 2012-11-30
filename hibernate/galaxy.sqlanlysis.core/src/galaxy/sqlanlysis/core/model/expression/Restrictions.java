//$Id: Restrictions.java 7844 2005-08-11 07:26:26Z oneovthafew $
package galaxy.sqlanlysis.core.model.expression;

import java.util.Collection;

/**
 * 表达式操作类
 * 
 * @author caiyu
 * @date 2012-11-30
 */
public class Restrictions {

	Restrictions() {
		// cannot be instantiated
	}

	/**
	 * Apply an "equal" constraint to the named property
	 * 
	 * @param propertyName
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression eq(String propertyName, Object value) {
		return new SimpleExpression(propertyName, value, "=");
	}

	/**
	 * Apply a "not equal" constraint to the named property
	 * 
	 * @param propertyName
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression ne(String propertyName, Object value) {
		return new SimpleExpression(propertyName, value, "<>");
	}

	/**
	 * Apply a "like" constraint to the named property
	 * 
	 * @param propertyName
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression like(String propertyName, Object value) {
		return new SimpleExpression(propertyName, value, " like ");
	}

	/**
	 * Apply a "greater than" constraint to the named property
	 * 
	 * @param propertyName
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression gt(String propertyName, Object value) {
		return new SimpleExpression(propertyName, value, ">");
	}

	/**
	 * Apply a "less than" constraint to the named property
	 * 
	 * @param propertyName
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression lt(String propertyName, Object value) {
		return new SimpleExpression(propertyName, value, "<");
	}

	/**
	 * Apply a "less than or equal" constraint to the named property
	 * 
	 * @param propertyName
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression le(String propertyName, Object value) {
		return new SimpleExpression(propertyName, value, "<=");
	}

	/**
	 * Apply a "greater than or equal" constraint to the named property
	 * 
	 * @param propertyName
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression ge(String propertyName, Object value) {
		return new SimpleExpression(propertyName, value, ">=");
	}

	/**
	 * Apply a "between" constraint to the named property
	 * 
	 * @param propertyName
	 * @param lo
	 *            value
	 * @param hi
	 *            value
	 * @return Criterion
	 */
	public static Expression between(String propertyName, Object lo, Object hi) {
		return new BetweenExpression(propertyName, lo, hi);
	}

	/**
	 * Apply an "in" constraint to the named property
	 * 
	 * @param propertyName
	 * @param values
	 * @return Criterion
	 */
	public static Expression in(String propertyName, Object[] values) {
		return new InExpression(propertyName, values);
	}

	/**
	 * Apply an "in" constraint to the named property
	 * 
	 * @param propertyName
	 * @param values
	 * @return Criterion
	 */
	public static Expression in(String propertyName, Collection values) {
		return new InExpression(propertyName, values.toArray());
	}

	/**
	 * Apply an "is null" constraint to the named property
	 * 
	 * @return Criterion
	 */
	public static Expression isNull(String propertyName) {
		return new NullExpression(propertyName);
	}

	/**
	 * Apply an "is not null" constraint to the named property
	 * 
	 * @return Criterion
	 */
	public static Expression isNotNull(String propertyName) {
		return new NotNullExpression(propertyName);
	}

	/**
	 * Return the negation of an expression
	 * 
	 * @param expression
	 * @return Criterion
	 */
	public static Expression not(Expression expression) {
		return new NotExpression(expression);
	}

	/**
	 * EXISTS表达式
	 * 
	 * @param propertyName
	 * @param subquery
	 * @return
	 */
	public static Expression isEmpty(String propertyName, String subquery) {
		return new EmptyExpression(propertyName, subquery);
	}

	/**
	 * NOT EXISTS表达式
	 * 
	 * @param propertyName
	 * @param subquery
	 * @return
	 */
	public static Expression isNotEmpty(String propertyName, String subquery) {
		return new NotEmptyExpression(propertyName, subquery);
	}

}
