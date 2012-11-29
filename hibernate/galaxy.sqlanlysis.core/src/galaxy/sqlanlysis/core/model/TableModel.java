package galaxy.sqlanlysis.core.model;

/**
 * 数据库表模型
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
