package galaxy.sqlanlysis.core.model;

/**
 * 可解析可自嵌套的SQL模型
 * 
 * @author caiyu
 * @date 2012-11-9
 */
public interface IAnlysisSqlModel {

	/**
	 * 获取SQL模型元素内容
	 * 
	 * @param elementKey
	 * @return
	 */
	Object getElement(Object elementKey);

	/**
	 * 设置SQL模型元素内容
	 * 
	 * @param elementKey
	 * @param elementValue
	 */
	void setElement(int elementKey, Object elementValue);
}
