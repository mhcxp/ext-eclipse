package galaxy.sqlanlysis.core.model.column;

import galaxy.sqlanlysis.core.dialect.Dialect;
import galaxy.sqlanlysis.core.model.AliasModel;
import galaxy.sqlanlysis.core.model.SqlElementModel;

/**
 * 字段
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

	/**
	 * 根据方言和Key内容来交付内容
	 * 
	 * @param dialect
	 * @param columnKey
	 * @return
	 */
	public String render(Dialect dialect, int columnKey) {
		// TODO Auto-generated method stub
		switch (columnKey) {
		case NAME:
			return getName();
		case NAME | ALIAS:
			return getName() + dialect.getAsToken() + getAlias();
		default:
			break;
		}
		return null;
	}
}
