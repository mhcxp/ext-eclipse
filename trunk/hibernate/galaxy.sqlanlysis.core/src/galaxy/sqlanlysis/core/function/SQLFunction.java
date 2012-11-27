package galaxy.sqlanlysis.core.function;

import java.util.List;

/**
 * SQL函数类型定义接口
 * 
 * @author caiyu
 * @date 2012-11-26
 */
public interface SQLFunction {
	/**
	 * 是否有参数
	 * 
	 * @return
	 */
	public boolean hasArguments();

	/**
	 * 当无参的时候是否有括号
	 * 
	 * @return
	 */
	public boolean hasParenthesesIfNoArguments();

	/**
	 * 赋参
	 * 
	 * @param args
	 *            参数列表
	 * @return sql
	 */
	public String render(@SuppressWarnings("rawtypes") List args);
}
