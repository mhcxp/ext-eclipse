package galaxy.sqlanlysis.core.dialect.layout;

import java.util.List;

/**
 * SQL语句布局接口
 * 
 * @author caiyu
 * @date 2012-11-15
 */
public interface IAnlysisSqlLayout {
	/**
	 * 获取排序器
	 * 
	 * @return
	 */
	AnylsisSqlSorter getSorter();

	/**
	 * 根据参数列表和参数关键字来交付SQL
	 * 
	 * @param args
	 *            参数列表
	 * @param key
	 *            关键字
	 * @return
	 */
	String render(List<? extends Object> args, int key);

}
