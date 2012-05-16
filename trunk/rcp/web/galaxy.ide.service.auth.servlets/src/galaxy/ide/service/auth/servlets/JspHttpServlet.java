/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.ide.service.auth.servlets;

import galaxy.ide.auth.http.AuthHttpConstants;
import galaxy.ide.auth.msg.AuthMsg;
import galaxy.ide.auth.msg.AuthMsgCoder;
import galaxy.ide.auth.role.IPCategory;
import galaxy.ide.auth.role.IPermission;
import galaxy.ide.auth.role.IRole;
import galaxy.ide.auth.role.IUser;
import galaxy.ide.service.auth.AuthorizationService;
import galaxy.ide.site.role.IAdmin;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.osgi.service.http.HttpService;

/**
 * @author caiyu
 * @version 1.0.0 Oct 31, 2011 9:49:33 AM
 */
public class JspHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String SERVICE_JSP_SHOWUSERS = "jsp_showUsers";

	private static final String SERVICE_JSP_ADDUSERS = "jsp_addUser";

	private static final String SERVICE_JSP_REMOVEUSERS = "jsp_removeUser";

	private static final String SERVICE_JSP_MODIFYUSER = "jsp_modifyUser";

	private static final String SERVICE_JSP_SHOWROLES = "jsp_showRoles";

	private static final String SERVICE_JSP_ADDROLE = "jsp_addRole";

	private static final String SERVICE_JSP_REMOVEROLES = "jsp_removeRole";

	private static final String SERVICE_JSP_MODIFYROLE = "jsp_modifyRole";

	private static final String SERVICE_JSP_SHOWPERMS = "jsp_showPerms";

	private static final String SERVICE_JSP_ADDPERM = "jsp_addPerm";

	private static final String SERVICE_JSP_REMOVEPERMS = "jsp_removePerms";

	private static final String SERVICE_JSP_MODIFYPERM = "jsp_modifyPerm";

	private static final String SERVICE_JSP_SHOWPCATES = "jsp_showPCates";

	private static final String SERVICE_JSP_ADDPCATE = "jsp_addPCate";

	private static final String SERVICE_JSP_REMOVEPCATES = "jsp_removePCates";

	private static final String SERVICE_JSP_MODIFYPCATE = "jsp_modifyPCate";

	private static final String EXEC_SQL = "exec_sql";

	private static final String SERVICE_JSP_SHOWADMINS = "jsp_showAdmins";

	private static final String SERVICE_JSP_MODIFYADMIN = "jsp_modifyAdmin";

	private static final String SERVICE_JSP_REMOVEADMINS = "jsp_removeAdmins";

	private static final String SERVICE_JSP_ADDADMIN = "jsp_addAdmin";

	private HttpService httpService;

	private AuthorizationService authService;

	private final int START = 0;
	private final int LIMIT = Integer.MAX_VALUE;

	public void setAuthService(AuthorizationService authService) {
		this.authService = authService;
	}

	public void unsetAuthService(AuthorizationService authService) {
		if (authService != this.authService)
			return;
		this.authService = null;
	}

	private static final String Alias_JspHttpServlet = "/jspHttpServlet";

	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
		try {
			this.httpService.registerServlet(Alias_JspHttpServlet, this, null,
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unsetHttpService(HttpService httpService) {
		if (httpService != this.httpService) {
			return;
		}
		this.httpService.unregister(Alias_JspHttpServlet);
		this.httpService = null;
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doGet()");

		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("doPost()");

		String name = req.getParameter(AuthHttpConstants.PARA_NAME);
		String pass = req.getParameter(AuthHttpConstants.PARA_PASS);
		String service = req.getParameter(AuthHttpConstants.PARA_SERVICE);
		print("Service:" + service);
		resp.setContentType("text/html; charset=utf-8");
		// req.setCharacterEncoding("utf-8");
		if (service == null) {
			RequestDispatcher rd = req.getRequestDispatcher("/auth/login.jsp");
			rd.forward(req, resp);
			return;
		}
		if (service.equals("login")) {
			if (!isValidAdmin(req, resp, name, pass)) {
				return;
			}
		} else {
			String n = (String) req.getSession().getAttribute("username");
			String p = (String) req.getSession().getAttribute("password");
			if (!authService.isValidAdmin(n, p)) {
				return;
			}
		}

		if (service.equals("logout")) {
			logout(req, resp);
		} else if (service.equals("getRole")) {
			getRole(req, resp, name, pass);
		} else if (service.equals(SERVICE_JSP_SHOWUSERS)) {
			showUsers(req, resp);
		} else if (service.equals(SERVICE_JSP_ADDUSERS)) {
			addUsers(req, resp);
		} else if (service.equals(SERVICE_JSP_REMOVEUSERS)) {
			removeUsers(req, resp);
		} else if (service.equals(SERVICE_JSP_MODIFYUSER)) {
			modifyUsers(req, resp);
		} else if (service.equals(SERVICE_JSP_SHOWROLES)) {
			showRoles(req, resp);
		} else if (service.equals(SERVICE_JSP_ADDROLE)) {
			addRole(req, resp);
		} else if (service.equals(SERVICE_JSP_REMOVEROLES)) {
			removeRoles(req, resp);
		} else if (service.equals(SERVICE_JSP_MODIFYROLE)) {
			modifyRole(req, resp);
		} else if (service.equals(SERVICE_JSP_SHOWPERMS)) {
			showPerms(req, resp);
		} else if (service.equals(SERVICE_JSP_ADDPERM)) {
			addPerm(req, resp);
		} else if (service.equals(SERVICE_JSP_REMOVEPERMS)) {
			removePerms(req, resp);
		} else if (service.equals(SERVICE_JSP_MODIFYPERM)) {
			modifyPerm(req, resp);
		} else if (service.equals(SERVICE_JSP_SHOWPCATES)) {
			showPCates(req, resp);
		} else if (service.equals(SERVICE_JSP_ADDPCATE)) {
			addPCate(req, resp);
		} else if (service.equals(SERVICE_JSP_REMOVEPCATES)) {
			removePCates(req, resp);
		} else if (service.equals(SERVICE_JSP_SHOWADMINS)) {
			showAdmins(req, resp);
		} else if (service.equals(SERVICE_JSP_ADDADMIN)) {
			addAdmin(req, resp);
		} else if (service.equals(SERVICE_JSP_MODIFYADMIN)) {
			modifyAdmin(req, resp);
		} else if (service.equals(SERVICE_JSP_REMOVEADMINS)) {
			removeAdmins(req, resp);
		}
		resp.flushBuffer();
	}

	private boolean isValidAdmin(HttpServletRequest req,
			HttpServletResponse resp, String name, String pass)
			throws IOException, ServletException {
		HttpSession session = req.getSession(true);
		boolean isValidAdmin = authService.isValidAdmin(name, pass);
		if (isValidAdmin) {
			session.setAttribute("username", name);
			session.setAttribute("password", pass);
			IAdmin admin = authService.getAdmin(name);
			session.setAttribute("nickname", admin.getName());
			resp.sendRedirect("/auth/index.jsp");
		} else {
			RequestDispatcher rd = req.getRequestDispatcher("/auth/login.jsp");
			req.setAttribute("message", "错误的用户名或密码");
			rd.forward(req, resp);
		}
		return isValidAdmin;
	}

	private void getRole(HttpServletRequest req, HttpServletResponse resp,
			String name, String pass) throws IOException {
		AuthMsg authMsg = authService.getAuthMsg(name, pass);
		byte[] bytes = AuthMsgCoder.newInstance(AuthMsgCoder.Type_DELI).toWire(
				authMsg);
		resp.getOutputStream().write(bytes, 0, bytes.length);
	}

	private void removeAdmins(HttpServletRequest req, HttpServletResponse resp) {
		// TODO 移除管理员
		String ids = req.getParameter("Ids");
		if (ids.contains(","))
			authService.removeAdmin(ids.split(","));
		else
			authService.removeAdmin(new String[] { ids });

	}

	private void modifyAdmin(HttpServletRequest req, HttpServletResponse resp) {
		// TODO 修改管理员
		String id = req.getParameter("uid");
		String newName = req.getParameter("name");
		String newPass = req.getParameter("password1");
		String newInfo = "";
		authService.modifyAdmin(id, newName, newPass, newInfo);
	}

	private void addAdmin(HttpServletRequest req, HttpServletResponse resp) {
		// TODO 添加管理员
		String newId = req.getParameter("uid");
		String newName = req.getParameter("name");
		String newPass = req.getParameter("password1");
		String newInfo = "";
		authService.addAdmin(newId, newName, newPass, newInfo);
	}

	private void showAdmins(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// TODO 展示管理员
		int start = handleInteger(req.getParameter("start"), START);
		int limit = handleInteger(req.getParameter("limit"), LIMIT);
		List<IAdmin> users = authService.getAllAdmins(start, limit);
		int total = authService.getAllAdmins().size();
		StringBuilder sb = new StringBuilder();
		sb.append("{total:");
		sb.append(total);
		sb.append(",rows:[");
		for (IAdmin user : users) {
			String info = user.getUserInfo("info");
			if (info == null) {
				info = "";
			}

			sb.append("{");
			sb.append("uid:" + "\'" + user.getID() + "\'");
			sb.append(",name:" + "\'" + user.getName() + "\'");
			sb.append(",info:" + "\'" + info + "\'");
			sb.append("},");
		}
		String json = sb.substring(0, sb.length() - 1);
		json += "]}";
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().println(json);

	}

	private void removePCates(HttpServletRequest req, HttpServletResponse resp) {
		// TODO 删除权限分类
		String ids = req.getParameter("Ids");
		if (ids.contains(","))
			authService.removePCategory(ids.split(","));
		else
			authService.removePCategory(new String[] { ids });
	}

	private void addPCate(HttpServletRequest req, HttpServletResponse resp) {
		// TODO 添加权限分类
		String newName = req.getParameter("newName");
		String newDesc = req.getParameter("newDesc");
		authService.addPCategory(newName, newDesc);
	}

	private void showPCates(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// TODO 查询权限分类
		String start = req.getParameter("start");
		String limit = req.getParameter("limit");

		List<IPCategory> pcates = authService.getAllPCategories();
		int total = pcates.size();
		if (start != null) {
			pcates = authService.getAllPCategories(Integer.parseInt(start),
					Integer.parseInt(limit));
		}

		StringBuilder sb = new StringBuilder();
		sb.append("{total:");
		sb.append(total);
		sb.append(",rows:[");
		for (IPCategory pcate : pcates) {
			sb.append(pcate.toJSONString());
			sb.append(",");
		}
		String json = sb.substring(0, sb.length() - 1);
		json += "]}";
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().println(json);
	}

	private void modifyPerm(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("pid");
		String newName = req.getParameter("name");
		String newDesc = req.getParameter("desc");
		String newCategory = req.getParameter("category_id");

		authService.modifyPermission(id, newName, newDesc, newCategory);
	}

	private void removePerms(HttpServletRequest req, HttpServletResponse resp) {
		String ids = req.getParameter("Ids");
		if (ids.contains(","))
			authService.removePermissions(ids.split(","));
		else
			authService.removePermissions(new String[] { ids });
	}

	private void addPerm(HttpServletRequest req, HttpServletResponse resp) {
		String newId = req.getParameter("pid");
		String newName = req.getParameter("name");
		String newDesc = req.getParameter("desc");
		String newCategory = req.getParameter("category");

		authService.addPermission(newId, newName, newDesc, newCategory);

	}

	private void showPerms(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String roleId = req.getParameter("rid");
		// TODO 获取指定角色的权限
		if (roleId != null) {
			IRole role = authService.getRole(roleId);
			Collection<IPermission> perms = role.getPermissions();
			StringBuilder sb = new StringBuilder();
			sb.append("{rows:[");
			for (IPermission perm : perms) {
				String cname = perm.getCategory().getName();
				if (cname == null)
					cname = "";
				sb.append("{");
				sb.append("pid:" + "\'" + perm.getID() + "\'");
				sb.append(",name:" + "\'" + perm.getName() + "\'");
				sb.append(",desc:" + "\'" + perm.getDescription() + "\'");
				sb.append(",category:" + "\'" + cname + "\'");
				sb.append("},");
			}
			String json = sb.substring(0, sb.length() - 1);
			json += "]}";
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().println(json);
			return;
		}

		List<IPermission> perms = authService.getAllPerms();
		String ex_roleId = req.getParameter("ex_rid");
		if (ex_roleId != null) {
			IRole role = authService.getRole(ex_roleId);

			perms.removeAll(role.getPermissions());
		}

		StringBuilder sb = new StringBuilder();
		sb.append("{rows:[");
		for (IPermission perm : perms) {
			String cname = perm.getCategory().getName();
			if (cname == null)
				cname = "";
			sb.append("{");
			sb.append("pid:" + "\'" + perm.getID() + "\'");
			sb.append(",name:" + "\'" + perm.getName() + "\'");
			sb.append(",desc:" + "\'" + perm.getDescription() + "\'");
			sb.append(",category:" + "\'" + cname + "\'");
			sb.append(",category_id:" + "\'" + perm.getCategory().getID()
					+ "\'");
			sb.append("},");
		}
		String json = sb.substring(0, sb.length() - 1);
		json += "]}";
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().println(json);

	}

	private void modifyRole(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("rid");
		String newPermissions = req.getParameter("Ids");
		if (newPermissions != null) {
			String[] permissions = null;
			if (newPermissions.contains(",")) {
				permissions = newPermissions.split(",");
			} else {
				permissions = new String[] { newPermissions };
			}
			authService.modifyRolePermission(id, permissions);
			return;
		}

		String newName = req.getParameter("name");
		String newDesc = req.getParameter("desc");

		authService.modifyRole(id, newName, newDesc);

	}

	private void removeRoles(HttpServletRequest req, HttpServletResponse resp) {
		String ids = req.getParameter("Ids");
		if (ids.contains(","))
			authService.removeRole(ids.split(","));
		else
			authService.removeRole(new String[] { ids });
	}

	private void addRole(HttpServletRequest req, HttpServletResponse resp) {
		String newName = req.getParameter("name");
		String newDesc = req.getParameter("desc");
		authService.addRole(newName, newDesc);
		// print(service);
	}

	private void showRoles(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String uid = req.getParameter("uid");
		if (uid != null) {
			IUser user = authService.getUser(uid);
			Collection<IRole> roles = user.getRoles();
			StringBuilder sb = new StringBuilder();
			sb.append("{rows:[");
			for (IRole role : roles) {
				sb.append("{");
				sb.append("rid:" + "\'" + role.getID() + "\'");
				sb.append(",name:" + "\'" + role.getName() + "\'");
				sb.append(",desc:" + "\'" + role.getDescription() + "\'");
				sb.append("},");
			}
			String json = sb.substring(0, sb.length() - 1);
			json += "]}";
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().println(json);
			return;
		}

		String ex_uid = req.getParameter("ex_uid");
		if (ex_uid != null) {
			IUser user = authService.getUser(ex_uid);
			Collection<IRole> roles = authService.getAllRoles();
			roles.removeAll(user.getRoles());
			StringBuilder sb = new StringBuilder();
			sb.append("{rows:[");
			for (IRole role : roles) {
				sb.append("{");
				sb.append("rid:" + "\'" + role.getID() + "\'");
				sb.append(",name:" + "\'" + role.getName() + "\'");
				sb.append(",desc:" + "\'" + role.getDescription() + "\'");
				sb.append("},");
			}
			String json = sb.substring(0, sb.length() - 1);
			json += "]}";
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().println(json);
			return;
		}

		int start = handleInteger(req.getParameter("start"), START);
		int limit = handleInteger(req.getParameter("limit"), LIMIT);
		List<IRole> roles = authService.getAllRoles(start, limit);
		int total = authService.getAllRoles().size();

		StringBuilder sb = new StringBuilder();
		sb.append("{total:");
		sb.append(total);
		sb.append(",rows:[");
		for (IRole role : roles) {
			sb.append("{");
			sb.append("rid:" + "\'" + role.getID() + "\'");
			sb.append(",name:" + "\'" + role.getName() + "\'");
			sb.append(",desc:" + "\'" + role.getDescription() + "\'");
			sb.append(",perms:" + "\'");
			int count = 0;
			for (IPermission perm : role.getPermissions()) {
				String pn = perm.getName();
				if (pn == null)
					pn = "";
				sb.append(pn.length() > 3 ? pn.substring(0, 3) + "..." : pn);
				sb.append(" |");
				if (count++ > 2)
					break;
			}
			sb.append("(Items:");
			sb.append(role.getPermissions().size());
			sb.append(")");
			sb.append("\'");
			sb.append("},");
		}
		String json = sb.substring(0, sb.length() - 1);
		json += "]}";
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().println(json);

	}

	private void modifyUsers(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("uid");
		String newRoles = req.getParameter("Ids");
		if (newRoles != null) {
			String[] roles = null;
			if (newRoles.contains(",")) {
				roles = newRoles.split(",");
			} else {
				roles = new String[] { newRoles };
			}
			authService.modifyUserRole(id, roles);
			return;
		}

		String newName = req.getParameter("name");
		String newPass = req.getParameter("password1");
		String newInfo = "";
		authService.modifyUser(id, newName, newPass, newInfo);

	}

	private void removeUsers(HttpServletRequest req, HttpServletResponse resp) {
		String ids = req.getParameter("Ids");
		if (ids.contains(","))
			authService.removeUser(ids.split(","));
		else
			authService.removeUser(new String[] { ids });
	}

	private void addUsers(HttpServletRequest req, HttpServletResponse resp) {
		String newId = req.getParameter("uid");
		String newName = req.getParameter("name");
		String newPass = req.getParameter("password1");
		String newInfo = "";
		authService.addUser(newId, newName, newPass, newInfo);
	}

	private void showUsers(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		int start = handleInteger(req.getParameter("start"), START);
		int limit = handleInteger(req.getParameter("limit"), LIMIT);
		List<IUser> users = authService.getAllUsers(start, limit);
		int total = authService.getAllUsers().size();

		StringBuilder sb = new StringBuilder();
		sb.append("{total:");
		sb.append(total);
		sb.append(",rows:[");
		for (IUser user : users) {
			String info = user.getUserInfo("info");
			if (info == null) {
				info = "";
			}
			sb.append("{");
			sb.append("uid:" + "\'" + user.getID() + "\'");
			sb.append(",name:" + "\'" + user.getName() + "\'");
			sb.append(",info:" + "\'" + info + "\'");
			sb.append(",roles:\'");
			for (IRole role : user.getRoles()) {
				sb.append(role.getName() + " |");
			}
			sb.append("(Items:");
			sb.append(user.getRoles().size());
			sb.append(")");
			sb.append("\'");
			sb.append("},");
		}
		String json = sb.substring(0, sb.length() - 1);
		json += "]}";
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().println(json);

		// print(json);

	}

	/**
	 * 字符串转换整数，如果出错，返回默认值
	 * 
	 * @param parameter
	 * @param def
	 * @return
	 */
	private int handleInteger(String parameter, int def) {
		try {
			return Integer.parseInt(parameter);
		} catch (NumberFormatException e) {
		}
		return def;
	}

	private void logout(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		req.getSession().removeAttribute("username");
		req.getSession().removeAttribute("password");
		resp.sendRedirect("/auth/index.jsp");
	}

	private void doWriteString(HttpServletResponse resp, String msg)
			throws IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().println(msg);
	}

	private void print(Object obj) {
		System.out.println("---------------------------\n\n" + obj
				+ "\n\n---------------------------");
	}

}
