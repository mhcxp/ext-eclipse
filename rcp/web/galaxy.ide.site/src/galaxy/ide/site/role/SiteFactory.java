package galaxy.ide.site.role;

import galaxy.ide.site.role.impl.AdminImpl;

public class SiteFactory {
	public static final IAdmin newAdmin(String id) {
		return AdminImpl.newInstance(id);
	}
}
