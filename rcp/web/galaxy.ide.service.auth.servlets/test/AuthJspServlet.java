/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.ide.service.auth.servlets;

import galaxy.ide.service.auth.AuthorizationService;

import org.eclipse.equinox.jsp.jasper.JspServlet;
import org.osgi.framework.Bundle;
import org.osgi.service.http.HttpService;

/**
 * @author dzh
 * 
 */
public class AuthJspServlet extends JspServlet {

	public AuthJspServlet(Bundle bundle, String bundleResourcePath) {
		super(bundle, bundleResourcePath);
	}

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

	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
		try {
//			this.httpService.registerServlet("/authJspServlet", this, null,
//					null);
//			this.httpService.registerResources("/pages", "/pages", null);
			this.httpService.registerResources("/test", "/test", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void unsetHttpService(HttpService httpService) {
		if (httpService != this.httpService) {
			return;
		}
		this.httpService.unregister("/arzan/authority");
		this.httpService.unregister("/arzan/pages");

		this.httpService = null;
	}
	
	

}
