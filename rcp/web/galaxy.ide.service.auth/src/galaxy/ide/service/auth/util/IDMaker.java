package galaxy.ide.service.auth.util;

import java.util.UUID;

public class IDMaker {
	public static String newsID() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}
}
