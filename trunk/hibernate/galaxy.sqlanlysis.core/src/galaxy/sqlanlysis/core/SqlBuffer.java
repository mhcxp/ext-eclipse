package galaxy.sqlanlysis.core;

/**
 * SQL��仺����Ϲ���
 * 
 * @author caiyu
 * @date 2012-11-14
 */
public final class SqlBuffer {
	StringBuffer buffer = new StringBuffer();

	/**
	 * ����Կո��β������
	 * 
	 * @param content
	 */
	public void append(String content) {
		if (content == null)
			content = "null";
		buffer.append(content.trim());
		buffer.append(" ");
	}

	/**
	 * ����Զ��Ž�β������
	 * 
	 * @param content
	 */
	public void appendWithComma(String content) {
		if (content == null)
			content = "null";
		buffer.append(content.trim());
		buffer.append(",");
	}

	public String getSql() {
		return buffer.toString().trim();
	}

}
