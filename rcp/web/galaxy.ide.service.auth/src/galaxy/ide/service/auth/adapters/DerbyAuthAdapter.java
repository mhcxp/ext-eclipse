/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.ide.service.auth.adapters;

import galaxy.derby.core.manager.DerbyManager;
import galaxy.ide.auth.msg.AuthMsg;
import galaxy.ide.auth.role.IPCategory;
import galaxy.ide.auth.role.IPermission;
import galaxy.ide.auth.role.IRole;
import galaxy.ide.auth.role.IUser;
import galaxy.ide.auth.role.RoleFactory;
import galaxy.ide.service.auth.util.IDMaker;
import galaxy.ide.service.auth.util.SqlFileExecutor;
import galaxy.ide.site.role.IAdmin;
import galaxy.ide.site.role.SiteFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.runtime.Platform;

/**
 * @author caiyu
 * @date Oct 31, 2011 5:21:04 PM 从数据库获取权限信息
 */
public class DerbyAuthAdapter implements IAuthAdapter {
	private DerbyManager derbyMgr = null;

	private Connection conn;

	// private static final String DB_id = "auth";
	//
	// private static final String TABLE_ROLE = "role";
	//
	// private static final String TABLE_USER = "user";

	public DerbyAuthAdapter() {
		initAdapter();
	}

