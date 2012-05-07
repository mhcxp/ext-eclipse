/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.ide.service.auth.impl;

import galaxy.ide.auth.msg.AuthMsg;
import galaxy.ide.auth.role.IPCategory;
import galaxy.ide.auth.role.IPermission;
import galaxy.ide.auth.role.IRole;
import galaxy.ide.auth.role.IUser;
import galaxy.ide.service.auth.AuthorizationService;
import galaxy.ide.service.auth.adapters.DerbyAuthAdapter;
import galaxy.ide.service.auth.adapters.IAuthAdapter;
import galaxy.ide.site.role.IAdmin;

import java.util.List;

import org.osgi.service.component.ComponentContext;

/**
 * @author dzh
 * @date Oct 31, 2011 9:57:10 AM
 */
public class AuthorizationServiceImpl implements AuthorizationService {

	private IAuthAdapter adapter;

	public AuthorizationServiceImpl() {
	}

	@Override
	public boolean isValidUser(String name, String pass) {
		return adapter.isValidUser(name, pass);
	}

	private void closeService() {
		adapter.dispose();
		adapter = null;
		System.out.println("closeService()");
	}

	protected void activate(ComponentContext context) {
		System.out
				.println("NameService Component Active,within the bundle lifecircle.");
	}

	public void deactivate(ComponentContext context) throws Exception {
		System.out
				.println("NameService Component Deactive,within the bundle lifecircle.");
	}

	public void start() {
		System.out.println("AuthorizationServiceImpl.start()");
		adapter = new DerbyAuthAdapter();
	}

	public void stop() {
		System.out.println("AuthorizationServiceImpl.stop()");
		closeService();
	}

	// public IRole getRole(String name, String pass) {
	// return adapter.getRole(name,pass);
	// }

	public AuthMsg getAuthMsg(String name, String pass) {
		return adapter.getAuthMsg(name, pass);
	}

	@Override
	public List<IUser> getAllUsers() {
		return adapter.getAllUsers();
	}

	@Override
	public boolean addUser(String newId, String newName, String newPass,
			String newInfo) {
		return adapter.addUser(newId, newName, newPass, newInfo);
	}

	@Override
	public List<IUser> getAllUsers(int start, int limit) {
		return adapter.getAllUsers(start, limit);
	}

	@Override
	public boolean removeUser(String[] users) {
		return adapter.removeUser(users);
	}

	@Override
	public boolean modifyUser(String id, String newName, String newPass,
			String newInfo) {
		return adapter.modifyUser(id, newName, newPass, newInfo);
	}

	@Override
	public List<IRole> getAllRoles(int start, int limit) {
		return adapter.getAllRoles(start, limit);
	}

	@Override
	public List<IRole> getAllRoles() {
		return adapter.getAllRoles();
	}

	@Override
	public boolean addRole(String newName, String newDesc) {
		return adapter.addRole(newName, newDesc);
	}

	@Override
	public boolean removeRole(String[] roles) {
		return adapter.removeRole(roles);
	}

	@Override
	public boolean modifyRole(String id, String newName, String newDesc) {
		return adapter.modifyRole(id, newName, newDesc);
	}

	@Override
	public List<IPermission> getAllPerms() {
		return adapter.getAllPerms();
	}

	@Override
	public List<IPCategory> getAllPCategories() {
		return adapter.getAllPCategories();
	}

	@Override
	public boolean removePermissions(String[] permissions) {
		return adapter.removePermissions(permissions);
	}

	@Override
	public boolean addPermission(String newId, String newName, String newDesc,
			String newCategory) {
		return adapter.addPermission(newId, newName, newDesc, newCategory);
	}

	@Override
	public boolean modifyPermission(String id, String newName, String newDesc,
			String newCategory) {
		return adapter.modifyPermission(id, newName, newDesc, newCategory);
	}

	@Override
	public IRole getRole(String roleId) {
		return adapter.getRole(roleId);
	}

	@Override
	public boolean modifyRolePermission(String id, String[] permissions) {
		return adapter.modifyRolePermission(id, permissions);
	}

	@Override
	public boolean executeQuery(String sql) {
		return adapter.executeQuery(sql);
	}

	@Override
	public IUser getUser(String uid) {
		return adapter.getUser(uid);
	}

	@Override
	public boolean modifyUserRole(String id, String[] roles) {
		return adapter.modifyUserRole(id, roles);
	}

	@Override
	public boolean addAdmin(String newId, String newName, String newPass,
			String newInfo) {
		return adapter.addAdmin(newId, newName, newPass, newInfo);
	}

	@Override
	public List<IAdmin> getAllAdmins(int start, int limit) {
		return adapter.getAllAdmins(start, limit);
	}

	@Override
	public List<IAdmin> getAllAdmins() {
		return adapter.getAllAdmins();
	}

	@Override
	public boolean modifyAdmin(String id, String newName, String newPass,
			String newInfo) {
		return adapter.modifyAdmin(id, newName, newPass, newInfo);
	}

	@Override
	public boolean removeAdmin(String[] ids) {
		return adapter.removeAdmin(ids);
	}

	@Override
	public boolean isValidAdmin(String name, String pass) {
		return adapter.isValidAdmin(name, pass);
	}

	@Override
	public boolean addPCategory(String newName, String newDesc) {
		return adapter.addPCategory(newName, newDesc);
	}

	@Override
	public boolean removePCategory(String[] ids) {
		return adapter.removePCategory(ids);
	}

	@Override
	public List<IPCategory> getAllPCategories(int start, int limit) {
		return adapter.getAllPCategories(start, limit);
	}

	@Override
	public IAdmin getAdmin(String aid) {
		return adapter.getAdmin(aid);
	}

}
