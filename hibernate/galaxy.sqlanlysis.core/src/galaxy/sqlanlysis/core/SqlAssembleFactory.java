package galaxy.sqlanlysis.core;

import galaxy.sqlanlysis.core.dialect.Dialect;
import galaxy.sqlanlysis.core.exception.IllegalModelTypeException;
import galaxy.sqlanlysis.core.model.IAnlysisSqlModel;
import galaxy.sqlanlysis.core.model.InsertModel;

/**
 * SQLƴװ����
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
	 * ͨ��IAnlysisSqlModel���ɷ�����SQLģ�ͣ���dialect�����ݿⷽ�ԣ�������������insert���
	 * 
	 * @param model
	 *            �ɷ�����SQLģ��
	 * @param dialect
	 *            ���ݿⷽ��
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
