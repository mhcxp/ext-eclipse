package galaxy.sqlanlysis.core.dialect.layout;

import java.util.List;

/**
 * SQL��䲼�ֽӿ�
 * 
 * @author caiyu
 * @date 2012-11-15
 */
public interface IAnlysisSqlLayout {
	/**
	 * ��ȡ������
	 * 
	 * @return
	 */
	AnylsisSqlSorter getSorter();

	/**
	 * ���ݲ����б�Ͳ����ؼ���������SQL
	 * 
	 * @param args
	 *            �����б�
	 * @param key
	 *            �ؼ���
	 * @return
	 */
	String render(List<? extends Object> args, int key);

}
