package galaxy.sqlanlysis.core.model;

import galaxy.sqlanlysis.core.model.expression.Expression;

/**
 * 查询条件模型
 * 
 * @author caiyu
 * @date 2012-11-21
 */
public class ConditionModel extends SqlElementModel {
	private ColumnModel column;
	private ValueModel value;
	private Expression expression;

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	public ColumnModel getColumn() {
		return column;
	}

	public void setColumn(ColumnModel column) {
		this.column = column;
	}

	public ValueModel getValue() {
		return value;
	}

	public void setValue(ValueModel value) {
		this.value = value;
	}
}
