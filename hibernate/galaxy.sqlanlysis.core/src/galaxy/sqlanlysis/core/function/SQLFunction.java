package galaxy.sqlanlysis.core.function;

import java.util.List;

/**
 * SQL�������Ͷ���ӿ�
 * 
 * @author caiyu
 * @date 2012-11-26
 */
public interface SQLFunction {
	/**
	 * �Ƿ��в���
	 * 
	 * @return
	 */
	public boolean hasArguments();

	/**
	 * ���޲ε�ʱ���Ƿ�������
	 * 
	 * @return
	 */
	public boolean hasParenthesesIfNoArguments();

	/**
	 * ����
	 * 
	 * @param args
	 *            �����б�
	 * @return sql
	 */
	public String render(@SuppressWarnings("rawtypes") List args);
}
