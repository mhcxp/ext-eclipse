/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.ide.service.auth;

import galaxy.ide.auth.msg.AuthMsg;
import galaxy.ide.auth.role.IPCategory;
import galaxy.ide.auth.role.IPermission;
import galaxy.ide.auth.role.IRole;
import galaxy.ide.auth.role.IUser;
import galaxy.ide.site.role.IAdmin;

import java.util.List;

/**
 * @author dzh 权限服务
 */
public interface AuthorizationService {

	// boolean hasPermmsion(String p_id); /**/

	/**
	 * 是否合法用户
	 */
	boolean isValidUser(String name, String pass);

	// boolean containRole()

	/**
	 * 获取角色
	 */
	// IRole getRole(String name, String pass);

	/**
	 * 获取权限信息
	 */
	AuthMsg getAuthMsg(String name, String pass);

	/**
	 * 查询全部用户
	 * 
	 * @return
	 */
	List<IUser> getAllUsers();

	/**
	 * 按限制查询用户
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	List<IUser> getAllUsers(int start, int limit);

	/**
	 * 增加新用户
	 * 
	 * @author caiyu
	 * @param newId
	 * @param newName
	 * @param newPass
	 * @param newInfo
	 */
	boolean addUser(String newId, String newName, String newPass, String newInfo);

	/**
	 * 移除用户
	 * 
	 * @author caiyu
	 * @param users
	 * @return
	 */
	boolean removeUser(String[] users);

	/**
	 * 修改用户
	 * 
	 * @author caiyu
	 * @param id
	 * @param newName
	 * @param newPass
	 * @param newInfo
	 * @return
	 */
	boolean modifyUser(String id, String newName, String newPass, String newInfo);

	/**
	 * 按限制查看角色
	 * 
	 * @author caiyu
	 * @param start
	 *            开始位置
	 * @param limit
	 *            限制长度
	 * @return
	 */
	List<IRole> getAllRoles(int start, int limit);

	/**
	 * 查询全部角色
	 * 
	 * @author caiyu
	 * @return
	 */
	List<IRole> getAllRoles();

	/**
	 * 添加角色
	 * 
	 * @author caiyu
	 * @param newName
	 * @param newDesc
	 * @return
	 */
	boolean addRole(String newName, String newDesc);

	/**
	 * 删除角色
	 * 
	 * @author caiyu
	 * @param split
	 * @return
	 */
	boolean removeRole(String[] roles);

	/**
	 * 修改角色内容
	 * 
	 * @author caiyu
	 * @param id
	 * @param newName
	 * @param newDesc
	 * @return
	 */
	boolean modifyRole(String id, String newName, String newDesc);

	/**
	 * 查询全部参数
	 * 
	 * @author caiyu
	 * @return
	 */
	List<IPermission> getAllPerms();

	/**
	 * 查询全部参数分类
	 * 
	 * @author caiyu
	 * @return
	 */
	List<IPCategory> getAllPCategories();

	/**
	 * 移除权限
	 * 
	 * @author caiyu
	 * @param permissions
	 *            权限ID号字符串数组
	 * @return
	 */
	boolean removePermissions(String[] permissions);

	/**
	 * 增加权限
	 * 
	 * @author caiyu
	 * @param newId
	 *            权限Id，4位16进制数字
	 * @param newName
	 *            权限名称
	 * @param newDesc
	 *            权限描述
	 * @param newCategory
	 *            分类ID
	 * @return true成功,false失败
	 */
	boolean addPermission(String newId, String newName, String newDesc,
			String newCategory);

	/**
	 * 
	 * @author caiyu
	 * @param id
	 * @param newName
	 * @param newDesc
	 * @param newCategory
	 * @return true成功,false失败
	 */
	boolean modifyPermission(String id, String newName, String newDesc,
			String newCategory);

	/**
	 * 根据ID查询角色
	 * 
	 * @author caiyu
	 * @param roleId
	 * @return
	 */
	IRole getRole(String roleId);

	/**
	 * 修改角色权限
	 * 
	 * @author caiyu
	 * @param id
	 * @param permissions
	 *            权限ID
	 * @return
	 */
	boolean modifyRolePermission(String id, String[] permissions);

	/**
	 * 查询
	 * 
	 * @author caiyu
	 * @param sql
	 *            查询语句
	 * @return
	 */
	boolean executeQuery(String sql);

	/**
	 * 获得指定用户
	 * 
	 * @author caiyu
	 * @param uid
	 */
	IUser getUser(String uid);

	/**
	 * 修改用户角色
	 * 
	 * @author caiyu
	 * @param id
	 * @param roles
	 */
	boolean modifyUserRole(String id, String[] roles);

	/**
	 * 增加管理员用户
	 * 
	 * @author caiyu
	 * @param newId
	 * @param newName
	 * @param newPass
	 * @param newInfo
	 * @return
	 */
	boolean addAdmin(String newId, String newName, String newPass,
			String newInfo);

	/**
	 * 根据限制查询全部管理员用户
	 * 
	 * @author caiyu
	 * @param start
	 *            指定开始位置
	 * @param limit
	 *            指定限制个数
	 * @return
	 */
	List<IAdmin> getAllAdmins(int start, int limit);

	/**
	 * 查询全部管理员用户
	 * 
	 * @author caiyu
	 * @return
	 */
	List<IAdmin> getAllAdmins();

	/**
	 * 修改管理员用户
	 * 
	 * @author caiyu
	 * @param id
	 * @param newName
	 * @param newPass
	 * @param newInfo
	 * @return
	 */
	boolean modifyAdmin(String id, String newName, String newPass,
			String newInfo);

	/**
	 * 删除管理员用户
	 * 
	 * @author caiyu
	 * @param ids
	 *            管理员用户ID数组
	 */
	boolean removeAdmin(String[] ids);

	/**
	 * 合法管理员检查
	 * 
	 * @author caiyu
	 * @param n
	 * @param p
	 * @return
	 */
	boolean isValidAdmin(String n, String p);

	/**
	 * 添加权限分类
	 * 
	 * @author caiyu
	 * @param newName
	 * @param newDesc
	 * @return
	 */
	boolean addPCategory(String newName, String newDesc);

	/**
	 * 移除权限分类
	 * 
	 * @author caiyu
	 * @param ids
	 */
	boolean removePCategory(String[] ids);

	/**
	 * 根据限制查询分类
	 * 
	 * @author caiyu
	 * @param start
	 * @param limit
	 * @return
	 */
	List<IPCategory> getAllPCategories(int start, int limit);

	/**
	 * 根据ID获得指定Admin
	 * 
	 * @author caiyu
	 * @param name
	 * @return
	 */
	IAdmin getAdmin(String aid);

}
