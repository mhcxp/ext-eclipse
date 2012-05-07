/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.ide.service.auth.adapters;

import galaxy.ide.auth.msg.AuthMsg;
import galaxy.ide.auth.role.IPCategory;
import galaxy.ide.auth.role.IPermission;
import galaxy.ide.auth.role.IRole;
import galaxy.ide.auth.role.IUser;
import galaxy.ide.site.role.IAdmin;

import java.util.List;

/**
 * @author dzh
 * @date Oct 31, 2011 5:38:46 PM
 */
public interface IAuthAdapter {

	boolean isValidUser(String name, String pass);

	void dispose();

	// IRole getRole(String name,String pass);

	AuthMsg getAuthMsg(String name, String pass);

	List<IUser> getAllUsers();

	boolean addUser(String newId, String newName, String newPass, String newInfo);

	List<IUser> getAllUsers(int start, int limit);

	boolean removeUser(String[] users);

	boolean modifyUser(String id, String newName, String newPass, String newInfo);

	List<IRole> getAllRoles(int start, int limit);

	List<IRole> getAllRoles();

	boolean addRole(String newName, String newDesc);

	boolean removeRole(String[] roles);

	boolean modifyRole(String id, String newName, String newDesc);

	List<IPermission> getAllPerms();

	List<IPCategory> getAllPCategories();

	boolean removePermissions(String[] permissions);

	boolean addPermission(String newId, String newName, String newDesc,
			String newCategory);

	boolean modifyPermission(String id, String newName, String newDesc,
			String newCategory);

	IRole getRole(String roleId);

	boolean modifyRolePermission(String id, String[] permissions);

	boolean executeQuery(String sql);

	IUser getUser(String uid);

	boolean modifyUserRole(String id, String[] roles);

	boolean isValidAdmin(String name, String pass);

	List<IAdmin> getAllAdmins();

	IAdmin getAdmin(String id);

	boolean removeAdmin(String[] ids);

	boolean addAdmin(String newId, String newName, String newPass,
			String newInfo);

	boolean modifyAdmin(String id, String newName, String newPass,
			String newInfo);

	List<IAdmin> getAllAdmins(int start, int limit);

	boolean addPCategory(String newName, String newDesc);

	boolean removePCategory(String[] ids);

	List<IPCategory> getAllPCategories(int start, int limit);
}
