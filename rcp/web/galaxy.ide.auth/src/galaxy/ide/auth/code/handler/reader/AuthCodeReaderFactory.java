package galaxy.ide.auth.code.handler.reader;

/**
 * 权限码获取器工厂
 * 
 * @author caiyu
 * 
 */
public class AuthCodeReaderFactory {
	public final static String XML_READER = "XML";

	/**
	 * 根据type获取
	 * 
	 * @param type
	 *            XML_READER
	 * @return
	 */
	public static IAuthCodeReader getReader(String type) {
		if (XML_READER.equals(type)) {
			return new XMLAuthCodeReader();
		}
		throw new IllegalArgumentException("非法type类型:" + type);
	}
}
