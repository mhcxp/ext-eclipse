package ext.eclipse.ide.auth.code.handler.reader;

/**
 * Ȩ�����ȡ������
 * 
 * @author caiyu
 * 
 */
public class AuthCodeReaderFactory {
	public final static String XML_READER = "XML";

	/**
	 * ���type��ȡ
	 * 
	 * @param type
	 *            XML_READER
	 * @return
	 */
	public static IAuthCodeReader getReader(String type) {
		if (XML_READER.equals(type)) {
			return new XMLAuthCodeReader();
		}
		throw new IllegalArgumentException("�Ƿ�type����:" + type);
	}
}
