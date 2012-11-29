package galaxy.sqlanlysis.core.model;

/**
 * SQL语句抽象模型
 * 
 * @author caiyu
 * @date 2012-11-14
 */
public abstract class AnlysisStatementModel extends AbstractAnlysisSqlModel {
	AnlysisStatementModel() {
		init();
	}

	/**
	 * 初始化
	 */
	protected abstract void init();
}
