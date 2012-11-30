package galaxy.sqlanlysis.core.model.expression;

/**
 * @author caiyu
 * @date 2012-11-30
 */
public class NotEmptyExpression extends AbstractEmptinessExpression {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2618012020113277685L;

	protected NotEmptyExpression(String propertyName, Object subquery) {
		super(propertyName, subquery);
	}

	protected boolean excludeEmpty() {
		return true;
	}

}