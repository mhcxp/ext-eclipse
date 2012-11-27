package galaxy.sqlanlysis.core;

import galaxy.sqlanlysis.core.dialect.Dialect;
import galaxy.sqlanlysis.core.exception.IllegalModelTypeException;
import galaxy.sqlanlysis.core.model.IAnlysisSqlModel;
import galaxy.sqlanlysis.core.model.InsertModel;

/**
 * SQL拼装工厂
 * 
 * @author caiyu
 * @date 2012-11-14
 */
public final class SqlAssembleFactory {
	public static SqlAssembleFactory INSTANCE = null;

	private SqlAssembleFactory() {
	}

	public SqlAssembleFactory getInstance() {
		if (INSTANCE == null)
			INSTANCE = new SqlAssembleFactory();
		return INSTANCE;
	}

	/**
	 * 通过IAnlysisSqlModel（可分析的SQL模型）和dialect（数据库方言）来生成完整的insert语句
	 * 
	 * @param model
	 *            可分析的SQL模型
	 * @param dialect
	 *            数据库方言
	 * @return
	 */
	public String getInsertSql(IAnlysisSqlModel model, Dialect dialect) {
		if (!(model instanceof InsertModel)) {
			throw new IllegalModelTypeException();
		}
		dialect.registerAnlysisSqlModel(model);
		return dialect.getSqlString();
	}
}
