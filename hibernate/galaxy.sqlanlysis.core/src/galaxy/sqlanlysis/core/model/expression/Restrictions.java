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
	 * @param columnKey
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression eq(int columnKey, int valueKey) {
		return new SimpleExpression(columnKey, valueKey, "=");
	}

	/**
	 * Apply a "not equal" constraint to the named property
	 * 
	 * @param columnKey
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression ne(int columnKey, int valueKey) {
		return new SimpleExpression(columnKey, valueKey, "<>");
	}

	/**
	 * Apply a "like" constraint to the named property
	 * 
	 * @param columnKey
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression like(int columnKey, int valueKey) {
		return new SimpleExpression(columnKey, valueKey, " like ");
	}

	/**
	 * Apply a "greater than" constraint to the named property
	 * 
	 * @param columnKey
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression gt(int columnKey, int valueKey) {
		return new SimpleExpression(columnKey, valueKey, ">");
	}

	/**
	 * Apply a "less than" constraint to the named property
	 * 
	 * @param columnKey
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression lt(int columnKey, int valueKey) {
		return new SimpleExpression(columnKey, valueKey, "<");
	}

	/**
	 * Apply a "less than or equal" constraint to the named property
	 * 
	 * @param columnKey
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression le(int columnKey, int valueKey) {
		return new SimpleExpression(columnKey, valueKey, "<=");
	}

	/**
	 * Apply a "greater than or equal" constraint to the named property
	 * 
	 * @param columnKey
	 * @param value
	 * @return Criterion
	 */
	public static SimpleExpression ge(int columnKey, int valueKey) {
		return new SimpleExpression(columnKey, valueKey, ">=");
	}

	/**
	 * Apply a "between" constraint to the named property
	 * 
	 * @param columnKey
	 * @param lo
	 *            value
	 * @param hi
	 *            value
	 * @return Criterion
	 */
	public static Expression between(String columnKey, Object lo, Object hi) {
		return new BetweenExpression(columnKey, lo, hi);
	}

	/**
	 * Apply an "in" constraint to the named property
	 * 
	 * @param columnKey
	 * @param values
	 * @return Criterion
	 */
	public static Expression in(String columnKey, Object[] values) {
		return new InExpression(columnKey, values);
	}

	/**
	 * Apply an "in" constraint to the named property
	 * 
	 * @param columnKey
	 * @param values
	 * @return Criterion
	 */
	public static Expression in(String columnKey, Collection values) {
		return new InExpression(columnKey, values.toArray());
	}

	/**
	 * Apply an "is null" constraint to the named property
	 * 
	 * @return Criterion
	 */
	public static Expression isNull(String columnKey) {
		return new NullExpression(columnKey);
	}

	/**
	 * Apply an "is not null" constraint to the named property
	 * 
	 * @return Criterion
	 */
	public static Expression isNotNull(String columnKey) {
		return new NotNullExpression(columnKey);
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
	 * @param columnKey
	 * @param subquery
	 * @return
	 */
	public static Expression isEmpty(String columnKey, String subquery) {
		return new EmptyExpression(columnKey, subquery);
	}

	/**
	 * NOT EXISTS表达式
	 * 
	 * @param columnKey
	 * @param subquery
	 * @return
	 */
	public static Expression isNotEmpty(String columnKey, String subquery) {
		return new NotEmptyExpression(columnKey, subquery);
	}

}