	protected void initAdapter() {
		derbyMgr = DerbyManager.newInstance(DerbyManager.TYPE_EMBEDDED);
		// TODO 配置连接
		// String sysHome = "F:\\DERBY\\";
		String sysHome = Platform.getInstanceLocation().getURL().getFile();
		Properties info = null;
		String dbid = "GALA_DB";
		conn = derbyMgr.createConn(sysHome, dbid, info);

		try {
			SqlFileExecutor
					.execute(conn,
							"E:\\IDE_SRV\\insert_permission.sql");
			SqlFileExecutor
					.execute(conn,
							"E:\\IDE_SRV\\insert_category.sql");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// try {
		// SqlFileExecutor
		// .execute(conn,
		// "F:\\workspace_only_galaIDE\\galaxy.ide.service.auth\\sql\\insert_permission.sql");
		// SqlFileExecutor
		// .execute(conn,
		// "F:\\workspace_only_galaIDE\\galaxy.ide.service.auth\\sql\\insert_category.sql");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	public void dispose() {
		derbyMgr.close();
	}

	@Override
	public boolean isValidUser(String id, String pass) {
		Statement stat = null;
		boolean isValidUser = false;
		try {

			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM users WHERE c_id='"
					+ id + "' and c_password='" + pass + "'");
			if (rs != null)
				isValidUser = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return isValidUser;
	}

	@Override
	public AuthMsg getAuthMsg(String id, String pass) {
		AuthMsg am = null;
		if (isValidUser(id, pass)) {
			IUser user = findUser(id);
			am = AuthMsg.newAuthMsg(user);
		}
		return am;
	}

	/**
	 * 
	 * @param user
	 */
	private void addRoleToUser(IUser user) {
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat
					.executeQuery("SELECT * FROM re_user_role WHERE c_uid='"
							+ user.getID() + "'");
			if (rs != null) {
				while (rs.next()) {
					String rid = rs.getString("c_rid").trim();
					if (!"".equals(rid))
						user.addRole(findRole(rid));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	/**
	 * 
	 * @param roleId
	 * @return
	 */
	private IRole findRole(String roleId) {
		Statement stat = null;
		IRole role = RoleFactory.newRole(roleId);
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM roles WHERE c_id='"
					+ roleId + "'");
			if (rs != null) {
				while (rs.next()) {
					role.setName(rs.getString("c_name").trim());
					role.setDesciption(rs.getString("vc_desc").trim());
					addPermissionToRole(role);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return role;
	}

	private void addPermissionToRole(IRole role) {
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat
					.executeQuery("SELECT * FROM re_role_permission WHERE c_rid='"
							+ role.getID() + "'");
			if (rs != null) {
				while (rs.next()) {
					String pid = rs.getString("c_pid").trim();
					if (!"".equals(pid))
						role.addPerm(findPermission(pid));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	private IPermission findPermission(String permId) {
		Statement stat = null;
		IPermission perm = RoleFactory.newPermission(permId);
		try {
			stat = conn.createStatement();
			ResultSet rs = stat
					.executeQuery("SELECT * FROM permission WHERE c_id='"
							+ permId + "'");
			if (rs != null) {
				while (rs.next()) {
					perm.setName(rs.getString("c_name").trim());
					perm.setDesciption(String.valueOf(rs.getString("vc_desc"))
							.trim());
					perm.setCategory(findCategory(rs.getString("c_category")
							.trim(), perm));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return perm;
	}

	private IPCategory findCategory(String cateId, IPermission perm) {
		Statement stat = null;
		IPCategory cate = RoleFactory.newCategory(cateId);
		try {
			stat = conn.createStatement();
			ResultSet rs = stat
					.executeQuery("SELECT * FROM perm_category WHERE c_id='"
							+ cateId + "'");
			if (rs != null) {
				while (rs.next()) {
					cate.setName(rs.getString("c_name").trim());
					cate.setDesciption(rs.getString("vc_desc").trim());
					cate.addPerm(perm);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return cate;
	}

	private IUser findUser(String id) {
		Statement stat = null;
		IUser user = RoleFactory.newUser(id);
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM users WHERE c_id='"
					+ id + "' ");
			if (rs != null) {
				while (rs.next()) {
					user.setName(rs.getString("c_name").trim());
					user.setPassword(rs.getString("c_password").trim());
					addRoleToUser(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return user;
	}

	/**
	 * 获取所有的用户
	 */
	@Override
	public List<IUser> getAllUsers() {
		List<IUser> users = new ArrayList<IUser>();
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM users");
			if (rs != null) {
				while (rs.next()) {
					IUser user = RoleFactory.newUser(rs.getString("c_id")
							.trim());
					user.setName(rs.getString("c_name").trim());
					user.setPassword(rs.getString("c_password").trim());
					addRoleToUser(user);
					users.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return users;
	}

	@Override
	public boolean addUser(String newId, String newName, String newPass,
			String newInfo) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn
					.prepareStatement("insert into users(c_id,c_name,c_password,c_proj_group) values(?,?,?,?)");
			stat.setString(1, newId);
			stat.setString(2, newName);
			stat.setString(3, newPass);
			stat.setString(4, newInfo);
			flag = stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return flag;
	}

	@Override
	public List<IUser> getAllUsers(int start, int limit) {
		List<IUser> users = new ArrayList<IUser>();
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat
					.executeQuery("SELECT * FROM ( SELECT ROW_NUMBER() OVER() AS rownum, users.* FROM users) "
							+ "AS tmp WHERE rownum <= "
							+ (start + limit)
							+ " and rownum>" + start);
			if (rs != null) {
				while (rs.next()) {
					IUser user = RoleFactory.newUser(rs.getString("c_id")
							.trim());
					user.setName(rs.getString("c_name").trim());
					user.setPassword(rs.getString("c_password").trim());
					addRoleToUser(user);
					users.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return users;
	}

	@Override
	public boolean removeUser(String[] users) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement("DELETE FROM users WHERE c_id=?");
			for (String id : users) {
				stat.setString(1, id);
				delUser2Role(id, "user");
				flag = stat.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}

	@Override
	public boolean modifyUser(String id, String newName, String newPass,
			String newInfo) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn
					.prepareStatement("UPDATE users SET c_name=?,c_password=?,c_proj_group=? WHERE c_id=?");
			stat.setString(1, newName);
			stat.setString(2, newPass);
			stat.setString(3, newInfo);
			stat.setString(4, id);
			flag = stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return flag;
	}

	@Override
	public List<IRole> getAllRoles(int start, int limit) {
		List<IRole> roles = new ArrayList<IRole>();
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat
					.executeQuery("SELECT * FROM ( SELECT ROW_NUMBER() OVER() AS rownum, roles.* FROM roles) "
							+ "AS tmp WHERE rownum <= "
							+ (start + limit)
							+ " and rownum>" + start);
			if (rs != null) {
				while (rs.next()) {
					IRole role = RoleFactory.newRole(rs.getString("c_id")
							.trim());
					role.setName(rs.getString("c_name").trim());
					role.setDesciption(rs.getString("vc_desc").trim());
					addPermissionToRole(role);
					roles.add(role);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return roles;
	}

	@Override
	public List<IRole> getAllRoles() {
		List<IRole> roles = new ArrayList<IRole>();
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM roles");
			if (rs != null) {
				while (rs.next()) {
					IRole role = RoleFactory.newRole(rs.getString("c_id")
							.trim());
					role.setName(rs.getString("c_name").trim());
					role.setDesciption(rs.getString("vc_desc").trim());
					addPermissionToRole(role);
					roles.add(role);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return roles;
	}

	@Override
	public boolean addRole(String newName, String newDesc) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn
					.prepareStatement("INSERT INTO roles(c_id,c_name,vc_desc) VALUES(?,?,?)");
			stat.setString(1, IDMaker.newsID());
			stat.setString(2, newName);
			stat.setString(3, newDesc);
			flag = stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}

	private void delUser2Role(String id, String type) {
		PreparedStatement stat = null;
		String column = null;
		if ("user".equals(type)) {
			column = "c_uid";
		} else if ("role".equals(type)) {
			column = "c_rid";
		}
		try {
			stat = conn.prepareStatement("DELETE FROM re_user_role WHERE "
					+ column + "=?");
			stat.setString(1, id);
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void delRole2Permission(String id, String type) {
		PreparedStatement stat = null;
		String column = null;
		if ("permission".equals(type)) {
			column = "c_pid";
		} else if ("role".equals(type)) {
			column = "c_rid";
		}
		try {
			stat = conn
					.prepareStatement("DELETE FROM re_role_permission WHERE "
							+ column + "=?");
			stat.setString(1, id);
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean removeRole(String[] roles) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement("DELETE FROM roles WHERE c_id=?");
			for (String id : roles) {
				stat.setString(1, id);
				delUser2Role(id, "role");
				delRole2Permission(id, "role");
				flag = stat.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stat != null)
					stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean modifyRole(String id, String newName, String newDesc) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn
					.prepareStatement("UPDATE roles SET c_name=?,vc_desc=? WHERE c_id=?");
			stat.setString(1, newName);
			stat.setString(2, newDesc);
			stat.setString(3, id);
			flag = stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}

	@Override
	public List<IPermission> getAllPerms() {
		List<IPermission> perms = new ArrayList<IPermission>();
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM permission");
			if (rs != null) {
				while (rs.next()) {
					IPermission perm = RoleFactory.newPermission(rs.getString(
							"c_id").trim());
					perm.setName(rs.getString("c_name").trim());
					perm.setDesciption(String.valueOf(rs.getString("vc_desc"))
							.trim());
					perm.setCategory(findCategory(rs.getString("c_category")
							.trim(), perm));
					perms.add(perm);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return perms;
	}

	@Override
	public List<IPCategory> getAllPCategories() {
		List<IPCategory> categories = new ArrayList<IPCategory>();
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM perm_category");
			if (rs != null) {
				while (rs.next()) {
					IPCategory category = RoleFactory.newCategory(rs.getString(
							"c_id").trim());
					category.setName(rs.getString("c_name").trim());
					category.setDesciption(rs.getString("vc_desc").trim());
					categories.add(category);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return categories;
	}

	@Override
	public boolean removePermissions(String[] permissions) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement("DELETE FROM permission WHERE c_id=?");
			for (String id : permissions) {
				stat.setString(1, id);
				delRole2Permission(id, "permission");
				flag = stat.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}

	@Override
	public boolean addPermission(String newId, String newName, String newDesc,
			String newCategory) {

		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn
					.prepareStatement("insert into permission(c_id,c_name,vc_desc,c_category) values(?,?,?,?)");
			stat.setString(1, newId);
			stat.setString(2, newName);
			stat.setString(3, newDesc);
			stat.setString(4, newCategory);
			flag = stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return flag;
	}

	@Override
	public boolean modifyPermission(String id, String newName, String newDesc,
			String newCategory) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn
					.prepareStatement("UPDATE permission SET c_name=?,vc_desc=?,c_category=? WHERE c_id=?");
			stat.setString(1, newName);
			stat.setString(2, newDesc);
			stat.setString(3, newCategory);
			stat.setString(4, id);
			flag = stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}

	@Override
	public IRole getRole(String roleId) {
		IRole role = null;
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM roles WHERE c_id='"
					+ roleId + "'");
			if (rs != null) {
				while (rs.next()) {
					role = RoleFactory.newRole(rs.getString("c_id").trim());
					role.setName(rs.getString("c_name").trim());
					role.setDesciption(rs.getString("vc_desc").trim());
					addPermissionToRole(role);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return role;
	}

	@Override
	public boolean modifyRolePermission(String rid, String[] pids) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn
					.prepareStatement("DELETE FROM  re_role_permission WHERE c_rid='"
							+ rid + "'");
			stat.execute();
			stat = conn
					.prepareStatement("INSERT INTO re_role_permission(c_rid,c_pid) VALUES(?,?)");
			for (String pid : pids) {
				stat.setString(1, rid);
				stat.setString(2, pid);
				flag = stat.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}

	@Override
	public boolean executeQuery(String sql) {
		Statement stat = null;
		boolean flag = false;
		try {
			stat = conn.createStatement();
			flag = stat.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}

	@Override
	public IUser getUser(String uid) {
		IUser user = null;
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM users WHERE c_id='"
					+ uid + "'");
			if (rs != null) {
				while (rs.next()) {
					user = RoleFactory.newUser(rs.getString("c_id").trim());
					user.setName(rs.getString("c_name").trim());
					user.setPassword(rs.getString("c_password").trim());
					addRoleToUser(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return user;
	}

	@Override
	public boolean modifyUserRole(String uid, String[] roles) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn
					.prepareStatement("DELETE FROM  re_user_role WHERE c_uid='"
							+ uid + "'");
			stat.execute();
			stat = conn
					.prepareStatement("INSERT INTO re_user_role(c_uid,c_rid) VALUES(?,?)");
			for (String rid : roles) {
				stat.setString(1, uid);
				stat.setString(2, rid);
				flag = stat.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}

	@Override
	public boolean isValidAdmin(String id, String pass) {
		Statement stat = null;
		boolean isValidAdmin = false;
		try {

			stat = conn.createStatement();
			ResultSet rs = stat
					.executeQuery("SELECT * FROM admins WHERE c_id='" + id
							+ "' and c_password='" + pass + "'");
			if (rs != null)
				isValidAdmin = rs.next();
			System.out.println(id + ":" + pass);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return isValidAdmin;
	}

	@Override
	public List<IAdmin> getAllAdmins() {
		List<IAdmin> admins = new ArrayList<IAdmin>();
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM admins");
			if (rs != null) {
				while (rs.next()) {
					IAdmin admin = SiteFactory.newAdmin(rs.getString("c_id")
							.trim());
					admin.setName(rs.getString("c_name").trim());
					admin.setPassword(rs.getString("c_password").trim());
					admins.add(admin);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return admins;
	}

	@Override
	public IAdmin getAdmin(String id) {
		IAdmin admin = null;
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat
					.executeQuery("SELECT * FROM admins WHERE c_id='" + id
							+ "'");
			if (rs != null) {
				while (rs.next()) {
					admin = SiteFactory.newAdmin(rs.getString("c_id").trim());
					admin.setName(rs.getString("c_name").trim());
					admin.setPassword(rs.getString("c_password").trim());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return admin;
	}

	@Override
	public boolean removeAdmin(String[] ids) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement("DELETE FROM admins WHERE c_id=?");
			for (String id : ids) {
				stat.setString(1, id);
				flag = stat.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}

	@Override
	public boolean addAdmin(String newId, String newName, String newPass,
			String newInfo) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn
					.prepareStatement("insert into admins(c_id,c_name,c_password,c_proj_group) values(?,?,?,?)");
			stat.setString(1, newId);
			stat.setString(2, newName);
			stat.setString(3, newPass);
			stat.setString(4, newInfo);
			flag = stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return flag;
	}

	@Override
	public boolean modifyAdmin(String id, String newName, String newPass,
			String newInfo) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn
					.prepareStatement("UPDATE admins SET c_name=?,c_password=?,c_proj_group=? WHERE c_id=?");
			stat.setString(1, newName);
			stat.setString(2, newPass);
			stat.setString(3, newInfo);
			stat.setString(4, id);
			flag = stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return flag;
	}

	@Override
	public List<IAdmin> getAllAdmins(int start, int limit) {
		List<IAdmin> admins = new ArrayList<IAdmin>();
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat
					.executeQuery("SELECT * FROM ( SELECT ROW_NUMBER() OVER() AS rownum, admins.* FROM admins) "
							+ "AS tmp WHERE rownum <= "
							+ (start + limit)
							+ " and rownum>" + start);
			if (rs != null) {
				while (rs.next()) {
					IAdmin admin = SiteFactory.newAdmin(rs.getString("c_id")
							.trim());
					admin.setName(rs.getString("c_name").trim());
					admin.setPassword(rs.getString("c_password").trim());
					admins.add(admin);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return admins;
	}

	@Override
	public boolean addPCategory(String newName, String newDesc) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn
					.prepareStatement("insert into perm_category(c_id,c_name,vc_desc) values(?,?,?)");
			stat.setString(1, IDMaker.newsID());
			stat.setString(2, newName);
			stat.setString(3, newDesc);
			flag = stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return flag;
	}

	@Override
	public boolean removePCategory(String[] ids) {
		boolean flag = false;
		PreparedStatement stat = null;
		try {
			stat = conn
					.prepareStatement("DELETE FROM perm_category WHERE c_id=?");
			for (String id : ids) {
				stat.setString(1, id);
				flag = stat.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}

	@Override
	public List<IPCategory> getAllPCategories(int start, int limit) {
		List<IPCategory> pcategories = new ArrayList<IPCategory>();
		Statement stat = null;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat
					.executeQuery("SELECT * FROM ( SELECT ROW_NUMBER() OVER() AS rownum, perm_category.* FROM perm_category) "
							+ "AS tmp WHERE rownum <= "
							+ (start + limit)
							+ " and rownum>" + start);
			if (rs != null) {
				while (rs.next()) {
					IPCategory pcategory = RoleFactory.newCategory(rs
							.getString("c_id").trim());
					pcategory.setName(rs.getString("c_name").trim());
					pcategory.setDesciption(rs.getString("vc_desc").trim());
					pcategories.add(pcategory);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stat != null)
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		return pcategories;
	}
}
