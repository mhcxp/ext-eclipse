package galaxy.sqlanlysis.core.model;

/**
 * ���Ԫ��ģ��
 * 
 * @author caiyu
 * @date 2012-11-14
 */
public abstract class SqlElementModel extends AbstractAnlysisSqlModel {
	protected final static int NAME = 0x013;

	/**
	 * ���Ԫ������
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
