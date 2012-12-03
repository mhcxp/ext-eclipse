package galaxy.sqlanlysis.core.model.condition;

import galaxy.sqlanlysis.core.dialect.Dialect;

/**
 * 简单的查询条件，只包含一个查询条件模型
 * 
 * @author caiyu
 * @date 2012-12-3
 */
public class SimpleCondition extends ConditionModelGroup {
	private ConditionModel condition;

	public ConditionModel getCondition() {
		return condition;
	}

	public void setCondition(ConditionModel condition) {
		this.condition = condition;
	}

	@Override
	public String render(Dialect dialect) {
		if (condition == null)
			throw new ConditionSqlException("查询条件模型不能为：" + condition);
		return condition.render(dialect);
	}
}
