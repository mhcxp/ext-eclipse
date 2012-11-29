package galaxy.sqlanlysis.core.model;

/**
 * ���ݿ��ģ��
 * 
 * @author caiyu
 * @date 2012-11-13
 */
public class TableModel extends SqlElementModel {
	private AliasModel alias;

	public TableModel(String name) {
		setName(name);
	}

	public AliasModel getAlias() {
		return alias;
	}

	public boolean hasAlias() {
		return alias != null && alias.getName() != null
				&& !alias.getName().trim().equals("");
	}

}
