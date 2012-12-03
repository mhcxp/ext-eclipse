package galaxy.sqlanlysis.core.model.value;

import galaxy.sqlanlysis.core.dialect.Dialect;
import galaxy.sqlanlysis.core.model.SqlElementModel;

/**
 * ÖµÄ£ÐÍ
 * 
 * @author caiyu
 * @date 2012-11-13
 */
public abstract class ValueModel extends SqlElementModel {
	public abstract String render(Dialect dialect);
}
