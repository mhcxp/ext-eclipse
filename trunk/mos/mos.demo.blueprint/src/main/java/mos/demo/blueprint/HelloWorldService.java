/**
 * Copyright 2012 Ext-Eclipse Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mos.demo.blueprint;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.servlet.ServletException;

import org.osgi.service.http.HttpContext;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;

/**
 * @author dzh
 * @version 1.0 Sep 14, 2012 4:00:27 PM
 */
public class HelloWorldService {
	private HttpService httpService;

	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
	}

	public HttpService getHttpService() {
		return this.httpService;
	}

	public void start() throws ServletException, NamespaceException {
		// create a default context to share between registrations
		final HttpContext httpContext = httpService.createDefaultHttpContext();
		// register the hello world servlet
		final Dictionary<String, String> initParams = new Hashtable<String, String>();
		initParams.put("from", "HttpService");
		httpService.registerServlet("/hw", // alias
				new HelloWorldServlet("/hw"), // registered servlet
				initParams, // init params
				httpContext // http context
				);
		// register images as resources
		httpService.registerResources("/images", "/images", httpContext);
	}

	public void stop() {
		try {
			httpService.unregister("/helloworld/hs");
			httpService.unregister("/*");
			httpService.unregister("/images");
		} catch (Exception e) {

		}
	}
}
