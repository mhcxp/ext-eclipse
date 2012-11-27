package galaxy.sqlanlysis.core.model;

/**
 * ×Ö¶Î
 * 
 * @author caiyu
 * @date 2012-11-13
 */
public class ColumnModel extends SqlElementModel {
	private AliasModel alias;
	private String name;

	public void setAlias(String alias) {
		if (alias == null) {
			this.alias = null;
			return;
		}
		if (this.alias == null) {
			this.alias = new AliasModel();
		}
		if (alias != null) {
			this.alias.setName(alias);
		}
	}

	public boolean hasAlias() {
		return alias != null;
	}

	public String getAlias() {
		return String.valueOf(alias);
	}
}
