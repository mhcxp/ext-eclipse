package galaxy.sqlanlysis.core.model;

/**
 * ��ѯ����ģ��
 * 
 * @author caiyu
 * @date 2012-11-21
 */
public class ConditionModel extends SqlElementModel {
	private ColumnModel column;
	private ValueModel value;

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
