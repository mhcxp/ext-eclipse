package galaxy.sqlanlysis.core.model.expression;

import galaxy.sqlanlysis.core.dialect.Dialect;
import galaxy.sqlanlysis.core.helper.StringHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caiyu
 * @date 2012-11-30
 */
public class InExpression implements Expression {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8928429578716894666L;

	private final String propertyName;
	private final Object[] values;

	public InExpression(String propertyName, Object[] values) {
		this.propertyName = propertyName;
		this.values = values;
	}

	@Override
	public String toSqlString(Dialect dialect) {
		// TODO Auto-generated method stub
		int size = values.length;
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < size; i++) {
			Object obj = values[i];
			if (obj instanceof String) {
				list.add((String) obj);
			}
		}
		String params = StringHelper.join(" , ", list.iterator());
		return this.propertyName + " in (" + params + ')';
	}
}
