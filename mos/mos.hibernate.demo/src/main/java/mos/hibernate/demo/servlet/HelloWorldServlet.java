/* Copyright 2008 Alin Dreghiciu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mos.hibernate.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mos.hibernate.demo.Cat;
import mos.hibernate.demo.CatDaoImpl;
import mos.hibernate.manager.HbmConfigContainer;
import mos.hibernate.manager.HbmConfigContainerManager;

/**
 * Hello World Servlet.
 * 
 * @author Alin Dreghiciu
 * @since 0.3.0, January 02, 2008
 */
public class HelloWorldServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String m_registrationPath;

	public HelloWorldServlet(final String registrationPath) {

		m_registrationPath = registrationPath;
	}

	protected void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("text/html");
		Map<String, HbmConfigContainer> map = HbmConfigContainerManager
				.getInstance().getAllHbmConfigContainer();

		final PrintWriter writer = response.getWriter();
		writer.println("<html><body align='center'>");
		writer.println("<h1>Config List</h1><br/>");
		if (map != null) {
			for (HbmConfigContainer container : map.values()) {
				if (container != null) {
					writer.println("<h2>DatabaseConfig:"
							+ container.getDatabaseConfig() + "</h2><p>");
					writer.println("<h2>MappingConfig:"
							+ container.getMappingConfigs() + "</h2>");
				}
			}
		}
		CatDaoImpl cat = new CatDaoImpl("hsqldb");

		Cat cat1 = new Cat();
		cat1.setColor("yellow");
		cat1.setId(0);
		cat1.setName("Meow");
		cat.addCat(cat1);
		writer.println("<br/>" + cat.findByName("Meow"));

		// DemoDaoImpl demoDao = new DemoDaoImpl("hsqldb");
		// demoDao.findByName("");
		writer.println("</body></html>");
	}
}
