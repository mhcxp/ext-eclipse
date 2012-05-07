package galaxy.ide.service.auth.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Tool {
	public static void main(String[] args) {
		System.out.println(Md5("admin"));

		encode("12121414", "2334");
	}

	public static void encode(String string, String random) {

	}

	private static String Md5(String pass) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(pass.getBytes());
			byte b[] = md.digest();

			int i;

			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return buf.toString();
	}
}
