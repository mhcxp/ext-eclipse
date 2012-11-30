package galaxy.sqlanlysis.core.model;

import galaxy.sqlanlysis.core.exception.AnlysisSqlException;

/**
 * 查询条件集合
 * 
 * @author caiyu
 * @date 2012-11-28
 */
public class ConditionModelGroup extends SqlElementModel {
	private ConditionModelGroup first;
	private ConditionModelGroup second;
	private String relationship;

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		if (relationship == null)
			throw new ConditionSqlException("SQL查询条件关系不能为" + relationship);
		relationship = relationship.trim().toUpperCase();
		if (!"AND".equals(relationship) && !"OR".equals(relationship))
			throw new ConditionSqlException("SQL查询条件关系不能为" + relationship);
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

	private class ConditionSqlException extends AnlysisSqlException {
		private static final long serialVersionUID = 1L;

		private ConditionSqlException(String msg) {
			super(msg);
		}
	}
}
