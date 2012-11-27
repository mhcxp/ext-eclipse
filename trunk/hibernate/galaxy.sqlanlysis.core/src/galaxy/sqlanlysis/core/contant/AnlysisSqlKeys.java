package galaxy.sqlanlysis.core.contant;

/**
 * sql模型常用常量
 * 
 * @author caiyu
 * @date 2012-11-14
 */
public interface AnlysisSqlKeys {
	/**
	 * SQL语句头模块，如INSERT INTO、SELECT等
	 * 
	 */
	public final static int HEAD = 0x1001;
	/**
	 * SQL语句DISTINCT模块
	 */
	public final static int DISTINCT = 0x1002;
	/**
	 * SQL语句目标数据表模块
	 */
	public final static int TARGET_TABLE = 0x1003;
	/**
	 * SQL语句字段列表模块
	 */
	public final static int COLUMNS = 0x1004;
	/**
	 * SQL语句分页模块
	 */
	public final static int LIMIT = 0x1005;
	/**
	 * SQL语句WHERE模块，既条件语句
	 */
	public final static int WHERE = 0x1006;
	/**
	 * SQL语句分组模块
	 */
	public final static int GROUP_BY = 0x1007;
	/**
	 * SQL语句排序模块
	 */
	public final static int ORDER_BY = 0x1008;
	/**
	 * SQL语句值列表模块
	 */
	public final static int VALUES = 0x1009;
	/**
	 * SQL语句SET模块
	 */
	public final static int SET = 0x1010;
}
