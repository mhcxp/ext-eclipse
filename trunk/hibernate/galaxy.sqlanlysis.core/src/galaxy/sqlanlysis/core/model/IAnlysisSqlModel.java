package galaxy.sqlanlysis.core.model;

/**
 * �ɽ�������Ƕ�׵�SQLģ��
 * 
 * @author caiyu
 * @date 2012-11-9
 */
public interface IAnlysisSqlModel {

	/**
	 * ��ȡSQLģ��Ԫ������
	 * 
	 * @param elementKey
	 * @return
	 */
	Object getElement(Object elementKey);

	/**
	 * ����SQLģ��Ԫ������
	 * 
	 * @param elementKey
	 * @param elementValue
	 */
	void setElement(int elementKey, Object elementValue);
}
