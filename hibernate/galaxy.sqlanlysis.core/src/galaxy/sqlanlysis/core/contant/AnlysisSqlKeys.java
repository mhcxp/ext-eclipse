package galaxy.sqlanlysis.core.contant;

/**
 * sqlģ�ͳ��ó���
 * 
 * @author caiyu
 * @date 2012-11-14
 */
public interface AnlysisSqlKeys {
	/**
	 * SQL���ͷģ�飬��INSERT INTO��SELECT��
	 * 
	 */
	public final static int HEAD = 0x1001;
	/**
	 * SQL���DISTINCTģ��
	 */
	public final static int DISTINCT = 0x1002;
	/**
	 * SQL���Ŀ�����ݱ�ģ��
	 */
	public final static int TARGET_TABLE = 0x1003;
	/**
	 * SQL����ֶ��б�ģ��
	 */
	public final static int COLUMNS = 0x1004;
	/**
	 * SQL����ҳģ��
	 */
	public final static int LIMIT = 0x1005;
	/**
	 * SQL���WHEREģ�飬���������
	 */
	public final static int WHERE = 0x1006;
	/**
	 * SQL������ģ��
	 */
	public final static int GROUP_BY = 0x1007;
	/**
	 * SQL�������ģ��
	 */
	public final static int ORDER_BY = 0x1008;
	/**
	 * SQL���ֵ�б�ģ��
	 */
	public final static int VALUES = 0x1009;
	/**
	 * SQL���SETģ��
	 */
	public final static int SET = 0x1010;
}
