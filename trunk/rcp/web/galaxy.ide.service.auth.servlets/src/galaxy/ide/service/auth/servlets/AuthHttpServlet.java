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
import galaxy.ide.service.auth.AuthorizationService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.http.HttpService;

/**
 * @author dzh
 * @version 1.0.0 Oct 31, 2011 9:49:33 AM
 */
public class AuthHttpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpService httpService;

	private AuthorizationService authService;

	public void setAuthService(AuthorizationService authService) {
		this.authService = authService;
	}

	public void unsetAuthService(AuthorizationService authService) {
		if (authService != this.authService)
			return;
		this.authService = null;
	}

	private static final String Alias_AuthHttpServlet = "/authHttpServlet";

	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
		try {
			this.httpService.registerServlet(Alias_AuthHttpServlet, this, null,
					null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unsetHttpService(HttpService httpService) {
		if (httpService != this.httpService) {
			return;
		}
		this.httpService.unregister(Alias_AuthHttpServlet);
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

		resp.setContentType("text/html; charset=utf-8");
		req.setCharacterEncoding("utf-8");
		if (service.equals("isValidUser")) {
			boolean isValidUser = authService.isValidUser(name, pass);
			resp.getWriter().print(String.valueOf(isValidUser));
		} else if (service.equals("getRole")) {
			AuthMsg authMsg = authService.getAuthMsg(name, pass);
			byte[] bytes = AuthMsgCoder.newInstance(AuthMsgCoder.Type_DELI)
					.toWire(authMsg);
			resp.getOutputStream().write(bytes, 0, bytes.length);
		} else if (service.equals("test")) {
			resp.getWriter().print("<html>");
			resp.getWriter().print("<head>");
			resp.getWriter().print("<p>hello world!</p>");
			resp.getWriter().print("</head>");
			resp.getWriter().print("<body><p>test</p></body>");
			resp.getWriter().print("</html>");
		} else if (service.equals("logout")) {
			req.getSession().removeAttribute("username");
			req.getSession().removeAttribute("password");
			resp.sendRedirect("/auth/index.jsp");
		}

		resp.flushBuffer();
	}

	// private void print(Object obj) {
	// System.out.println("---------------------------\n\n" + obj
	// + "\n\n---------------------------");
	// }

}
