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
 * @author dzh Ȩ�޷���
 */
public interface AuthorizationService {

	// boolean hasPermmsion(String p_id); /**/

	/**
	 * �Ƿ�Ϸ��û�
	 */
	boolean isValidUser(String name, String pass);

	// boolean containRole()

	/**
	 * ��ȡ��ɫ
	 */
	// IRole getRole(String name, String pass);

	/**
	 * ��ȡȨ����Ϣ
	 */
	AuthMsg getAuthMsg(String name, String pass);

	/**
	 * ��ѯȫ���û�
	 * 
	 * @return
	 */
	List<IUser> getAllUsers();

	/**
	 * �����Ʋ�ѯ�û�
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	List<IUser> getAllUsers(int start, int limit);

	/**
	 * �������û�
	 * 
	 * @author caiyu
	 * @param newId
	 * @param newName
	 * @param newPass
	 * @param newInfo
	 */
	boolean addUser(String newId, String newName, String newPass, String newInfo);

	/**
	 * �Ƴ��û�
	 * 
	 * @author caiyu
	 * @param users
	 * @return
	 */
	boolean removeUser(String[] users);

	/**
	 * �޸��û�
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
	 * �����Ʋ鿴��ɫ
	 * 
	 * @author caiyu
	 * @param start
	 *            ��ʼλ��
	 * @param limit
	 *            ���Ƴ���
	 * @return
	 */
	List<IRole> getAllRoles(int start, int limit);

	/**
	 * ��ѯȫ����ɫ
	 * 
	 * @author caiyu
	 * @return
	 */
	List<IRole> getAllRoles();

	/**
	 * ��ӽ�ɫ
	 * 
	 * @author caiyu
	 * @param newName
	 * @param newDesc
	 * @return
	 */
	boolean addRole(String newName, String newDesc);

	/**
	 * ɾ����ɫ
	 * 
	 * @author caiyu
	 * @param split
	 * @return
	 */
	boolean removeRole(String[] roles);

	/**
	 * �޸Ľ�ɫ����
	 * 
	 * @author caiyu
	 * @param id
	 * @param newName
	 * @param newDesc
	 * @return
	 */
	boolean modifyRole(String id, String newName, String newDesc);

	/**
	 * ��ѯȫ������
	 * 
	 * @author caiyu
	 * @return
	 */
	List<IPermission> getAllPerms();

	/**
	 * ��ѯȫ����������
	 * 
	 * @author caiyu
	 * @return
	 */
	List<IPCategory> getAllPCategories();

	/**
	 * �Ƴ�Ȩ��
	 * 
	 * @author caiyu
	 * @param permissions
	 *            Ȩ��ID���ַ�������
	 * @return
	 */
	boolean removePermissions(String[] permissions);

	/**
	 * ����Ȩ��
	 * 
	 * @author caiyu
	 * @param newId
	 *            Ȩ��Id��4λ16��������
	 * @param newName
	 *            Ȩ������
	 * @param newDesc
	 *            Ȩ������
	 * @param newCategory
	 *            ����ID
	 * @return true�ɹ�,falseʧ��
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
	 * @return true�ɹ�,falseʧ��
	 */
	boolean modifyPermission(String id, String newName, String newDesc,
			String newCategory);

	/**
	 * ����ID��ѯ��ɫ
	 * 
	 * @author caiyu
	 * @param roleId
	 * @return
	 */
	IRole getRole(String roleId);

	/**
	 * �޸Ľ�ɫȨ��
	 * 
	 * @author caiyu
	 * @param id
	 * @param permissions
	 *            Ȩ��ID
	 * @return
	 */
	boolean modifyRolePermission(String id, String[] permissions);

	/**
	 * ��ѯ
	 * 
	 * @author caiyu
	 * @param sql
	 *            ��ѯ���
	 * @return
	 */
	boolean executeQuery(String sql);

	/**
	 * ���ָ���û�
	 * 
	 * @author caiyu
	 * @param uid
	 */
	IUser getUser(String uid);

	/**
	 * �޸��û���ɫ
	 * 
	 * @author caiyu
	 * @param id
	 * @param roles
	 */
	boolean modifyUserRole(String id, String[] roles);

	/**
	 * ���ӹ���Ա�û�
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
	 * �������Ʋ�ѯȫ������Ա�û�
	 * 
	 * @author caiyu
	 * @param start
	 *            ָ����ʼλ��
	 * @param limit
	 *            ָ�����Ƹ���
	 * @return
	 */
	List<IAdmin> getAllAdmins(int start, int limit);

	/**
	 * ��ѯȫ������Ա�û�
	 * 
	 * @author caiyu
	 * @return
	 */
	List<IAdmin> getAllAdmins();

	/**
	 * �޸Ĺ���Ա�û�
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
	 * ɾ������Ա�û�
	 * 
	 * @author caiyu
	 * @param ids
	 *            ����Ա�û�ID����
	 */
	boolean removeAdmin(String[] ids);

	/**
	 * �Ϸ�����Ա���
	 * 
	 * @author caiyu
	 * @param n
	 * @param p
	 * @return
	 */
	boolean isValidAdmin(String n, String p);

	/**
	 * ���Ȩ�޷���
	 * 
	 * @author caiyu
	 * @param newName
	 * @param newDesc
	 * @return
	 */
	boolean addPCategory(String newName, String newDesc);

	/**
	 * �Ƴ�Ȩ�޷���
	 * 
	 * @author caiyu
	 * @param ids
	 */
	boolean removePCategory(String[] ids);

	/**
	 * �������Ʋ�ѯ����
	 * 
	 * @author caiyu
	 * @param start
	 * @param limit
	 * @return
	 */
	List<IPCategory> getAllPCategories(int start, int limit);

	/**
	 * ����ID���ָ��Admin
	 * 
	 * @author caiyu
	 * @param name
	 * @return
	 */
	IAdmin getAdmin(String aid);

}
