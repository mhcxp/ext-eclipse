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
package ext.eclipse.logback.property;

import org.eclipse.core.runtime.Platform;

import ch.qos.logback.core.PropertyDefinerBase;

/**
 * <p>
 * 将Eclipse平台的一些信息,转换为动态属性;通常直接以标准属性名一致;
 * </p>
 * <li>
 * 文件夹目录以“/”结尾</li>
 * <p>
 * {@link org.eclipse.core.runtime.Platform}
 * {@link org.eclipse.osgi.service.datalocation.Location}
 * </p>
 * 
 * @author dzh
 * @version 1.0 Jun 15, 2012 12:24:48 AM
 */
public class PlatformPropertyDefiner extends PropertyDefinerBase {

	/**
	 * This is aPropertyElement
	 */
	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name == null)
			name = "";
		this.name = name;
	}

	@Override
	public String getPropertyValue() {
		String value = null;
		if (name.startsWith("osgi.")) {
			value = queryOsgiValue();
		}

		if (value == null || "".equals(value)) {
			value = "Not found property: !" + name + "!";
		}

		return value;
	}

	private String queryOsgiValue() {
		if ("osgi.instance.area".equals(name)) {
			return Platform.getInstanceLocation().getURL().getPath();
		}
		if ("osgi.install.area".equals(name)) {
			return Platform.getInstallLocation().getURL().getPath();
		}
		if ("osgi.configuration.area".equals(name)) {
			return Platform.getConfigurationLocation().getURL().getPath();
		}
		if ("osgi.user.area".equals(name)) {
			return Platform.getUserLocation().getURL().getPath();
		}
		return null;
	}
}
