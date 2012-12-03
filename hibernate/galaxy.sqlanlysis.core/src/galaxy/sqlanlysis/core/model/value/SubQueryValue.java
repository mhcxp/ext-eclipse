package galaxy.sqlanlysis.core.model.value;

import galaxy.sqlanlysis.core.dialect.Dialect;
import galaxy.sqlanlysis.core.model.SelectModel;

/**
 * 对应子查询的值类型
 * 
 * @author caiyu
 * @date 2012-12-3
 */
public class SubQueryValue extends ValueModel {
	private SelectModel model;

	public SelectModel getModel() {
		return model;
	}

	public void setModel(SelectModel model) {
		this.model = model;
	}

	@Override
	public String render(Dialect dialect) {
		try {
			Dialect subDialect = dialect.getClass().newInstance();
			subDialect.registerAnlysisSqlModel(model);
			return subDialect.getSqlString();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
