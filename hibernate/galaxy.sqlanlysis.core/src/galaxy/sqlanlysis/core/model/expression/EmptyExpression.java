package galaxy.sqlanlysis.core.model.expression;

/**
 * @author caiyu
 * @date 2012-11-30
 */
public class EmptyExpression extends AbstractEmptinessExpression {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4431889113059323344L;

	protected EmptyExpression(String propertyName, Object subquery) {
		super(propertyName, subquery);
	}

	protected boolean excludeEmpty() {
		return false;
	}

}
