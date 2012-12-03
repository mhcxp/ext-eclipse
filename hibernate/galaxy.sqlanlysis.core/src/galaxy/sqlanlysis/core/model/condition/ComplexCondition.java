package galaxy.sqlanlysis.core.model.condition;

import galaxy.sqlanlysis.core.SqlBuffer;
import galaxy.sqlanlysis.core.dialect.Dialect;

/**
 * 复杂的查询条件，会包含多个查询条件以及“OR","AND"关系式
 * 
 * @author caiyu
 * @date 2012-12-3
 */
public class ComplexCondition extends ConditionModelGroup {
	private ConditionModelGroup first;
	private ConditionModelGroup second;
	private String relationship;

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		if (relationship == null)
			throw new ConditionSqlException("SQL查询条件关系不能为 " + relationship);
		relationship = relationship.trim().toUpperCase();
		if (!"AND".equals(relationship) && !"OR".equals(relationship))
			throw new ConditionSqlException("SQL查询条件关系不能为 " + relationship);
		this.relationship = relationship;
	}

	public ConditionModelGroup getFirst() {
		return first;
	}

	public void setFirst(ConditionModelGroup first) {
		this.first = first;
	}

	public ConditionModelGroup getSecond() {
		return second;
	}

	public void setSecond(ConditionModelGroup second) {
		this.second = second;
	}

	@Override
	public String render(Dialect dialect) {
		SqlBuffer buffer = new SqlBuffer();
		if (!isRoot)
			buffer.append("(");
		buffer.append(first.render(dialect));
		buffer.append(relationship);
		buffer.append(second.render(dialect));
		if (!isRoot)
			buffer.append(")");
		isRoot = false;
		return buffer.toString();
	}
}
