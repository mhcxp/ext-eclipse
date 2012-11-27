package galaxy.sqlanlysis.core;

/**
 * SQL语句缓冲组合工具
 * 
 * @author caiyu
 * @date 2012-11-14
 */
public final class SqlBuffer {
	StringBuffer buffer = new StringBuffer();

	/**
	 * 添加以空格结尾的内容
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
	 * 添加以逗号结尾的内容
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
