package galaxy.sqlanlysis.core.model.condition;

import galaxy.sqlanlysis.core.dialect.Dialect;

/**
 * �򵥵Ĳ�ѯ������ֻ����һ����ѯ����ģ��
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
			throw new ConditionSqlException("��ѯ����ģ�Ͳ���Ϊ��" + condition);
		return condition.render(dialect);
	}
}
