package galaxy.sqlanlysis.core.model;

/**
 * 语句元素模型
 * 
 * @author caiyu
 * @date 2012-11-14
 */
public abstract class SqlElementModel extends AbstractAnlysisSqlModel {
	protected final static int NAME = 0x013;

	/**
	 * 获得元素名称
	 * 
	 * @return
	 */
	public String getName() {
		// TODO Auto-generated method stub
		Object obj = getElement(NAME);
		if (obj instanceof String)
			return (String) obj;
		else
			return null;
	}

	public void setName(String name) {
		setElement(NAME, name);
	}
}
