//$Id: Criterion.java 5685 2005-02-12 07:19:50Z steveebersole $
package galaxy.sqlanlysis.core.model.expression;

import galaxy.sqlanlysis.core.dialect.Dialect;
import galaxy.sqlanlysis.core.model.column.ColumnModel;
import galaxy.sqlanlysis.core.model.value.ValueModel;

import java.io.Serializable;

/**
 * Sql¹æ·¶
 * 
 * @author caiyu
 * @date 2012-11-30
 */
public interface Expression extends Serializable {

	String toSqlString(Dialect dialect, ColumnModel column, ValueModel value);

}
